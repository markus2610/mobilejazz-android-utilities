package cat.mobilejazz.utilities.collections;

import java.util.LinkedHashMap;

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
