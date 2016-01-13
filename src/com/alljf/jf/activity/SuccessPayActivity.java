package com.alljf.jf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.Application.SysApplication;
import com.alljf.jf.R;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 下午4:15:06
 * 支付成功界面
 */  
public class SuccessPayActivity extends Activity {
	
	
	private ImageView mback,mhome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_successpay);
		SysApplication.getInstance().addActivity(this);
		initview();
	}

	/**
	 * @2016-1-7下午10:20:07
	 */
	private void initview() {
		mback=(ImageView) findViewById(R.id.successpay_back);
		mback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SuccessPayActivity.this.finish();
			}
		});
		mhome=(ImageView) findViewById(R.id.successpay_home);
		mhome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SuccessPayActivity.this.finish();
				startActivity(new Intent(SuccessPayActivity.this,MainActivity.class));
			}
		});
		
	}

}
