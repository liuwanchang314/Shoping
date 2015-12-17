package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PayMoneyActivity extends Activity {
	
	private LinearLayout layout_shouhuodizhi;
	private TextView mName;
	private TextView mPhone;
	private TextView mAddress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_paymoney);
		initview();
	}

	private void initview() {
		// TODO Auto-generated method stub
		layout_shouhuodizhi=(LinearLayout) findViewById(R.id.pay_shouhuodizhi);
		layout_shouhuodizhi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(PayMoneyActivity.this,TakeGoodsAddressActivity.class);
				startActivityForResult(intent,1);
			}

		});
		mName=(TextView) findViewById(R.id.paymoney_activity_tv_shouhuorennanme);
		mPhone=(TextView) findViewById(R.id.paymoney_activity_tv_shouhuorendianhua);
		mAddress=(TextView) findViewById(R.id.paymoney_activity_tv_shouhuorenaddress);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==1){
			//说明值传递了过来
			mName.setText(data.getStringExtra("name"));
			mPhone.setText(data.getStringExtra("phone"));
			mAddress.setText(data.getStringExtra("xxaddress"));
		}
	}

}
