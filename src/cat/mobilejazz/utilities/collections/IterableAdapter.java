package cat.mobilejazz.utilities.collections;

import java.util.Iterator;

public abstract class IterableAdapter<T, V> implements Iterable<V> {

	private class InternalIteratorAdapter extends IteratorAdapter<T, V> {

		public InternalIteratorAdapter(Iterator<T> delegate) {
			super(delegate);
		}

		@Override
		protected V convert(T obj) {
			return IterableAdapter.this.convert(obj);
		}

	}

	private Iterable<T> delegate;

	public IterableAdapter(Iterable<T> delegate) {
		this.delegate = delegate;
	}

	public Iterable<T> getDelegate() {
		return delegate;
	}

	protected abstract V convert(T obj);

	@Override
	public Iterator<V> iterator() {
		return new InternalIteratorAdapter(delegate.iterator());
	}

}
