package com.lionkwon.kwonutils.transaction;


public interface TransactionListener
{
	public void onTransactionResult(String result, int position, Object msg);
}

/**
 * 사용법
 * 
 * 	
 * 
     TransactionListener result = new TransactionListener() {
			@Override
			public void onTransactionResult(String result, int position, TABLE_MSG msg) {
	
			}
	};
	
			MsgSendTask task = new MsgSendTask(context, result);
				task.setProgress(false);
 * 
 */
