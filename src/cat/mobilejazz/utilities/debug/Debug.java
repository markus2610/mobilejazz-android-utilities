package cat.mobilejazz.utilities.debug;

import android.text.TextUtils;
import android.util.Log;
import cat.mobilejazz.utilities.BuildConfig;
import cat.mobilejazz.utilities.format.StringFormatter;

public class Debug {

	private static boolean disableLogOutputInReleaseMode = true;

	public static void enableLogOutputInReleaseMode() {
		disableLogOutputInReleaseMode = false;
	}

	private static boolean shouldLog() {
		return !disableLogOutputInReleaseMode || BuildConfig.DEBUG;
	}

	private static boolean internalMethod(StackTraceElement e) {
		return e.getClassName().startsWith("dalvik.") || e.getClassName().startsWith("java.lang.")
				|| e.getClassName().startsWith(Debug.class.getName());
	}

	protected static StackTraceElement getCurrentMethod() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

		int i = 0;
		while (internalMethod(stackTrace[i]) && i < stackTrace.length)
			++i;

		return stackTrace[i];
	}

	public static String getCurrentClassName() {
		return getCurrentMethod().getClassName();
	}

	public static String getClassTag() {
		String className = getCurrentClassName();
		String simpleClassName = className.substring(className.lastIndexOf('.') + 1);
		int innerClassIndex = simpleClassName.indexOf('$');
		if (innerClassIndex >= 0) {
			return simpleClassName.substring(0, innerClassIndex);
		} else {
			return simpleClassName;
		}
	}

	public static String getClassMethodTag(String delim) {
		StackTraceElement elem = getCurrentMethod();
		return elem.getClassName() + delim + elem.getMethodName();
	}

	public static String getDefaultTag() {
		return getClassTag();
	}

	public static void log(int priority, String message, Object... args) {
		if (shouldLog()) {
			if (TextUtils.isEmpty(message)) {
				message = "<NO_MESSAGE>";
			}
			if (args.length > 0) {
				Log.println(priority, getDefaultTag(), String.format(message, args));
			} else {
				Log.println(priority, getDefaultTag(), message);
			}
		}
	}

	public static void debug(String message, Object... args) {
		log(Log.DEBUG, message, args);
	}

	public static void verbose(String message, Object... args) {
		log(Log.VERBOSE, message, args);
	}

	public static void info(String message, Object... args) {
		log(Log.INFO, message, args);
	}

	public static void warning(String message, Object... args) {
		log(Log.WARN, message, args);
	}

	public static void error(String message, Object... args) {
		log(Log.ERROR, message, args);
	}

	public static void logException(Throwable e) {
		if (shouldLog()) {
			e.printStackTrace();

			String message = e.getLocalizedMessage();
			if (message != null)
				error(message);
		}
	}

	public static void logMethod() {
		StackTraceElement elem = getCurrentMethod();
		verbose(elem.getMethodName());
	}

	public static void logMethodVerbose(Object... params) {
		if (shouldLog()) {
			Log.v("KDebug", "logMethodVerbose");
			StackTraceElement currentMethod = getCurrentMethod();
			try {

				StringBuilder verboseOutput = new StringBuilder();
				verboseOutput.append(currentMethod.getMethodName());
				verboseOutput.append("(");
				verboseOutput.append(StringFormatter.printIterable(params));
				verboseOutput.append(")");
				verboseOutput.append("  [");
				verboseOutput.append(currentMethod.getFileName());
				verboseOutput.append(":");
				verboseOutput.append(currentMethod.getLineNumber());
				verboseOutput.append("]");

				String className = currentMethod.getClassName();
				Log.v(className.substring(className.lastIndexOf('.') + 1), verboseOutput.toString());

			} catch (SecurityException e) {
				Log.v(currentMethod.getClassName(), currentMethod.getMethodName());
			}
		}
	}

}
