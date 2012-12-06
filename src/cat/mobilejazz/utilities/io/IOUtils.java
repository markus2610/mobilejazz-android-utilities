package cat.mobilejazz.utilities.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

public class IOUtils {

	/**
	 * Reads from a {@link Reader} and outputs the results to a
	 * {@link CharSequence}. Note that the reader is closed after reaching the
	 * end of the input.
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

	public static void copy(InputStream from, OutputStream to, int chunkSize, ProgressListener listener, long fileSize)
			throws IOException {
		try {
			byte data[] = new byte[chunkSize];
			long total = 0;
			int count;
			while ((count = from.read(data)) != -1) {
				total += count;
				if (listener != null) {
					listener.onProgress(total * 100 / fileSize);
				}
				to.write(data, 0, count);
			}

			to.flush();
			to.close();
			from.close();
		} catch (IOException e) {
			throw e;
		} finally {
			to.close();
			from.close();
		}
	}

	public static void copy(InputStream from, OutputStream to, ProgressListener listener, long fileSize)
			throws IOException {
		copy(from, to, 1024, listener, fileSize);
	}

	public static void copy(InputStream from, OutputStream to, int chunkSize) throws IOException {
		copy(from, to, chunkSize, null, 0);
	}

	public static void copy(InputStream from, OutputStream to) throws IOException {
		copy(from, to, 1024);
	}

}
