package com.alljf.jf.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Application.SysApplication;
import com.alljf.jf.R;
/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 上午10:03:26
 * 确认收货界面
 * 需要操作的控件有如下
 * 1返回，2主页，3订单编号，4密码，5确认
 */  
public class ConfirmationTakeGoodsActivity extends Activity {
	
	
	private ImageView mBack;
	private ImageView mHome;
	private TextView mNumber;//订单编号
	private EditText mPassword;//密码
	private TextView mSure;//确认
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_confirmationtakegoods);
		SysApplication.getInstance().addActivity(this);
		initview();
	}
	
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-9 上午10:41:30
	 * 控件初始化
	 */  

	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.confirmationtakegoods_back);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ConfirmationTakeGoodsActivity.this.finish();
			}
		});
		
		mHome=(ImageView) findViewById(R.id.confirmationtakegoods_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ConfirmationTakeGoodsActivity.this.finish();
				startActivity(new Intent(ConfirmationTakeGoodsActivity.this,MainActivity.class));
			}
		});
		mNumber=(TextView) findViewById(R.id.confirmationtakegoods_dingdanbianhao);
		mPassword=(EditText) findViewById(R.id.confirmationtakegoods_mimakuang);
		mSure=(TextView) findViewById(R.id.confirmationtakegoods_queren);
	}
	
	

}
