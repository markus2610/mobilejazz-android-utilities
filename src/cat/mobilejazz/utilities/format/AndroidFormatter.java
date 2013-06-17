package cat.mobilejazz.utilities.format;

import java.util.HashSet;
import java.util.Set;

import android.content.Intent;

/**
 * This class provides convenience methods to print information about android
 * entities.
 * 
 * @author Hannes Widmoser
 * 
 */
public class AndroidFormatter {

	public static Set<String> getFlags(Intent intent) {
		Set<String> result = new HashSet<String>();
		int flags = intent.getFlags();
		if ((flags & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0)
			result.add("FLAG_ACTIVITY_BROUGHT_TO_FRONT");
		if ((flags & Intent.FLAG_ACTIVITY_CLEAR_TASK) != 0)
			result.add("FLAG_ACTIVITY_CLEAR_TASK");
		if ((flags & Intent.FLAG_ACTIVITY_CLEAR_TOP) != 0)
			result.add("FLAG_ACTIVITY_CLEAR_TOP");
		if ((flags & Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET) != 0)
			result.add("FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET");
		if ((flags & Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS) != 0)
			result.add("FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS");
		if ((flags & Intent.FLAG_ACTIVITY_FORWARD_RESULT) != 0)
			result.add("FLAG_ACTIVITY_FORWARD_RESULT");
		if ((flags & Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY) != 0)
			result.add("FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY");
		if ((flags & Intent.FLAG_ACTIVITY_MULTIPLE_TASK) != 0)
			result.add("FLAG_ACTIVITY_MULTIPLE_TASK");
		if ((flags & Intent.FLAG_ACTIVITY_NEW_TASK) != 0)
			result.add("FLAG_ACTIVITY_NEW_TASK");
		if ((flags & Intent.FLAG_ACTIVITY_NO_ANIMATION) != 0)
			result.add("FLAG_ACTIVITY_NO_ANIMATION");
		if ((flags & Intent.FLAG_ACTIVITY_NO_HISTORY) != 0)
			result.add("FLAG_ACTIVITY_NO_HISTORY");
		if ((flags & Intent.FLAG_ACTIVITY_NO_USER_ACTION) != 0)
			result.add("FLAG_ACTIVITY_NO_USER_ACTION");
		if ((flags & Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP) != 0)
			result.add("FLAG_ACTIVITY_PREVIOUS_IS_TOP");
		if ((flags & Intent.FLAG_ACTIVITY_REORDER_TO_FRONT) != 0)
			result.add("FLAG_ACTIVITY_REORDER_TO_FRONT");
		if ((flags & Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED) != 0)
			result.add("FLAG_ACTIVITY_RESET_TASK_IF_NEEDED");
		if ((flags & Intent.FLAG_ACTIVITY_SINGLE_TOP) != 0)
			result.add("FLAG_ACTIVITY_SINGLE_TOP");
		if ((flags & Intent.FLAG_ACTIVITY_TASK_ON_HOME) != 0)
			result.add("FLAG_ACTIVITY_TASK_ON_HOME");
		if ((flags & Intent.FLAG_DEBUG_LOG_RESOLUTION) != 0)
			result.add("FLAG_DEBUG_LOG_RESOLUTION");
		if ((flags & Intent.FLAG_EXCLUDE_STOPPED_PACKAGES) != 0)
			result.add("FLAG_EXCLUDE_STOPPED_PACKAGES");
		if ((flags & Intent.FLAG_FROM_BACKGROUND) != 0)
			result.add("FLAG_FROM_BACKGROUND");
		if ((flags & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0)
			result.add("FLAG_GRANT_READ_URI_PERMISSION");
		if ((flags & Intent.FLAG_GRANT_WRITE_URI_PERMISSION) != 0)
			result.add("FLAG_GRANT_WRITE_URI_PERMISSION");
		if ((flags & Intent.FLAG_INCLUDE_STOPPED_PACKAGES) != 0)
			result.add("FLAG_INCLUDE_STOPPED_PACKAGES");
		if ((flags & Intent.FLAG_RECEIVER_FOREGROUND) != 0)
			result.add("FLAG_RECEIVER_FOREGROUND");
		if ((flags & Intent.FLAG_RECEIVER_REGISTERED_ONLY) != 0)
			result.add("FLAG_RECEIVER_REGISTERED_ONLY");
		if ((flags & Intent.FLAG_RECEIVER_REPLACE_PENDING) != 0)
			result.add("FLAG_RECEIVER_REPLACE_PENDING");
		return result;
	}
}
