package cat.mobilejazz.utilities.concurrency;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;

/**
 * An implementation of {@link AsyncTask} that survives an activity recreation
 * and can be attached to a fragment.
 * 
 * @author Hannes Widmoser
 * 
 */
public abstract class PersistentAsyncTask<D extends AsyncDialogFragment> extends AsyncTask<Void, Integer, Exception> {

	private static int sCurrentIndex = 0;
	private static SparseArray<PersistentAsyncTask<? extends AsyncDialogFragment>> sTasks = new SparseArray<PersistentAsyncTask<? extends AsyncDialogFragment>>();

	private static int getNextIndex() {
		return sCurrentIndex++;
	}

	static PersistentAsyncTask<? extends AsyncDialogFragment> getTask(int index) {
		return sTasks.get(index);
	}

	private D mFragment;
	private int mIndex;

	void attachDialog(D fragment) {
		mFragment = fragment;
		if (mFragment.getDialog() != null) {
			mFragment.getDialog().setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					cancel(true);
				}
			});
		}
	}

	protected D getDialogFragment() {
		return mFragment;
	}

	protected Dialog getDialog() {
		return (mFragment != null) ? mFragment.getDialog() : null;
	}

	/**
	 * You must expect that this method may return {@code null} at any time
	 * because it accesses the fragment's activity. This activity is only
	 * accessible when the fragment is set and attached to it's activity.
	 * 
	 * @return the {@link Activity} that is attached to the fragment.
	 */
	protected Activity getActivity() {
		return (mFragment != null) ? mFragment.getActivity() : null;
	}

	public PersistentAsyncTask() {
	}

	/**
	 * Shows the given dialog and associates it with this task. If
	 * {@code executeImmediately} is set to {@code true} the task is immediately
	 * executed. If not, executing the task is up to the dialog (e.g. depending
	 * on whether the user pressed the positive or the negative button.
	 * 
	 * @param dialog
	 *            The dialog that is to be shown.
	 * @param fragmentManager
	 *            A {@link FragmentManager}.
	 * @param executeImmediately
	 *            A boolean flag to indicate whether to immediately execute the
	 *            task.
	 * @param params
	 *            Optional params to pass to {@link #execute(Object...)}
	 */
	public void start(D dialog, FragmentManager fragmentManager, String tag, boolean executeImmediately) {
		if (dialog != null && fragmentManager != null) {
			mIndex = getNextIndex();
			sTasks.put(mIndex, this);

			mFragment = dialog;
			if (mFragment.getArguments() == null) {
				mFragment.setArguments(new Bundle());
			}
			if (mFragment != null) {
				mFragment.getArguments().putInt(AsyncDialogFragment.KEY_TASK_INDEX, mIndex);
				if (tag == null) {
					tag = "cat.mobilejazz.utilities.concurrency.PersistentAsyncTask-" + mIndex;
				}
				mFragment.show(fragmentManager, tag);
			}
		}
		if (executeImmediately) {
			execute();
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	private void release() {
		sTasks.remove(mIndex);
		mFragment = null;
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		release();
	}

	@Override
	protected void onPostExecute(Exception result) {
		super.onPostExecute(result);
		if (mFragment != null)
			mFragment.finish();
		release();
	}

}
