package com.alljf.jf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.Application.SysApplication;
import com.alljf.jf.R;

public class activity_qidongye extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_qidongye);
		SysApplication.getInstance().addActivity(this);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent=new Intent(activity_qidongye.this,MainActivity.class);
				startActivity(intent);
			}
		}).start();
		
	}

}
