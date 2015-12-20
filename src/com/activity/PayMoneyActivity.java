package com.activity;

import java.util.List;

import com.adapter.PayMoneyAdatper;
import com.bean.BuyCartBean;
import com.customview.Mylistview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
	private Mylistview mlistview;
	private LinearLayout mBillInfo;//发票信息
	private TextView mBillInfoTextviw;
	private TextView mFapiao;
	private TextView shangjiafahuo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_paymoney);
		initview();
		Intent intent=getIntent();
		List<BuyCartBean> list=(List<BuyCartBean>) getIntent().getSerializableExtra("list");
		Log.i("现在传递过来了多少数据",list.size()+"");
		//准备adapter
		PayMoneyAdatper adapter=new PayMoneyAdatper(list, PayMoneyActivity.this);
		mlistview.setAdapter(adapter);
		
		mBillInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(PayMoneyActivity.this,BillActivity.class);
				startActivityForResult(intent, 4);
			}
		});
		shangjiafahuo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shangjiafahuo.setTextColor(Color.RED);
				
				Intent intents=new Intent(PayMoneyActivity.this,ShangJiaSendGoodsActivity.class);
				intents.putExtra("mode",shangjiafahuo.getText().toString());
				startActivityForResult(intents, 3);
			}
		});
	}

	@SuppressLint("CutPasteId")
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
		mlistview=(Mylistview) findViewById(R.id.paymoney_activity_listview);
		mBillInfo=(LinearLayout) findViewById(R.id.pay_bill);
		mBillInfoTextviw=(TextView) findViewById(R.id.paymoney_activity_tv_fapiaoxinxi);
		mFapiao=(TextView) findViewById(R.id.paymoney_activity_tv_fapiaoxinxi);
		shangjiafahuo=(TextView) findViewById(R.id.paymoney_activity_tv_shangjiafahuo);
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
		}else if(resultCode==4){
			//说明是从发票界面传递过来的
			String title=data.getStringExtra("title");
			mFapiao.setText(title);
		}else if(resultCode==3){
			//说明是从商家发货界面过来的数据
		}
	}

}
