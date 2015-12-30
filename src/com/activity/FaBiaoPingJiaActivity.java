package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015-12-30 下午10:04:35 
 * 类说明   发表评价界面
 */
public class FaBiaoPingJiaActivity extends Activity {
	private ImageView mBack;
	private ImageView mHome;
	private ImageView mGoodsImage;
	private EditText mPingjia;
	private CheckBox mBBPF1,mBBPF2,mBBPF3,mBBPF4,mBBPF5;//宝贝评分
	private CheckBox mBBYMSXF1,mBBYMSXF2,mBBYMSXF3,mBBYMSXF4,mBBYMSXF5;//宝贝与描述相符
	private CheckBox mMJDFWTD1,mMJDFWTD2,mMJDFWTD3,mMJDFWTD4,mMJDFWTD5;//卖家的服务态度
	private CheckBox mMJDFHSD1,mMJDFHSD2,mMJDFHSD3,mMJDFHSD4,mMJDFHSD5;//卖家的发货速度
	private TextView mFabiaopingjia;//发表评价
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pingjia);
		initview();
	}
	/**
	 * @2015-12-30下午10:11:38
	 * @author JZKJ-LWC
	 * 初始化控件
	 */
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.pingjia_back);
		mHome=(ImageView) findViewById(R.id.pingjia_home);
		mGoodsImage=(ImageView) findViewById(R.id.pingjia_goodsimage);
		mPingjia=(EditText) findViewById(R.id.pingjia_pingjia);
		mBBPF1=(CheckBox) findViewById(R.id.checkBox_baobeipingjia1);
		mBBPF2=(CheckBox) findViewById(R.id.checkBox_baobeipingjia2);
		mBBPF3=(CheckBox) findViewById(R.id.checkBox_baobeipingjia3);
		mBBPF4=(CheckBox) findViewById(R.id.checkBox_baobeipingjia4);
		mBBPF5=(CheckBox) findViewById(R.id.checkBox_baobeipingjia5);
		mBBYMSXF1=(CheckBox) findViewById(R.id.checkBox_baobeiyumiaoshuiangfu1);
		mBBYMSXF2=(CheckBox) findViewById(R.id.checkBox_baobeiyumiaoshuiangfu2);
		mBBYMSXF3=(CheckBox) findViewById(R.id.checkBox_baobeiyumiaoshuiangfu3);
		mBBYMSXF4=(CheckBox) findViewById(R.id.checkBox_baobeiyumiaoshuiangfu4);
		mBBYMSXF5=(CheckBox) findViewById(R.id.checkBox_baobeiyumiaoshuiangfu5);
		mMJDFWTD1=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu1);
		mMJDFWTD2=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu2);
		mMJDFWTD3=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu3);
		mMJDFWTD4=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu4);
		mMJDFWTD5=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu5);
		mMJDFHSD1=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu1);
		mMJDFHSD2=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu2);
		mMJDFHSD3=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu3);
		mMJDFHSD4=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu4);
		mMJDFHSD5=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu5);
		mFabiaopingjia=(TextView) findViewById(R.id.pingjia_fabiao);
		
	}

}
