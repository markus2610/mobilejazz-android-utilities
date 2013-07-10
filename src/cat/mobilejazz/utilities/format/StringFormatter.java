package cat.mobilejazz.utilities.format;

import java.nio.ByteBuffer;
import java.util.Iterator;

import android.util.Base64;

public class StringFormatter {

	private static final String INDENT_LITERAL_32 = "                                                                                                                                    ";

	public static StringBuilder appendWS(StringBuilder b, int num) {
		return b.append(INDENT_LITERAL_32, 0, num);
	}

	public static CharSequence repeat(StringBuilder output, CharSequence content, CharSequence delimiter, int count) {
		output.append(content);
		for (int i = 1; i < count; ++i) {
			output.append(delimiter).append(content);
		}
		return output;
	}

	public static CharSequence repeat(StringBuilder output, CharSequence content, int count) {
		return repeat(output, content, " ", count);
	}

	public static CharSequence repeat(CharSequence content, CharSequence delimiter, int count) {
		return repeat(new StringBuilder(), content, delimiter, count);
	}

	public static CharSequence repeat(CharSequence content, int count) {
		return repeat(new StringBuilder(), content, " ", count);
	}

	public static <V> CharSequence printIterable(StringBuilder output, Iterable<V> iterable, CharSequence delimiter,
			ObjectPrinter<V> printer) {
		if (iterable != null) {
			Iterator<V> i = iterable.iterator();
			if (i.hasNext()) {
				output.append(printer.toString(i.next()));
			}
			while (i.hasNext()) {
				output.append(delimiter);
				output.append(printer.toString(i.next()));
			}
			return output;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <V> CharSequence printIterable(StringBuilder output, Iterable<V> iterable, CharSequence delimiter) {
		return printIterable(output, iterable, delimiter, (ObjectPrinter<V>) DefaultObjectPrinter.getInstance());
	}

	public static <V> CharSequence printIterable(StringBuilder output, Iterable<V> iterable) {
		return printIterable(output, iterable, ", ");
	}

	public static <V> CharSequence printIterable(StringBuilder output, CharSequence delimiter,
			ObjectPrinter<V> printer, V... array) {
		if (array != null) {
			if (array.length > 0) {
				output.append(printer.toString(array[0]));
			}
			for (int i = 1; i < array.length; i++) {
				output.append(delimiter);
				output.append(printer.toString(array[i]));
			}
			return output;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <V> CharSequence printIterable(StringBuilder output, CharSequence delimiter, V... array) {
		return printIterable(output, delimiter, (ObjectPrinter<V>) DefaultObjectPrinter.getInstance(), array);
	}

	public static <V> CharSequence printIterable(StringBuilder output, V... array) {
		return printIterable(output, ", ", array);
	}

	public static <V> CharSequence printIterable(Iterable<V> iterable, CharSequence delimiter, ObjectPrinter<V> printer) {
		return printIterable(new StringBuilder(), iterable, delimiter, printer);
	}

	public static <V> CharSequence printIterable(Iterable<V> iterable, CharSequence delimiter) {
		return printIterable(new StringBuilder(), iterable, delimiter);
	}

	public static <V> CharSequence printIterable(Iterable<V> iterable) {
		return printIterable(new StringBuilder(), iterable);
	}

	public static <V> CharSequence printIterable(CharSequence delimiter, ObjectPrinter<V> printer, V... array) {
		return printIterable(new StringBuilder(), delimiter, printer, array);
	}

	public static <V> CharSequence printIterable(CharSequence delimiter, V... array) {
		return printIterable(new StringBuilder(), delimiter, array);
	}

	public static <V> CharSequence printIterable(V... array) {
		return printIterable(new StringBuilder(), array);
	}

	/**
	 * Only upper cases the first letter of {@code in}
	 * 
	 * @param in
	 * @return
	 */
	public static CharSequence toUpperCase(CharSequence in) {
		StringBuilder b = new StringBuilder(in);
		b.setCharAt(0, Character.toUpperCase(in.charAt(0)));
		return b;
	}
	
	public static CharSequence compactIdentityHashCode(Object o) {
		final int id = System.identityHashCode(o);
		final byte[] bytes = ByteBuffer.allocate(4).putInt(id).array();
		return Base64.encodeToString(bytes, Base64.NO_PADDING | Base64.NO_WRAP);
	}

}
