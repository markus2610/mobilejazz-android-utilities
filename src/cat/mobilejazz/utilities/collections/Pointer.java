package cat.mobilejazz.utilities.collections;

public interface Pointer<E> {

	public boolean isAfterLast();

	public void moveToNext();

	public E getValue();

}
