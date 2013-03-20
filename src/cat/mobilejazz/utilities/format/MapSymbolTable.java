package cat.mobilejazz.utilities.format;

import java.util.HashMap;
import java.util.Map;

/**
 * A default symbol table implementation that is backed by a {@link Map}.
 * 
 * @author Hannes Widmoser
 * 
 * @param <T>
 */
public class MapSymbolTable<T> implements MutableSymbolTable<T> {

	private Map<String, T> map;

	/**
	 * Creates a new instance of this class backed by the specified map. This
	 * constructor is typically used to provide custom map implementations (e.g.
	 * {@link TreeMap}).
	 * 
	 * @param map
	 *            An arbitrary {@link Map} instance.
	 */
	public MapSymbolTable(Map<String, T> map) {
		this.map = map;
	}

	/**
	 * Craetes a new instance of this class backed by an initially empty
	 * {@link HashMap}.
	 */
	public MapSymbolTable() {
		this.map = new HashMap<String, T>();
	}

	@Override
	public T getSymbol(String name) {
		return map.get(name);
	}

	@Override
	public void put(String name, T value) {
		map.put(name, value);
	}

}
