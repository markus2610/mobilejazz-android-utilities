package cat.mobilejazz.utilities;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteTransactionListener;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;

public class CompatibilityUtils {

	public static float getPixels(Context context, float dp) {
		Resources r = context.getResources();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
	}

	public static int getPixelsInt(Context context, float dp) {
		Resources r = context.getResources();
		return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
	}

	/**
	 * In API Level 16, {@link View#setBackgroundDrawable(Drawable)} was
	 * replaced by {@link View#setBackground(Drawable)}. You can use this method
	 * to use the refactored method name from API Level 16 on but fall back to
	 * the old one for compatibility.
	 **/
	@SuppressWarnings("deprecation")
	public static void setBackground(View view, Drawable drawable) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			view.setBackground(drawable);
		} else {
			view.setBackgroundDrawable(drawable);
		}
	}

	/**
	 * In earlier versions of android, AsyncTasks where run on different threads
	 * by employing a {@link ThreadPoolExecutor}. However, in more recent
	 * versions, they all use only one single thread. If this behavior is not
	 * desired, a new method
	 * {@link AsyncTask#executeOnExecutor(Executor, Object...)} was introduced.
	 * However, this method is only available since honeycomb. This helper
	 * method mitigates this change in the framework. For older versions it just
	 * ignores the executor parameter.
	 * 
	 * @param task
	 *            An {@link AsyncTask}
	 * @param executor
	 *            The executor which should host the task.
	 * @param args
	 *            Optional arguments to be passed to the task.
	 */
	@SuppressLint("NewApi")
	public static <Params, Progress, Result> void executeOnExecutor(AsyncTask<Params, Progress, Result> task,
			Executor executor, Params... args) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			task.executeOnExecutor(executor, args);
		} else {
			task.execute(args);
		}
	}

	public static <Params, Progress, Result> void executeParallel(AsyncTask<Params, Progress, Result> task,
			Params... args) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, args);
		} else {
			task.execute(args);
		}
	}

	public static void beginTransactionNonExclusive(SQLiteDatabase db) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			db.beginTransactionNonExclusive();
		} else {
			db.beginTransaction();
		}
	}
	
	public static void beginTransactionNonExclusive(SQLiteDatabase db, SQLiteTransactionListener transactionListener) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			db.beginTransactionWithListenerNonExclusive(transactionListener);
		} else {
			db.beginTransactionWithListener(transactionListener);
		}
	}

}
