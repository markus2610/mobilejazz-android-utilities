package cat.mobilejazz.utilities.collections;

import android.database.Cursor;

public class CursorPointer implements Pointer<Cursor> {

	private Cursor cursor;

	public CursorPointer(Cursor c) {
		cursor = c;
	}

	@Override
	public boolean isAfterLast() {
		return cursor.isAfterLast();
	}

	@Override
	public void moveToNext() {
		cursor.moveToNext();
	}

	@Override
	public Cursor getValue() {
		return cursor;
	}

}
