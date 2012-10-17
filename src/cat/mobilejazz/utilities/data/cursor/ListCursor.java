package cat.mobilejazz.utilities.data.cursor;

import java.util.ArrayList;
import java.util.List;

import android.database.AbstractCursor;
import android.database.Cursor;

public abstract class ListCursor<T> extends AbstractCursor {

	private List<T> data;

	public ListCursor(List<T> data) {
		this.data = data;
		if (data == null) {
			this.data = new ArrayList<T>();
		}
	}

	private int getTypeOfObject(Object obj) {
		if (obj == null) {
			return Cursor.FIELD_TYPE_NULL;
		} else if (obj instanceof byte[]) {
			return Cursor.FIELD_TYPE_BLOB;
		} else if (obj instanceof Float || obj instanceof Double) {
			return Cursor.FIELD_TYPE_FLOAT;
		} else if (obj instanceof Long || obj instanceof Integer
				|| obj instanceof Short || obj instanceof Byte) {
			return Cursor.FIELD_TYPE_INTEGER;
		} else {
			return Cursor.FIELD_TYPE_STRING;
		}
	}

	@Override
	public int getCount() {
		return data.size();
	}

	protected Object get(int column) {
		return getContent(data.get(mPos), column);
	}

	protected abstract Object getContent(T object, int column);

	@Override
	public abstract String[] getColumnNames();

	@Override
	public String getString(int column) {
		Object value = get(column);
        if (value == null) return null;
        return value.toString();
	}

	@Override
	public short getShort(int column) {
		Object value = get(column);
		if (value == null)
			return 0;
		if (value instanceof Number)
			return ((Number) value).shortValue();
		return Short.parseShort(value.toString());
	}

	@Override
	public int getInt(int column) {
		Object value = get(column);
		if (value == null)
			return 0;
		if (value instanceof Number)
			return ((Number) value).intValue();
		return Integer.parseInt(value.toString());
	}

	@Override
	public long getLong(int column) {
		Object value = get(column);
		if (value == null)
			return 0;
		if (value instanceof Number)
			return ((Number) value).longValue();
		return Long.parseLong(value.toString());
	}

	@Override
	public float getFloat(int column) {
		Object value = get(column);
		if (value == null)
			return 0.0f;
		if (value instanceof Number)
			return ((Number) value).floatValue();
		return Float.parseFloat(value.toString());
	}

	@Override
	public double getDouble(int column) {
		Object value = get(column);
		if (value == null)
			return 0.0d;
		if (value instanceof Number)
			return ((Number) value).doubleValue();
		return Double.parseDouble(value.toString());
	}

	@Override
	public byte[] getBlob(int column) {
		Object value = get(column);
		return (byte[]) value;
	}

	@Override
	public int getType(int column) {
		return getTypeOfObject(get(column));
	}

	@Override
	public boolean isNull(int column) {
		return get(column) == null;
	}

}
