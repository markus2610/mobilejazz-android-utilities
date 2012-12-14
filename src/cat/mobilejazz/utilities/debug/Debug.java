package cat.mobilejazz.utilities.debug;

import android.util.Log;
import cat.mobilejazz.utilities.BuildConfig;
import cat.mobilejazz.utilities.format.StringFormatter;

public class Debug {

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
		return className.substring(className.lastIndexOf('.') + 1);
	}

	public static String getClassMethodTag(String delim) {
		StackTraceElement elem = getCurrentMethod();
		return elem.getClassName() + delim + elem.getMethodName();
	}

	public static String getDefaultTag() {
		return getClassTag();
	}

	public static void debug(String message) {
		if (BuildConfig.DEBUG) {
			Log.d(getDefaultTag(), message);
		}
	}

	public static void verbose(String message) {
		if (BuildConfig.DEBUG) {
			Log.v(getDefaultTag(), message);
		}
	}

	public static void info(String message) {
		if (BuildConfig.DEBUG) {
			Log.i(getDefaultTag(), message);
		}
	}

	public static void warning(String message) {
		if (BuildConfig.DEBUG) {
			Log.w(getDefaultTag(), message);
		}
	}

	public static void error(String message) {
		if (BuildConfig.DEBUG) {
			Log.e(getDefaultTag(), message);
		}
	}

	public static void logException(Throwable e) {
		e.printStackTrace();
		String message = e.getLocalizedMessage();
		if (message != null)
			error(message);
	}

	public static void logMethod() {
		StackTraceElement elem = getCurrentMethod();
		verbose(elem.getMethodName());
	}

	public static void logMethodVerbose(Object... params) {
		if (BuildConfig.DEBUG) {
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
