package cat.mobilejazz.utilities.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

/**
 * Provides static convenience methods to handle {@link InputStream}s and
 * {@link Reader}s.
 * 
 * @author Hannes Widmoser
 * 
 */
public class IOUtils {

	/**
	 * A listener interface to asynchronously notify progress of a long running
	 * operation such as copying from one stream to another.
	 * 
	 * @author Hannes Widmoser
	 * 
	 */
	public static interface ProgressListener {

		public void onProgress(float progress);

	}

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

	/**
	 * Reads from an {@link InputStream} and outputs the results to a
	 * {@link CharSequence}. Note that the reader is closed after reaching the
	 * end of the input.
	 * 
	 * @param in
	 *            The input {@link InputStream}
	 * @return A {@link CharSequence} representing the contents of the stream
	 *         object.
	 * @throws IOException
	 *             if the some IO error occurred.
	 */
	public static CharSequence read(InputStream in) throws IOException {
		return read(new InputStreamReader(in));
	}

	/**
	 * Copies the content of an {@link InputStream} to a different
	 * {@link OutputStream}.
	 * 
	 * @param from
	 *            The source.
	 * @param to
	 *            The target.
	 * @param chunkSize
	 *            The maximum number of bytes that should be read/written at
	 *            once (i.e. with one operation). See
	 *            {@link InputStream#read(byte[])} for more information.
	 * @param listener
	 *            A {@link ProgressListener} that is informed of the progress of
	 *            the operation. May be {@code null}.
	 * @param fileSize
	 *            The expected number of bytes from the input. If a listener is
	 *            set, this needs to be greater than 0. Otherwise it is ignored.
	 * @throws IOException
	 *             If the operation fails.
	 */
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
