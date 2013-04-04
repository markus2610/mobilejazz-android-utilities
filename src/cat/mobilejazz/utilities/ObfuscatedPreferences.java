package cat.mobilejazz.utilities;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Base64;

/** 
 * Provides access to obfuscated keys in a {@link SharedPreferences} object.
 * Based on Android LVL code, PreferenceObfuscator and AESObfuscator classes.
 */
public class ObfuscatedPreferences {
    private static final String UTF8 = "UTF-8";
    private static final String KEYGEN_ALGORITHM = "PBEWITHSHAAND256BITAES-CBC-BC";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final byte[] IV =
        { 16, 74, 71, -80, 32, 101, -47, 72, 117, -14, 0, -29, 70, 65, -12, 74 };
    private static final String header = "cat.mobilejazz.util.security.ObfuscatedPreferences-1|";
    private Cipher mEncryptor;
    private Cipher mDecryptor;
	
    private final SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    /**
     * Constructor.
     *
     * @param sp A SharedPreferences instance provided by the system.
     * @param o The Obfuscator to use when reading or writing data.
     */
    public ObfuscatedPreferences(Context context, SharedPreferences sp, byte[] salt) {
        mPreferences = sp;
        mEditor = null;
        // init obfuscation
        String applicationId = context.getPackageName();
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(KEYGEN_ALGORITHM);
            KeySpec keySpec =
                new PBEKeySpec((applicationId + deviceId).toCharArray(), salt, 1024, 256);
            SecretKey tmp = factory.generateSecret(keySpec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
            mEncryptor = Cipher.getInstance(CIPHER_ALGORITHM);
            mEncryptor.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(IV));
            mDecryptor = Cipher.getInstance(CIPHER_ALGORITHM);
            mDecryptor.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(IV));
        } catch (GeneralSecurityException e) {
            // This can't happen on a compatible Android device.
            throw new RuntimeException("Invalid environment", e);
        }
    }

    /** Stores the preference, obfuscated */
    public void putString(String key, String value) {
        if (mEditor == null) {
            mEditor = mPreferences.edit();
        }
        String obfuscatedValue = obfuscate(value);
        mEditor.putString(key, obfuscatedValue);
    }

    /** Gets a preference, unobfuscated. If the preference was not obfuscated,
     * it returns the preference as is. If the preference does not exist, returns
     * defValue */
    public String getString(String key, String defValue) {
        String value = mPreferences.getString(key, null);
        if(value == null)
        	return value;
        String result = unobfuscate(value);
        if(result == null)
            return value;
        return result;
    }

    public void commit() {
        if (mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }
    
    // obfuscation
    private String obfuscate(String original) {
        if (original == null) {
            return null;
        }
        try {
            // Header is appended as an integrity check
            return Base64.encodeToString(mEncryptor.doFinal((header + original).getBytes(UTF8)), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Invalid environment", e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Invalid environment", e);
        }
    }

    private String unobfuscate(String obfuscated) {
        if (obfuscated == null) {
            return null;
        }
        try {
            String result = new String(mDecryptor.doFinal(Base64.decode(obfuscated, Base64.DEFAULT)), UTF8);
            // Check for presence of header. This serves as a final integrity check, for cases
            // where the block size is correct during decryption.
            int headerIndex = result.indexOf(header);
            if (headerIndex != 0) {
                return null;
            }
            return result.substring(header.length(), result.length());
        } catch (IllegalBlockSizeException e) {
        	return null;
        } catch (BadPaddingException e) {
        	return null;
        } catch (UnsupportedEncodingException e) {
        	return null;
        }
    }
}
