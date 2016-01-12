package com.alljf.jf.activity;

import com.alljf.jf.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-12 下午9:55:59 
 * 类说明 
 */
public class XuanzefuwuActivity extends Activity implements OnClickListener{
	
	private ImageView mBack,mHome;
	private RelativeLayout mTuikuantuihuo;
	private RelativeLayout mJintuikuan;
	private RelativeLayout mHuanhuo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_xuanzefuwu);
		initview();
	}
	/**
	 * 初始化
	 * @2016-1-12下午9:57:59
	 */
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.xuanzefuwu_back);
		mBack.setOnClickListener(this);
		mHome=(ImageView) findViewById(R.id.xuanzefuwu_home);
		mHome.setOnClickListener(this);
		mTuikuantuihuo=(RelativeLayout) findViewById(R.id.xuanzefuwu_tuikuantuihuo);
		mTuikuantuihuo.setOnClickListener(this);
		mJintuikuan=(RelativeLayout) findViewById(R.id.xuanzefuwu_jinjintuikuan);
		mJintuikuan.setOnClickListener(this);
		mHuanhuo=(RelativeLayout) findViewById(R.id.xuanzefuwu_huanhuo);
		mHuanhuo.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.xuanzefuwu_back:
			XuanzefuwuActivity.this.finish();
			break;
		case R.id.xuanzefuwu_home:
			XuanzefuwuActivity.this.finish();
			startActivity(new Intent(XuanzefuwuActivity.this,MainActivity.class));
			break;
		case R.id.xuanzefuwu_tuikuantuihuo:
			Intent intent=new Intent();
			intent.putExtra("fuwu","退款退货");
			XuanzefuwuActivity.this.setResult(1,intent);
			XuanzefuwuActivity.this.finish();
			break;
		case R.id.xuanzefuwu_jinjintuikuan:
			Intent intent2=new Intent();
			intent2.putExtra("fuwu","仅退款");
			XuanzefuwuActivity.this.setResult(1,intent2);
			XuanzefuwuActivity.this.finish();
			break;
		case R.id.xuanzefuwu_huanhuo:
			Intent intent3=new Intent();
			intent3.putExtra("fuwu","换货");
			XuanzefuwuActivity.this.setResult(1,intent3);
			XuanzefuwuActivity.this.finish();
			break;
		default:
			break;
		}
	}
	
	

}
