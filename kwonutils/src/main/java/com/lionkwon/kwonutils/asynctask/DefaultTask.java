package com.lionkwon.kwonutils.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

public class DefaultTask extends AsyncTask<String, String, String> {

	Context context;
	public String loadingMsg = "Loading...";
	boolean isProgress = true;

	/**
	 * 로딩 다이얼로그
	 */
	public final int PROGRESS_OPEN = 1;
	public final int PROGRESS_CLOSE = 2;
	private ProgressDialog loadingDialog;

	public DefaultTask(Context context) {
		this.context = context;
	}

	public void setLoadingMsg(String loadingMsg) {
		this.loadingMsg = loadingMsg;
	}
	public void setProgress(boolean value){
		this.isProgress = value;
	}

	public final Handler loadingHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case PROGRESS_OPEN:
				try {
					String	msg_str = (String) msg.obj;
					loadingDialog = ProgressDialog.show(context, "",msg_str, true, true, null);
					loadingDialog.setCancelable(true);
				} catch (Exception e) {
				}
				break;
			case PROGRESS_CLOSE:
				try {
					if(loadingDialog != null && loadingDialog.isShowing()){
						loadingDialog.dismiss();
					}
				} catch (Exception e) {
				}
				break;
			}
		}
	};

	@Override
	protected void onPostExecute(String result) {
		if(isProgress)
			loadingHandler.sendEmptyMessage(PROGRESS_CLOSE);
	}

	@Override
	protected void onPreExecute() {
		Message message = new Message();
		message.what =  PROGRESS_OPEN;
		message.obj = loadingMsg;
		if(isProgress)
			loadingHandler.sendMessage(message);
	}

	@Override
	protected String doInBackground(String... params) {
		return null;
	}
}