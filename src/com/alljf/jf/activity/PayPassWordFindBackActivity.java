package com.alljf.jf.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alljf.jf.R;


/**
 * @author JZKJ-LWC
 * @date : 2015-12-10 下午11:12:54
 * 支付密码找回界面
 */  
public class PayPassWordFindBackActivity extends Activity {
	
	private ImageView mBack,mHome;
	private EditText mPhone;//
	private EditText mYanzm;//yanzhengma
	private EditText mNewPassword;
	private EditText mSurewore;//确认密码
	private TextView mTijiao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_paypasswordfindback);
		initview();
	}

	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-11 上午10:56:49
	 */  
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.passwordfindback_back);
		mHome=(ImageView) findViewById(R.id.passwordfindback_home);
		mPhone=(EditText) findViewById(R.id.passwordfindback_phone);
		mYanzm=(EditText) findViewById(R.id.passwordfindback_yanzhengma);
		mNewPassword=(EditText) findViewById(R.id.passwordfindback_newpaypassword);
		mSurewore=(EditText) findViewById(R.id.passwordfindback_argainpaypassword);
		mTijiao=(TextView) findViewById(R.id.passwordfindback_go);
	}

}
