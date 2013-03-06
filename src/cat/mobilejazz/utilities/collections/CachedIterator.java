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

}