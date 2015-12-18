package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BillActivity extends Activity {
	
	private RelativeLayout mSureUserNewBill;//是否使用新发票
	private LinearLayout mFapiaoFenlei;//发票分类部分
	private ImageView mImPTim;//普通发票旁边的图标
	private TextView mTvPTtv;//普通发票按钮
	private ImageView mImZZim;//增值发票旁边
	private TextView mTvZZtv;//增值发票
	private boolean mchoiceis=false;
	private ImageView mChoise;//是否使用新发票图标
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_billinfo);
		initview();
		mSureUserNewBill.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//需要将发票分类显示出来
				if(mchoiceis){
					mChoise.setBackgroundResource(R.drawable.quanquan);
					mFapiaoFenlei.setVisibility(View.GONE);
					mchoiceis=false;
					
				}else{
					mChoise.setBackgroundResource(R.drawable.gougou);
					mFapiaoFenlei.setVisibility(View.VISIBLE);
					mchoiceis=true;
				}
			}
		});
	}
	
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-18 下午5:23:56
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		mSureUserNewBill=(RelativeLayout) findViewById(R.id.bill_shiyongxinfapiao);
		mFapiaoFenlei=(LinearLayout) findViewById(R.id.bill_yincang_fapiaofenlei);
		mImPTim=(ImageView) findViewById(R.id.bill_yincang_fapiaofenlei_pt_im);
		mTvPTtv=(TextView) findViewById(R.id.bill_yincang_fapiaofenlei_pt_tv);
		mImZZim=(ImageView) findViewById(R.id.bill_yincang_fapiaofenlei_zz_im);
		mTvZZtv=(TextView) findViewById(R.id.bill_yincang_fapiaofenlei_zz_tv);
		mChoise=(ImageView) findViewById(R.id.bill_checks);
	}
	

}
