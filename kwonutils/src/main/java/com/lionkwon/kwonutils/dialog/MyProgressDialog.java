package com.lionkwon.kwonutils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.lionkwon.kwonutils.R;

public class MyProgressDialog extends Dialog { 

	public static MyProgressDialog show(Context context, CharSequence title, CharSequence message) { 
		return show(context, title, message, false); 
	} 

	public static MyProgressDialog show(Context context, CharSequence title, CharSequence message, boolean indeterminate) { 
		return show(context, title, message, indeterminate, false, null); 
	} 

	public static MyProgressDialog show(Context context, CharSequence title, CharSequence message, boolean indeterminate, boolean cancelable) { 
		return show(context, title, message, indeterminate, cancelable, null); 
	} 

	public static MyProgressDialog show(Context context, CharSequence title, CharSequence message, boolean indeterminate, 
			boolean cancelable, OnCancelListener cancelListener) { 
		MyProgressDialog dialog = new MyProgressDialog(context); 
		dialog.setTitle(title); 
		dialog.setCancelable(cancelable); 
		dialog.setOnCancelListener(cancelListener); 
		/* The next line will add the ProgressBar to the dialog. */ 
//		dialog.addContentView(new ProgressBar(context), new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//		dialog.setIndeterminateDrawable(context.getResources().getDrawable(R.anim.loadinganimation));
		dialog.show(); 

		return dialog; 
	} 

	public MyProgressDialog(Context context) { 
		super(context, R.style.NewDialog); 
	} 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	/**
	 * 사용법
	 * 
	 *		 		 dialog = MyProgressDialog.show(MainActivity.this,"","",true,true,null);
	 * 
	 *				  if (dialog!=null) 
	 *						 dialog.dismiss();	
	 */
}
