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
public abstract class PersistentAsyncTask<Params, Progress, Result, D extends AsyncDialogFragment> extends
		AsyncTask<Params, Progress, Result> {

	private static int sCurrentIndex = 0;
	private static SparseArray<PersistentAsyncTask<?, ?, ?, ?>> sTasks = new SparseArray<PersistentAsyncTask<?, ?, ?, ?>>();

	private static int getNextIndex() {
		return sCurrentIndex++;
	}

	static PersistentAsyncTask<?, ?, ?, ?> getTask(int index) {
		return sTasks.get(index);
	}

	private D mFragment;
	private int mIndex;

	private FragmentManager mFragmentManager;

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

	public PersistentAsyncTask(D dialog, FragmentManager fragmentManager) {
		mFragment = dialog;
		if (mFragment.getArguments() == null) {
			mFragment.setArguments(new Bundle());
		}
		mFragmentManager = fragmentManager;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mIndex = getNextIndex();
		sTasks.put(mIndex, this);

		if (mFragment != null) {
			mFragment.getArguments().putInt(AsyncDialogFragment.KEY_TASK_INDEX, mIndex);
			mFragment.show(mFragmentManager, "cat.mobilejazz.utilities.concurrency.PersistentAsyncTask-" + mIndex);
		}

		// release as soon as possible:
		mFragmentManager = null;
	}

	private void release() {
		sTasks.remove(mIndex);
		mFragmentManager = null;
		mFragment = null;
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		release();
	}

	@Override
	protected void onPostExecute(Result result) {
		super.onPostExecute(result);
		if (mFragment != null)
			mFragment.dismiss();
		release();
	}

}
