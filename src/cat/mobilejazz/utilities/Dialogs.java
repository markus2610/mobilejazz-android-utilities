package cat.mobilejazz.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.app.FragmentActivity;

public class Dialogs {

	private static OnCancelListener IGNORE_CANCEL = new OnCancelListener() {

		@Override
		public void onCancel(DialogInterface dialog) {
		}
	};

	public static void showErrorDialogAndFinish(final Activity context, int titleResId, int messageResId,
			int buttonLabelResId) {
		new AlertDialog.Builder(context).setMessage(messageResId).setTitle(titleResId)
				.setPositiveButton(buttonLabelResId, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						context.finish();
					}
				}).create().show();
	}

	public static void showErrorDialogAndCloseFragment(final FragmentActivity context, int titleResId,
			int messageResId, int buttonLabelResId, final String backStackName) {
		new AlertDialog.Builder(context).setMessage(messageResId).setTitle(titleResId)
				.setPositiveButton(buttonLabelResId, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						context.getSupportFragmentManager().popBackStack();
					}
				}).setOnCancelListener(IGNORE_CANCEL).create().show();
	}

	public static void showErrorDialog(Context context, int titleResId, int messageResId, int buttonLabelResId) {
		new AlertDialog.Builder(context).setMessage(messageResId).setTitle(titleResId)
				.setPositiveButton(buttonLabelResId, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				}).create().show();
	}

	public static void showErrorDialog(Context context, int titleResId, String message, int buttonLabelResId) {
		new AlertDialog.Builder(context).setMessage(message).setTitle(titleResId)
				.setPositiveButton(buttonLabelResId, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				}).create().show();
	}

}
