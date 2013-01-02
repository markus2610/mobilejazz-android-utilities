package cat.mobilejazz.utilities.data.database;

import android.content.ContentValues;
import cat.mobilejazz.utilities.format.SymbolTable;

public class ContentValuesSymbolTable implements SymbolTable<Object> {
	
	private ContentValues values;
	
	public ContentValuesSymbolTable(ContentValues values) {
		this.values = values;
	}

	@Override
	public Object getSymbol(String name) {
		return values.get(name);
	}

}
