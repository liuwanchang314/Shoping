package com.activity;

import com.Extension.RoundImageView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 下午1:33:15
 * 个人资料界面
 */  

public class PersonDataActivity extends Activity implements OnClickListener{
	
	private ImageView mBack,mHome;
	private RoundImageView mHeadPic;//自定义圆形头像
	private TextView mPhone;//电话
	private TextView mEmail;//邮箱
	private LinearLayout mAnquan;//安全信息
	private LinearLayout mPayinfo;//支付
	private LinearLayout mCard;//名片
	private ActionBar actionbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_persondata);
		initview();
	}
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		actionbar=getActionBar();
		actionbar.hide();
		mBack=(ImageView) findViewById(R.id.persondata_back);
		mBack.setOnClickListener(this);
		mHome=(ImageView) findViewById(R.id.persondata_home);
		mHome.setOnClickListener(this);
		mHeadPic=(RoundImageView) findViewById(R.id.persondata_touxiang);
		mPhone=(TextView) findViewById(R.id.persondata_shoujihao);
		mEmail=(TextView) findViewById(R.id.persondata_dianziyouxiang);
		mAnquan=(LinearLayout) findViewById(R.id.persondata_anquanxinxi);
		mAnquan.setOnClickListener(this);
		mPayinfo=(LinearLayout) findViewById(R.id.persondata_zhifumima);
		mPayinfo.setOnClickListener(this);
		mCard=(LinearLayout) findViewById(R.id.persondata_mingpian);
		mCard.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.persondata_back://返回
			Toast.makeText(PersonDataActivity.this,"返回",Toast.LENGTH_SHORT).show();
			break;
		case R.id.persondata_home://zhuye 
			Toast.makeText(PersonDataActivity.this,"主页",Toast.LENGTH_SHORT).show();
			break;
		case R.id.persondata_anquanxinxi://安全
			Toast.makeText(PersonDataActivity.this,"安全",Toast.LENGTH_SHORT).show();
			startActivity(new Intent(PersonDataActivity.this, ChangeSafetyActivity.class));
			break;
		case R.id.persondata_zhifumima://密码
			Toast.makeText(PersonDataActivity.this,"密码",Toast.LENGTH_SHORT).show();
			startActivity(new Intent(PersonDataActivity.this,PayPassWordFindBackActivity.class ));
			break;
		case R.id.persondata_mingpian://名片
			Toast.makeText(PersonDataActivity.this,"名片",Toast.LENGTH_SHORT).show();
			startActivity( new Intent(PersonDataActivity.this,TwoDimensionCodeActivity.class));
			break;

		default:
			break;
		}
	}
	}


