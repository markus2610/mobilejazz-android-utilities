package cat.mobilejazz.utilities.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class IOUtils {

	/**
	 * Reads from a {@link Reader} and outputs the results to a
	 * {@link CharSequence}. Note that the reader is closed after
	 * reaching the end of the input.
	 * 
	 * @param in
	 *            The input {@link Reader}
	 * @return A {@link CharSequence} representing the contents of the reader
	 *         object.
	 * @throws IOException
	 *             if the some IO error occurred.
	 */
	public static CharSequence read(Reader in) throws IOException {
		StringBuilder result = new StringBuilder();
		int c = in.read();
		while (c >= 0) {
			result.append((char) c);
			c = in.read();
		}
		in.close();
		return result;
	}
	
	public static CharSequence read(InputStream in) throws IOException {
		return read(new InputStreamReader(in));
	}

}
