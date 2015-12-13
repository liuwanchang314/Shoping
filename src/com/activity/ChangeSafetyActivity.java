package com.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 上午11:24:22
 * 修改安全信息界面
 */  
@SuppressLint("NewApi")
public class ChangeSafetyActivity extends Activity {
	
	private ImageView mBack,mHome;
	private TextView mQuestion;//问题按钮
	private EditText mAnswer;//答案
	private EditText mNewpw;//新密码
	private EditText mSpd;//确认密码
	private TextView mTG;//提交
	private ActionBar actionbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changesafety);
		initview();
		
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-11 上午11:43:52
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		actionbar=getActionBar();
		actionbar.hide();
		mBack=(ImageView) findViewById(R.id.changesafety_back);
		mHome=(ImageView) findViewById(R.id.changesafety_home);
		mQuestion=(TextView) findViewById(R.id.changesafety_wentianniu);
		mAnswer=(EditText) findViewById(R.id.changesafety_daan);
		mNewpw=(EditText) findViewById(R.id.changesafety_newpaypassword);
		mSpd=(EditText) findViewById(R.id.changesafety_argainpaypassword);
		mTG=(TextView) findViewById(R.id.changesafety_go);
	}

}
