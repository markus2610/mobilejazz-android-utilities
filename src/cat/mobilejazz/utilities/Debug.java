package cat.mobilejazz.utilities;

import android.util.Log;

public class Debug {

	protected static StackTraceElement getCurrentMethod() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		return stackTrace[stackTrace.length - 1]; 
	}
	
	public static String getClassTag() {
		return getCurrentMethod().getClassName();
	}
	
	public static String getClassMethodTag(String delim) {
		StackTraceElement elem = getCurrentMethod();
		return elem.getClassName() + delim + elem.getMethodName();
	}
	
	public static String getDefaultTag() {
		return getClassMethodTag(" - ");
	}
	
	public static void debug(String message) {
		Log.d(getDefaultTag(), message);
	}
	
	public static void verbose(String message) {
		Log.v(getDefaultTag(), message);
	}
	
	public static void info(String message) {
		Log.i(getDefaultTag(), message);
	}
	
	public static void warning(String message) {
		Log.w(getDefaultTag(), message);
	}
	
	public static void error(String message) {
		Log.e(getDefaultTag(), message);
	}
	
	public static void logMethod() {
		StackTraceElement elem = getCurrentMethod();
		Log.v(elem.getClassName(), elem.getMethodName());
	}
	
	public static void logMethodVerbose(Object... params) {
		Log.v("KDebug", "logMethodVerbose");
		
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement currentMethod = stackTrace[3];
		try {
			
			
			Class<?> clazz = Class.forName(currentMethod.getClassName());
			
			Class<?>[] methodParams = new Class<?>[params.length];
			for (int i = 0; i < params.length; i++) {
				if (params[i] != null)
					methodParams[i] = params[i].getClass();
				else
					methodParams[i] = null;
			}
			
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
			
			Log.v(currentMethod.getClassName(), verboseOutput.toString());

		} catch (ClassNotFoundException e) {
			Log.v(currentMethod.getClassName(), currentMethod.getMethodName());
		} catch (SecurityException e) {
			Log.v(currentMethod.getClassName(), currentMethod.getMethodName());
		}
		
		
	}

}
