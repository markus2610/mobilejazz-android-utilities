package cat.mobilejazz.utilities.collections;

import java.util.Iterator;

public abstract class IteratorAdapter<T, V> implements Iterator<V> {

	private Iterator<T> delegate;

	public IteratorAdapter(Iterator<T> delegate) {
		this.delegate = delegate;
	}

	protected abstract V convert(T obj);

	@Override
	public boolean hasNext() {
		return delegate.hasNext();
	}

	@Override
	public V next() {
		return convert(delegate.next());
	}

	@Override
	public void remove() {
		delegate.remove();
	}

}
