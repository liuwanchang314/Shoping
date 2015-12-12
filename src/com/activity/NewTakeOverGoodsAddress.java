package com.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 下午1:44:33
 * 新建收货地址界面
 */  

public class NewTakeOverGoodsAddress extends Activity {
	
	private ImageView mBack,mHome;
	private EditText mName;
	private EditText mPhone;
	private EditText mYoubian;
	private RelativeLayout mSSX;//省市县
	private RelativeLayout mJD;//街道
	private TextView mSave;//保存
	private ActionBar actionbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newtaskeovergoodsaddress);
		initview();
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-11 下午2:16:13
	 */  
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		actionbar=getActionBar();
		actionbar.hide();
		mBack=(ImageView) findViewById(R.id.newtakeoveraddress_back);
		mHome=(ImageView) findViewById(R.id.newtakeoveraddress_home);
		mName=(EditText) findViewById(R.id.newtakeoveraddress_name);
		mPhone=(EditText) findViewById(R.id.newtakeoveraddress_phone);
		mYoubian=(EditText) findViewById(R.id.newtakeoveraddress_youzhenbianma);
		mSSX=(RelativeLayout) findViewById(R.id.newtakeoveraddress_shengshixian);
		mJD=(RelativeLayout) findViewById(R.id.newtakeoveraddress_jiedao);
		mSave=(TextView) findViewById(R.id.newtakeoveraddress_save);
	}

}
