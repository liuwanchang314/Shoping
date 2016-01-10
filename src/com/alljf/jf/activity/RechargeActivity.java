package com.alljf.jf.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.alljf.jf.R;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-7 下午9:59:40
 * 充值界面
 * 
 */  
public class RechargeActivity extends Activity implements OnClickListener{
	private ActionBar actionbar;
	
	private ImageView mback;//返回
	private ImageView mhome;//主页
	private EditText mJine;//输入的金额
	private TextView mCurrentPrice;//当前金额
	private TextView mcurrentrechange;//当前充值金额
	private TextView mPayclass;//支付方式
	private ImageView mZzhifubao;//支付宝
	private RelativeLayout mBgzhifubao;//支付宝需改变背景
	private ImageView mWeixin;//微信
	private RelativeLayout mBgweixin;//微信需改变北京
	private TextView mPay;//确认支付
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_recharge);
		SysApplication.getInstance().addActivity(this);
		initview();
	}
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mback=(ImageView) findViewById(R.id.rechrageactivity_imageView_callback);
		mback.setOnClickListener(this);
		mhome=(ImageView) findViewById(R.id.rechrageactivity_imageView_home);
		mhome.setOnClickListener(this);
		mJine=(EditText) findViewById(R.id.rechrageactivity_edittext_jine);
		mCurrentPrice=(TextView) findViewById(R.id.rechrageactivity_current_jine);
		mcurrentrechange=(TextView) findViewById(R.id.rechrageactivity_textview_jine);
		mPayclass=(TextView) findViewById(R.id.rechrageactivity_textview_payclass);
		mZzhifubao=(ImageView) findViewById(R.id.rechrageactivity_imageview_zhifubao);
		mZzhifubao.setOnClickListener(this);
		mBgzhifubao=(RelativeLayout) findViewById(R.id.rechrageactivity_bg_zhifubao);
		mWeixin=(ImageView) findViewById(R.id.rechrageactivity_imageview_weixin);
		mWeixin.setOnClickListener(this);
		mBgweixin=(RelativeLayout) findViewById(R.id.rechrageactivity_bg_weixin);
		mPay=(TextView) findViewById(R.id.rechrageactivity_textview_querenzhifu);
		mPay.setOnClickListener(this);
		mBgzhifubao.setBackgroundColor(Color.WHITE);
		mBgweixin.setBackgroundColor(Color.WHITE);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rechrageactivity_imageView_callback://返回
			RechargeActivity.this.finish();
			break;
		case R.id.rechrageactivity_imageView_home://主页
			RechargeActivity.this.finish();
			startActivity(new Intent(RechargeActivity.this,MainActivity.class));
			break;
		case R.id.rechrageactivity_imageview_zhifubao://支付宝
			//进行背景填充
			mBgzhifubao.setBackgroundColor(Color.GRAY);
			mBgweixin.setBackgroundColor(Color.WHITE);
			//改变支付说明
			mPayclass.setText("你选择了支付宝支付");
			break;
		case R.id.rechrageactivity_imageview_weixin://威信
			mBgweixin.setBackgroundColor(Color.GRAY);
			mBgzhifubao.setBackgroundColor(Color.WHITE);
			mPayclass.setText("你选择了微信支付");
			break;
		case R.id.rechrageactivity_textview_querenzhifu://支付
			Toast.makeText(RechargeActivity.this,"支付", Toast.LENGTH_SHORT).show();
			mBgzhifubao.setBackgroundColor(Color.WHITE);
			mBgweixin.setBackgroundColor(Color.WHITE);
			break;
			

		default:
			break;
		}
	}

}
