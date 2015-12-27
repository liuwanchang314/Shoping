package com.other;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;

public class InternerIsConnection {
	
	 public  static boolean network(ConnectivityManager manager,final Context context) {
          boolean flag = false;
          //判断网络连接是否开启，如果没开启会提示设置
          if (manager.getActiveNetworkInfo() != null) {
                  flag = manager.getActiveNetworkInfo().isAvailable();
          }
          if (!flag) {
                  AlertDialog.Builder normalDia=new AlertDialog.Builder(context); 
          normalDia.setTitle("提示:"); 
          normalDia.setMessage("网络连接失败，请重新链接网络"); 
           
          normalDia.setPositiveButton("确定", new DialogInterface.OnClickListener() { 
              public void onClick(DialogInterface dialog, int which) { 
                      
              } 
          }); 
          normalDia.setNegativeButton("设置网络", new DialogInterface.OnClickListener() { 
              @Override 
              public void onClick(DialogInterface dialog, int which) { 
                      context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
              } 
          }); 
          normalDia.create().show();
          } else {
                
          }
		return flag;
  } 
}
	  
