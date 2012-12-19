package cat.mobilejazz.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class CompatibilityUtils {

	public static float getPixels(Context context, float dp) {
		Resources r = context.getResources();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
	}
	
	public static int getPixelsInt(Context context, float dp) {
		Resources r = context.getResources();
		return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
	}
	
}
