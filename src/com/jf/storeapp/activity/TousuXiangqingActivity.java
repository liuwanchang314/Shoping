package com.jf.storeapp.activity;

import com.jf.storeapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-5 下午11:14:01 
 * 类说明  投诉详情页面
 */
public class TousuXiangqingActivity extends Activity {
	private TextView mTousujincheng;
	private TextView mTousuzhuti;
	private TextView mTousushijian;
	private TextView mGoodsname;
	private TextView mGoodsprice;
	private TextView mGoodsnum;
	private TextView mWentimiaoshu;
	private TextView mTousudianpu;
	private TextView mTousuneirong;
	private TextView mChulixiangqing;
	private ImageView mTousuzhengju1,mTousuzhengju2,mTousuzhengju3;
	private ImageView mShensuzhengju1,mShensuzhengju2,mShensuzhengju3;
	private ImageView mGoodsImage;
	private ImageView mBack,mHome;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tousuxiangqing);
		initview();
	}
	/**
	 * @2016-1-5下午11:27:32
	 * 初始化控件
	 */
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.tousuxiangqing_back);
		mHome=(ImageView) findViewById(R.id.tousuxiangqing_home);
		mTousudianpu=(TextView) findViewById(R.id.tousuxiangqing_beitousudianpu);
		mTousujincheng=(TextView) findViewById(R.id.tousuxiangqing_jincheng);
		mTousuneirong=(TextView) findViewById(R.id.tousuxiangqing_tousuneirong);
		mTousushijian=(TextView) findViewById(R.id.tousuxiangqing_tousushijian);
		mTousuzhengju1=(ImageView) findViewById(R.id.tousuxiangqing_zhengju1);
		mTousuzhengju2=(ImageView) findViewById(R.id.tousuxiangqing_zhengju2);
		mTousuzhengju3=(ImageView) findViewById(R.id.tousuxiangqing_zhengju3);
		mTousuzhuti=(TextView) findViewById(R.id.tousuxiangqing_tousuzhuti);
		mGoodsImage=(ImageView) findViewById(R.id.tousuxiangqing_goodsimage);
		mGoodsname=(TextView) findViewById(R.id.tousuxiangqing_goodsname);
		mGoodsprice=(TextView) findViewById(R.id.tousuxiangqing_jiage);
		mGoodsnum=(TextView) findViewById(R.id.tousuxiangqing_shuliang);
		mWentimiaoshu=(TextView) findViewById(R.id.tousuxiangqing_wentimiaoshu);
		mChulixiangqing=(TextView) findViewById(R.id.tousuxiangqing_chulixiangqing);
	}

}
