package com.lionkwon.kwonutils.fragment;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.os.*;

@SuppressLint("NewApi")
public class AlertDialogFragment extends DialogFragment {

	static Context context;

	public static AlertDialogFragment newInstance(Context contexts, String title) {
		context = contexts;
		AlertDialogFragment frag = new AlertDialogFragment();
		Bundle args = new Bundle();
		args.putString("title", title);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		String title = getArguments().getString("title");

		return new AlertDialog.Builder(getActivity())
//		.setIcon(R.drawable.sns_fail)
		.setTitle(title)
		.setPositiveButton("확인",
				new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				dismiss();
			}
		}
				)

				//		.setNegativeButton("취소",
				//				new DialogInterface.OnClickListener() {
				//			@Override
				//			public void onClick(DialogInterface dialog, int whichButton) {
				//				((Activity) context).finish();
				//			}
				//		}
				//		)
				.create();
	}
}