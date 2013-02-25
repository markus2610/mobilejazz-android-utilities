package cat.mobilejazz.utilities.io;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.text.TextUtils;

public class DateUtils {

	private static SimpleDateFormat iso8601FormatUTC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat sqlFormatUTC = new SimpleDateFormat("yyyy-MM-dd");

	static {
		iso8601FormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
		sqlFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	public static Date parseTimestamp(String dateString) throws ParseException {
		if (!TextUtils.isEmpty(dateString)) {
			return iso8601FormatUTC.parse(dateString);
		} else {
			return null;
		}
	}

	public static Date parseDate(String dateString) throws ParseException {
		if (!TextUtils.isEmpty(dateString)) {
			return sqlFormatUTC.parse(dateString);
		} else {
			return null;
		}
	}

	public static String formatTimestamp(Date date) {
		if (date != null)
			return iso8601FormatUTC.format(date);
		else
			return null;
	}

	public static String formatDate(Date date) {
		if (date != null)
			return sqlFormatUTC.format(date);
		else
			return null;
	}

}
