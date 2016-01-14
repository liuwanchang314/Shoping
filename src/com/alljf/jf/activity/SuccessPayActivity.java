package com.alljf.jf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.Application.SysApplication;
import com.alljf.jf.R;
import com.alljf.jf.R.string;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 下午4:15:06
 * 支付成功界面
 */  
public class SuccessPayActivity extends Activity implements OnClickListener{
	
	
	private ImageView mback,mhome;
	private String price;
	private String orderid;
	private TextView mShifukuan;
	private TextView mName;
	private TextView mPhone;
	private TextView mAddress;
	private TextView mDingdanxiangqing;
	private TextView mFanhuizhuye;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_successpay);
		SysApplication.getInstance().addActivity(this);
		Intent intent=getIntent();
		price=intent.getStringExtra("sfk");
		orderid=intent.getStringExtra("orderid");
		initview();
		//订单不合适，暂时无法获取定单详情数据
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
		
		mShifukuan=(TextView) findViewById(R.id.successpay_price);
		mName=(TextView) findViewById(R.id.successpay_name);
		mPhone=(TextView) findViewById(R.id.success_dianhua);
		mAddress=(TextView) findViewById(R.id.successpay_address);
		mDingdanxiangqing=(TextView) findViewById(R.id.successpay_dingdanxiangqing);
		mDingdanxiangqing.setOnClickListener(this);
		mFanhuizhuye=(TextView) findViewById(R.id.successpay_fanhuizhuye);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.successpay_dingdanxiangqing:
			Intent intent=new Intent(SuccessPayActivity.this,DingDanXQActivity.class);
			break;

		default:
			break;
		}
	}

}
