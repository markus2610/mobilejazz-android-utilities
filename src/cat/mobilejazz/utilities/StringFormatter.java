package cat.mobilejazz.utilities;

import java.util.Iterator;

public class StringFormatter {

	private static final String INDENT_LITERAL_32 = "                                                                                                                                    ";

	public static StringBuilder appendWS(StringBuilder b, int num) {
		return b.append(INDENT_LITERAL_32, 0, num);
	}
	
	public static <V> CharSequence printIterable(Iterable<V> iterable,
			CharSequence delimiter, ObjectPrinter<V> printer) {
		StringBuilder output = new StringBuilder();
		Iterator<V> i = iterable.iterator();
		if (i.hasNext()) {
			output.append(printer.toString(i.next()));
		}
		while (i.hasNext()) {
			output.append(delimiter);
			output.append(printer.toString(i.next()));
		}
		return output;
	}

	@SuppressWarnings("unchecked")
	public static <V> CharSequence printIterable(Iterable<V> iterable,
			CharSequence delimiter) {
		return printIterable(iterable, delimiter,
				(ObjectPrinter<V>) DefaultObjectPrinter.getInstance());
	}

	public static <V> CharSequence printIterable(Iterable<V> iterable) {
		return printIterable(iterable, ", ");
	}

	public static <V> CharSequence printIterable(CharSequence delimiter,
			ObjectPrinter<V> printer, V... array) {
		StringBuilder output = new StringBuilder();
		if (array.length > 0) {
			output.append(printer.toString(array[0]));
		}
		for (int i = 1; i < array.length; i++) {
			output.append(delimiter);
			output.append(printer.toString(array[i]));
		}
		return output;
	}

	@SuppressWarnings("unchecked")
	public static <V> CharSequence printIterable(CharSequence delimiter,
			V... array) {
		return printIterable(delimiter,
				(ObjectPrinter<V>) DefaultObjectPrinter.getInstance(), array);
	}

	public static <V> CharSequence printIterable(V... array) {
		return printIterable(", ", array);
	}

}
