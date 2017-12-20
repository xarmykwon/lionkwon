package com.lionkwon.kwonutils.preference;

import com.lionkwon.kwonutils.security.*;

import android.content.Context;
import android.content.SharedPreferences;

public class SecuritySharedPreference {

	private SharedPreferences prefs;
	private SharedPreferences.Editor editor;
	int Mode = Context.MODE_MULTI_PROCESS;

	/**
	 * 싱글턴 사용시
	 */
	private volatile static SecuritySharedPreference sp = null;
	public static SecuritySharedPreference getInstance(Context ctx, int mode){
		if(sp == null){
			synchronized (SecuritySharedPreference.class) {
				if(sp==null)
					sp = new SecuritySharedPreference(ctx, mode);
			}
		}
		return sp;
	}

	public static SecuritySharedPreference getInstance(Context ctx){
		if(sp == null){
			synchronized (SecuritySharedPreference.class) {
				if(sp==null)
					sp = new SecuritySharedPreference(ctx);
			}
		}
		return sp;
	}

	public SecuritySharedPreference(Context ctx) {
		prefs = ctx.getSharedPreferences("DB", Mode);
	}

	public SecuritySharedPreference(Context ctx, int mode) {
		Mode  = mode;
		prefs = ctx.getSharedPreferences("DB", Mode);
	}

	public String getString(String key){
		try {
			String temp = prefs.getString(TripleDes.encryption(key), "");
			if(temp.equalsIgnoreCase("")){
				return temp;
			}else{
				return TripleDes.decription(temp);
			}
		} catch (Exception e) {
			return prefs.getString(key, "");
		}
	}

	public boolean putString(String key, String value){
		editor = prefs.edit();
		try {
			if(value.equalsIgnoreCase("")){
				editor.putString(TripleDes.encryption(key), value);
			}else{
				editor.putString(TripleDes.encryption(key), TripleDes.encryption(value));
			}
		} catch (Exception e) {
			editor.putString(key, value);
		}
		return editor.commit();
	}

	public boolean putBoolean(String key, boolean value){
		editor = prefs.edit();
		try {
			editor.putBoolean(TripleDes.encryption(key), value);
		} catch (Exception e) {
			editor.putBoolean(key, value);
		}
		return editor.commit();
	}

	public boolean putInt(String key, int value){
		editor = prefs.edit();
		try {
			editor.putInt(TripleDes.encryption(key), value);
		} catch (Exception e) {
			editor.putInt(key, value);
		}
		
		return editor.commit();
	}

	public boolean getBoolean(String key){
		try {
			return prefs.getBoolean(TripleDes.encryption(key), false);
		} catch (Exception e) {
			return prefs.getBoolean(key, false);
		}
	}

	public boolean getBoolean(String key, boolean default_value){
		try {
			return prefs.getBoolean(TripleDes.encryption(key), default_value);
		} catch (Exception e) {
			return prefs.getBoolean(key, default_value);
		}
	}

	public int getInt(String key, int default_value){
		try {
			return prefs.getInt(TripleDes.encryption(key), default_value);
		} catch (Exception e) {
			return prefs.getInt(key, default_value);
		}
	}

}
