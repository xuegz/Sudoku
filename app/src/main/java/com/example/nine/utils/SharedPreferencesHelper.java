package com.example.nine.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
	private SharedPreferences sp;

	public SharedPreferencesHelper(Context context) {
		super();
		sp = context.getSharedPreferences("info",context.MODE_PRIVATE);
	}
	
	//保存string
	public void putString(String key,String value){
		sp.edit().putString(key, value).commit();
	}
	//获取string
	public String getString(String key,String defValue){
		return sp.getString(key, defValue);
	}
	
}
