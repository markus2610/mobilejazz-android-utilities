package cat.mobilejazz.utilities;

import android.util.Log;

public class KDebug {

	public static void logMethod() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement currentMethod = stackTrace[stackTrace.length - 1];
		
		Log.v(currentMethod.getClassName(), currentMethod.getMethodName());
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
