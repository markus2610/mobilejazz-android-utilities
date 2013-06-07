package cat.mobilejazz.utilities.concurrency;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * A dialog fragment that is attached to {@link PersistentAsyncTask}
 * 
 * @author Hannes Widmoser
 * 
 */
public class AsyncDialogFragment extends DialogFragment {

	public static final String KEY_TASK_INDEX = "cat.mobilejazz.utilities.concurrency.AsyncDialogFragment.KEY_TASK_INDEX";

	private int mTaskIndex;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			mTaskIndex = savedInstanceState.getInt(KEY_TASK_INDEX, -1);
		} else if (getArguments() != null) {
			mTaskIndex = getArguments().getInt(KEY_TASK_INDEX, -1);
		} else {
			mTaskIndex = -1;
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_TASK_INDEX, mTaskIndex);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		attach();
	}

	@SuppressWarnings("unchecked")
	private <D extends AsyncDialogFragment> void attach() {
		PersistentAsyncTask<D> task = getTask();
		if (task != null) {
			task.attachDialog((D) this);
		}
	}

	protected void finish() {
		dismiss();
	}

	@SuppressWarnings("unchecked")
	protected <D extends AsyncDialogFragment> PersistentAsyncTask<D> getTask() {
		return (PersistentAsyncTask<D>) PersistentAsyncTask.getTask(mTaskIndex);
	}

}
