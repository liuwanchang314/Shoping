package com.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class FileUtils {

	public static void savePayInfo(Context context,String sfk,String orderid){
		SharedPreferences mySharedPreferences= context.getSharedPreferences("pay", 
				Activity.MODE_PRIVATE); 
				SharedPreferences.Editor editor = mySharedPreferences.edit(); 
				editor.putString("sfk", sfk); 
				editor.putString("orderid", orderid); 
				editor.commit(); 
	}
	
	public static String getSFKAndClear(Context context){
		SharedPreferences sharedPreferences= context.getSharedPreferences("PAY", 
				Activity.MODE_PRIVATE); 
				String sfk =sharedPreferences.getString("sfk", ""); 
				SharedPreferences.Editor editor = sharedPreferences.edit(); 
				editor.putString("sfk", ""); 
				editor.commit();
		return sfk;
	}
	public static String getOrderidAndClear(Context context){
		SharedPreferences sharedPreferences= context.getSharedPreferences("PAY", 
				Activity.MODE_PRIVATE); 
				String orderid =sharedPreferences.getString("orderid", ""); 
				SharedPreferences.Editor editor = sharedPreferences.edit(); 
				editor.putString("orderid", ""); 
				editor.commit();
		return orderid;
	}
	public static void clearPayInfo(Context context){
		SharedPreferences mySharedPreferences= context.getSharedPreferences("pay", 
				Activity.MODE_PRIVATE); 
				SharedPreferences.Editor editor = mySharedPreferences.edit(); 
				editor.putString("sfk", ""); 
				editor.putString("orderid", ""); 
				editor.commit(); 
	}
}
