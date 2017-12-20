package com.lionkwon.kwonutils.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

	private SharedPreferences prefs;
	private SharedPreferences.Editor editor;
	int Mode = Context.MODE_PRIVATE;

	/**
	 * 싱글턴 사용시
	 */
	private volatile static SharedPreference sp = null;
	public static SharedPreference getInstance(Context ctx, int mode){
		if(sp == null){
			synchronized (SharedPreference.class) {
				if(sp==null)
					sp = new SharedPreference(ctx, mode);
			}
		}
		return sp;
	}
	
	public static SharedPreference getInstance(Context ctx){
		if(sp == null){
			synchronized (SharedPreference.class) {
				if(sp==null)
					sp = new SharedPreference(ctx);
			}
		}
		return sp;
	}
	
	public SharedPreference(Context ctx) {
		prefs = ctx.getSharedPreferences("DB", Mode);
	}
	
	public SharedPreference(Context ctx, int mode) {
		Mode  = mode;
		prefs = ctx.getSharedPreferences("DB", Mode);
	}

	public String getString(String key){
		return prefs.getString(key, "");
	}

	public boolean putString(String key, String value){
		editor = prefs.edit();
		editor.putString(key, value);
		return editor.commit();
	}

	public boolean putBoolean(String key, boolean value){
		editor = prefs.edit();
		editor.putBoolean(key, value);
		return editor.commit();
	}
	
	public boolean putInt(String key, int value){
		editor = prefs.edit();
		editor.putInt(key, value);
		return editor.commit();
	}
	
	public boolean getBoolean(String key){
		return prefs.getBoolean(key, false);
	}
	
	public boolean getBoolean(String key, boolean default_value){
		return prefs.getBoolean(key, default_value);
	}
	
	public int getInt(String key, int default_value){
		return prefs.getInt(key, default_value);
	}
	
	


}
