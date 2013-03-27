package cat.mobilejazz.utilities.collections;

import java.util.Iterator;

public class CachedIterator<E> implements Pointer<E> {

	private E value;
	private Iterator<E> iterator;
	private E noDataElement;

	public CachedIterator(Iterator<E> iterator) {
		this(iterator, null);
	}

	public CachedIterator(Iterator<E> iterator, E noDataElement) {
		this.iterator = iterator;
		this.noDataElement = noDataElement;
		moveToNext();
	}

	public void moveToNext() {
		if (iterator.hasNext()) {
			value = iterator.next();
		} else {
			value = noDataElement;
		}
	}

	public boolean isAfterLast() {
		return value == noDataElement;
	}

	public E getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.format("CachedIterator: value = %s; noDataElement = %s; hasNext = %b; isAfterLast = %b", value,
				noDataElement, iterator.hasNext(), isAfterLast());
	}

}