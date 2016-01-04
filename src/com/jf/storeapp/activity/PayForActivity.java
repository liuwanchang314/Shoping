package com.jf.storeapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf.storeapp.R;


public class PayForActivity extends Activity implements OnClickListener {

	private ImageView mBack;
	private ImageView mHome;
	private TextView mMoneyNum;
	private TextView mOrderNum;
	private TextView mPeisongWay;
	private RelativeLayout MzhifuBG;
	private RelativeLayout McaifutongBG;
	private RelativeLayout MweixinBG;
	private ImageView mZhifubao;
	private ImageView mCaifutong;
	private ImageView mWeixin;
	private TextView mSureButton;
	private String payTAG="zfb";//用来记录当前用户选择了哪种支付方式
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_payfor);
		initview();
		//首先分析，需要从之前界面传递过来的数据有
		/**
		 * 1,需付款金额，2，订单号，3配送方式
		 * */
		Intent intent=getIntent();
		String price=intent.getStringExtra("price");
		String psfs=intent.getStringExtra("fhfs");
		String order=intent.getStringExtra("order");
		mMoneyNum.setText(price);
		mPeisongWay.setText(psfs);
		mOrderNum.setText(order);
		mZhifubao.setOnClickListener(this);
		mCaifutong.setOnClickListener(this);
		mWeixin.setOnClickListener(this);
		mSureButton.setOnClickListener(this);
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-21 下午9:13:05
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.payfor_back);
		mHome=(ImageView) findViewById(R.id.payfor_home);
		mMoneyNum=(TextView) findViewById(R.id.payfor_moneynum);
		mOrderNum=(TextView) findViewById(R.id.payfor_dingdanhao);
		mPeisongWay=(TextView) findViewById(R.id.payfor_peisongfangshi);
		MzhifuBG=(RelativeLayout) findViewById(R.id.payfor_zhifubao_bg_zhifubao);
		MzhifuBG.setBackgroundColor(Color.WHITE);
		mZhifubao=(ImageView) findViewById(R.id.payfor_zhifubao);
		McaifutongBG=(RelativeLayout) findViewById(R.id.payfor_caifutong_bg_caifutong);
		McaifutongBG.setBackgroundColor(Color.WHITE);
		mCaifutong=(ImageView) findViewById(R.id.payfor_caifutong);
		MweixinBG=(RelativeLayout) findViewById(R.id.payfor_weixin_bg_weixin);
		MweixinBG.setBackgroundColor(Color.WHITE);
		mWeixin=(ImageView) findViewById(R.id.payfor_weixin);
		mSureButton=(TextView) findViewById(R.id.payfor_querentijiao);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.payfor_zhifubao:
			payTAG="zfb";
			MzhifuBG.setBackgroundColor(Color.GRAY);
			McaifutongBG.setBackgroundColor(Color.WHITE);
			MweixinBG.setBackgroundColor(Color.WHITE);
			break;
		case R.id.payfor_caifutong:
			payTAG="cft";
			MzhifuBG.setBackgroundColor(Color.WHITE);
			McaifutongBG.setBackgroundColor(Color.GRAY);
			MweixinBG.setBackgroundColor(Color.WHITE);
			break;
		case R.id.payfor_weixin:
			payTAG="wx";
			MzhifuBG.setBackgroundColor(Color.WHITE);
			McaifutongBG.setBackgroundColor(Color.WHITE);
			MweixinBG.setBackgroundColor(Color.GRAY);
			break;
		case R.id.payfor_querentijiao:
			if(payTAG.equals("zfb")){
				//在这里进行相应的支付操作
				Log.i("当前选择了支付宝支付", payTAG);
			}else if(payTAG.equals("cft")){
				//财付通的操作
				Log.i("当前选择了财付通支付", payTAG);
			}else if(payTAG.equals("wx")){
				//微信操作
				Log.i("当前选择了微信支付", payTAG);
			}
				
			break;

		default:
			break;
		}
	}

}
