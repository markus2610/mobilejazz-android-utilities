package cat.mobilejazz.utilities.format;

/**
 * A symbol table that can be modified.
 * 
 * @author Hannes Widmoser
 * 
 * @param <T>
 */
public interface MutableSymbolTable<T> extends SymbolTable<T> {

	/**
	 * Adds the given value to the symbol table. If there is already a value
	 * associated with the given name, that value is overwritten.
	 * 
	 * @param name
	 *            The name this value is associated with.
	 * @param value
	 *            An arbitrary value of type {@code T}.
	 */
	public void put(String name, T value);

}
