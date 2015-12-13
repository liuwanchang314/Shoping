package com.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.drm.DrmStore.Action;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 下午3:52:55
 * 提现
 */  
public class WithDrawAcivity extends Activity implements OnClickListener{
	
	private ImageView mBack;
	private ImageView mHome;//
	private TextView mCurrent;//当前余额
	private EditText mMoney;//要提现的金额
	private EditText mBankName;//银行名称
	private EditText mName;//开户名成
	private EditText mBranchName;//分行名称
	private EditText mNumber;//卡号
	private TextView mSure;//确认提现
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_withdraw);
		initview();
	}
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.withdraw_back);
		mBack.setOnClickListener(this);
		mHome=(ImageView) findViewById(R.id.withdraw_home);
		mHome.setOnClickListener(this);
		mCurrent=(TextView) findViewById(R.id.withdraw_ketixianjine);
		mMoney=(EditText) findViewById(R.id.withdraw_yaotixianjine);
		mBankName=(EditText) findViewById(R.id.withdraw_yinhangmingcheng);
		mName=(EditText) findViewById(R.id.withdraw_xingming);
		mBranchName=(EditText) findViewById(R.id.withdraw_kaihuhangmingcheng);
		mNumber=(EditText) findViewById(R.id.withdraw_yinhangzhanghao);
		mSure=(TextView) findViewById(R.id.withdraw_querentixian);
		mSure.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.withdraw_back:
			Toast.makeText(WithDrawAcivity.this,"返回",Toast.LENGTH_SHORT).show();
			break;
		case R.id.withdraw_home:
			Toast.makeText(WithDrawAcivity.this,"主页",Toast.LENGTH_SHORT).show();
			break;
		case R.id.withdraw_querentixian:
			Toast.makeText(WithDrawAcivity.this,"确认",Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

}
