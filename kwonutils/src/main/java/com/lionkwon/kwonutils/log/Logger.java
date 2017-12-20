package com.lionkwon.kwonutils.log;

import android.util.Log;

public class Logger {
	private static String TAG = "kwon";
	private static boolean LOG = true;

	public static void setTag(String tag){
		TAG = tag;
	}
	public static void setLOG(boolean log){
		LOG = log;
	}

	public static void error(Exception e) {
		if(LOG){
			StackTraceElement[] ste = e.getStackTrace();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ste.length; i++) {
				sb.append(ste[i].toString() + "\n");
			}
			Log.e(TAG, e.toString());
			Log.e(TAG, sb.toString());
		}
	}

	public static void error(String message) {
		if(LOG){
			Log.e(TAG, message);
		}
	}
	public static void error(String message, Exception e) {
		if(LOG){
			Log.e(TAG, message, e);
			StackTraceElement[] ste = e.getStackTrace();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ste.length; i++) {
				sb.append(ste[i].toString() + "\n");
			}
			Log.e(TAG, message+"::"+e.toString());
			Log.e(TAG, sb.toString());
		}
	}

	public static void warn(String message) {
		if(LOG){
			Log.w(TAG, message);
		}
	}

	public static void info(String message) {
		if(LOG){
			Log.w(TAG, message);
		}
	}

	public static void debug(String message) {
		if(LOG){
			Log.d(TAG, message);
		}
	}
}
