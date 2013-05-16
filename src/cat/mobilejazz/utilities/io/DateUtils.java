package cat.mobilejazz.utilities.io;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.text.TextUtils;

public class DateUtils {

	private static ThreadLocal<DateUtils> instance = new ThreadLocal<DateUtils>() {
		@Override
		protected DateUtils initialValue() {
			return new DateUtils();
		};
	};
	
	public static DateUtils get() {
		return instance.get();
	}
 	
	private SimpleDateFormat iso8601FormatUTC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

	private SimpleDateFormat sqlFormatUTC = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

	public DateUtils() {
		iso8601FormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
		sqlFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	public Date parseTimestamp(String dateString) throws ParseException {
		if (!TextUtils.isEmpty(dateString)) {
			return iso8601FormatUTC.parse(dateString);
		} else {
			return null;
		}
	}

	public Date parseDate(String dateString) throws ParseException {
		if (!TextUtils.isEmpty(dateString)) {
			return sqlFormatUTC.parse(dateString);
		} else {
			return null;
		}
	}

	public String formatTimestamp(Date date) {
		if (date != null)
			return iso8601FormatUTC.format(date);
		else
			return null;
	}

	public String formatDate(Date date) {
		if (date != null)
			return sqlFormatUTC.format(date);
		else
			return null;
	}

}
