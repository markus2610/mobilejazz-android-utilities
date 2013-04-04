package cat.mobilejazz.utilities.collections;

import java.util.LinkedHashMap;

/**
 * 
 * A hash map that does not exceed a maximum number of elements. When the map
 * contains the maximum number of elements and a new element is added, the
 * oldest one is removed to make space for the new one.
 * 
 * @author Hannes Widmoser
 * 
 * @param <K>
 * @param <V>
 */
public class LimitedSizeHashMap<K, V> extends LinkedHashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3414414990555487243L;
	private int maxSize;

	public LimitedSizeHashMap(int maxSize) {
		super(maxSize);
		this.maxSize = maxSize;
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return size() > maxSize;
	}

}
