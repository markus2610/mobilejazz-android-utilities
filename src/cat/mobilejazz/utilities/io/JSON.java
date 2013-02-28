package cat.mobilejazz.utilities.io;

import java.text.ParseException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class JSON {

	public static String getString(JSONObject obj, String key) throws JSONException {
		if (obj.isNull(key)) {
			return null;
		} else {
			return obj.getString(key);
		}
	}

	public static long getLong(JSONObject obj, String key) throws JSONException {
		if (obj.isNull(key)) {
			return 0L;
		} else {
			return obj.getLong(key);
		}
	}

	public static boolean getBoolean(JSONObject obj, String key) throws JSONException {
		if (obj.isNull(key)) {
			return false;
		} else {
			return obj.getBoolean(key);
		}
	}

	public static Date getTimestamp(JSONObject obj, String key) throws JSONException, ParseException {
		if (obj.isNull(key)) {
			return null;
		} else {
			return DateUtils.parseTimestamp(obj.getString(key));
		}
	}

}
