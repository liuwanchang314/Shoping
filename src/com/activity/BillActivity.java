package com.activity;

import android.app.Activity;
import android.graphics.Color;
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
	private LinearLayout mZengzhi;//增值切换部分
	private LinearLayout mPutong;//普通切换部分
	private TextView mTvgeren;//个人按钮
	private TextView mTvdanwei;//单位按钮
	private ImageView mImgeren;//个人图标
	private ImageView mIndanwei;//单位图标
	private RelativeLayout mDanweimingchen;//单位名称部分
	
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
					mZengzhi.setVisibility(View.GONE);
					mPutong.setVisibility(View.GONE);
					mchoiceis=false;
					
				}else{
					mChoise.setBackgroundResource(R.drawable.gougou);
					mFapiaoFenlei.setVisibility(View.VISIBLE);
					mchoiceis=true;
				}
			}
		});
		//当普通或者增值发票被点击的时候，显示其相对应的界面发票被点击的时候
		mTvPTtv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//首先隐藏相对的界面
				mImPTim.setBackgroundResource(R.drawable.gougou);
				mImZZim.setBackgroundResource(R.drawable.quanquan);
				mTvPTtv.setTextColor(Color.RED);
				mTvZZtv.setTextColor(Color.BLACK);
				mZengzhi.setVisibility(View.GONE);
				mPutong.setVisibility(View.VISIBLE);
			}
		});
		mTvZZtv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mImPTim.setBackgroundResource(R.drawable.quanquan);
				mImZZim.setBackgroundResource(R.drawable.gougou);
				mTvPTtv.setTextColor(Color.BLACK);
				mTvZZtv.setTextColor(Color.RED);
				mPutong.setVisibility(View.GONE);
				mZengzhi.setVisibility(View.VISIBLE);
				
			}
		});
		//个人被点击，单位图标变暗，显示个人部分
		mTvgeren.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mImgeren.setBackgroundResource(R.drawable.gougou);
				mTvgeren.setTextColor(Color.RED);
				mTvdanwei.setTextColor(Color.BLACK);	
				mIndanwei.setBackgroundResource(R.drawable.quanquan);
				mDanweimingchen.setVisibility(View.GONE);
			}
		});
		mTvdanwei.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mImgeren.setBackgroundResource(R.drawable.quanquan);
				mTvgeren.setTextColor(Color.BLACK);
				mTvdanwei.setTextColor(Color.RED);	
				mIndanwei.setBackgroundResource(R.drawable.gougou);
				mDanweimingchen.setVisibility(View.VISIBLE);
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
		mZengzhi=(LinearLayout) findViewById(R.id.bill_yincang_zengzhi);
		mPutong=(LinearLayout) findViewById(R.id.bill_yincang_putong);
		mTvgeren=(TextView) findViewById(R.id.bill_layout_geren_tv);
		mTvdanwei=(TextView) findViewById(R.id.bill_layout_danwei_tv);
		mIndanwei=(ImageView) findViewById(R.id.bill_layout_danwei_im);
		mImgeren=(ImageView) findViewById(R.id.bill_layout_geren_im);
		
		mDanweimingchen=(RelativeLayout) findViewById(R.id.bill_layout_danweimingcheng);
	}
	

}
