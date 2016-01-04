package com.jf.storeapp.activity;

import java.util.ArrayList;
import java.util.List;

import com.adapter.DingdanxiangqingAdapter;
import com.bean.OrderBean;
import com.customview.Mylistview;
import com.jf.storeapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015-12-30 下午11:38:54 
 * 类说明 
 */
public class DingDanXQActivity extends Activity {
	private TextView mYunshuzhuangtai;
	private TextView mShuoming;
	private TextView mYunshuTime;
	private TextView mShouhuoName;
	private TextView mShouhuophone;
	private TextView mShouhuoAddress;
	private TextView mDingdanbianhao;
	private TextView mChuangjianshijian;
	private TextView mFukuanshijian;
	private TextView mFahuoshijian;
	private TextView mChengjiaoshijian;
	private Mylistview Mlistview;
	private ImageView mBack;
	private ImageView mHome;
	private TextView mPingjia;
	private TextView mChakanwuliu;
	private DingdanxiangqingAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dingdanxiangqingye);
		initview();
		initdata();
	}
	/**
	 * @2016-1-3下午6:58:17
	 * 初始化数据
	 */
	private void initdata() {
		// TODO Auto-generated method stub
		Intent intent=getIntent();
		OrderBean bean=(OrderBean) intent.getSerializableExtra("bean");
		List<OrderBean> list=new ArrayList<OrderBean>();
		list.add(bean);
		//由于无订单详情接口，而且订单列表接口里面也无此数据，所以暂时不写
		mYunshuzhuangtai.setText("暂无");
		mShuoming.setText("期待再次为您服务");
		mYunshuTime.setText("2013.3.3");
		
		//下面数据也部分没有
		mShouhuoName.setText(bean.getReciver_name());
		mShouhuophone.setText(bean.getRecieveinfobean().getPhone());
		mShouhuoAddress.setText(bean.getRecieveinfobean().getAddress());
		
		//下面开始配置时间
		mDingdanbianhao.setText(bean.getOrder_sn());
		mChuangjianshijian.setText(bean.getOrder_time());
		mFukuanshijian.setText(bean.getPay_time());
		mFahuoshijian.setText(bean.getShipping_time());
		mChengjiaoshijian.setText(bean.getPay_time());
		
		adapter=new DingdanxiangqingAdapter(list,DingDanXQActivity.this);
		Mlistview.setAdapter(adapter);
		
	}
	/**
	 * @2016-1-3下午6:40:59
	 * 初始化控件
	 */
	private void initview() {
		// TODO Auto-generated method stub
		mYunshuzhuangtai=(TextView) findViewById(R.id.ddxq_kuaidixingxi);
		mShuoming=(TextView) findViewById(R.id.ddxq_shuoming);
		mYunshuTime=(TextView) findViewById(R.id.ddxq_time);
		mShouhuoName=(TextView) findViewById(R.id.ddxq_shouhuorenname);
		mShouhuophone=(TextView) findViewById(R.id.ddxq_shouhuorendianhua);
		mShouhuoAddress=(TextView) findViewById(R.id.ddxq_shouhuodizhi);
		Mlistview=(Mylistview) findViewById(R.id.ddxq_listview);
		mDingdanbianhao=(TextView) findViewById(R.id.ddxq_dingdanbianhao);
		mChuangjianshijian=(TextView) findViewById(R.id.ddxq_chuangjianshijian);
		mFukuanshijian=(TextView) findViewById(R.id.ddxq_fukuanshijian);
		mFahuoshijian=(TextView) findViewById(R.id.ddxq_fahuoshijian);
		mChengjiaoshijian=(TextView) findViewById(R.id.ddxq_chengjiaoshijian);
		mBack=(ImageView) findViewById(R.id.dingdanxiangqing_back);
		mHome=(ImageView) findViewById(R.id.dingdanxiangqing_home);
		mPingjia=(TextView) findViewById(R.id.ddxq_pingjia);
		mChakanwuliu=(TextView) findViewById(R.id.ddxq_chakanwuliu);
	}

}
