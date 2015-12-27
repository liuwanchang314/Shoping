package com.other;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;

public class InternerIsConnection {
	
	 public  static boolean network(ConnectivityManager manager,final Context context) {
          boolean flag = false;
          //�ж����������Ƿ��������û��������ʾ����
          if (manager.getActiveNetworkInfo() != null) {
                  flag = manager.getActiveNetworkInfo().isAvailable();
          }
          if (!flag) {
                  AlertDialog.Builder normalDia=new AlertDialog.Builder(context); 
          normalDia.setTitle("��ʾ:"); 
          normalDia.setMessage("��������ʧ�ܣ���������������"); 
           
          normalDia.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { 
              public void onClick(DialogInterface dialog, int which) { 
                      
              } 
          }); 
          normalDia.setNegativeButton("��������", new DialogInterface.OnClickListener() { 
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
	  
