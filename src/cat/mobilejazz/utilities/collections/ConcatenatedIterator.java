package cat.mobilejazz.utilities.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcatenatedIterator<T> implements Iterator<T> {

	public static <T> Iterable<T> getIterable(List<Iterable<T>> iterables) {
		final List<Iterator<T>> iterators = new ArrayList<Iterator<T>>();
		for (Iterable<T> i : iterables) {
			iterators.add(i.iterator());
		}
		return new Iterable<T>() {

			@Override
			public Iterator<T> iterator() {
				return new ConcatenatedIterator<T>(iterators);
			}

		};
	}

	private List<Iterator<T>> iterators;
	private int currentIndex;

	public ConcatenatedIterator(List<Iterator<T>> iterators) {
		this.iterators = iterators;
		currentIndex = 0;
	}

	@Override
	public boolean hasNext() {
		for (int i = currentIndex; i < iterators.size(); ++i) {
			if (iterators.get(i).hasNext()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public T next() {
		while (!iterators.get(currentIndex).hasNext()) {
			++currentIndex;
		}
		return iterators.get(currentIndex).next();
	}

	@Override
	public void remove() {
		iterators.get(currentIndex).remove();
	}

}
