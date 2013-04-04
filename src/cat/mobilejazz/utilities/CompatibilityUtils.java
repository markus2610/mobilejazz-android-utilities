package cat.mobilejazz.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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

}
