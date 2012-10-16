package cat.mobilejazz.utilities.protocols.json;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateConverter implements JsonSerializer<Date>,
		JsonDeserializer<Date> {

	private List<DateFormat> formats;
	
	public DateConverter(String... formatStrings) {
		formats = new ArrayList<DateFormat>();
		for (String s : formatStrings) {
			formats.add(new SimpleDateFormat(s));
		}
	}
	
	
	@Override
	public Date deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		ParseException exc = null;
		for (DateFormat format : formats) { 
			try {
				Date date = format.parse(element.getAsString());
				return date;
			} catch (ParseException e) { // ignore
				exc = e;
			}
		}
		throw new JsonParseException(exc);
	}

	@Override
	public JsonElement serialize(Date date, Type type,
			JsonSerializationContext context) {
		return new JsonPrimitive(formats.get(0).format(date));
	}

}
