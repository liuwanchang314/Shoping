package com.alljf.jf.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.Application.SysApplication;
import com.alljf.jf.R;
import com.bean.TousuxiangqingBean;
import com.jsonParser.TousuxiangqingJsonParser;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

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
		SysApplication.getInstance().addActivity(this);
		initview();
		Intent intent=getIntent();
		String id=intent.getStringExtra("id");
		getdata(id);
	}
	/**
	 * @2016-1-6下午11:07:58
	 * 网络获取数据
	 */
	private void getdata(String id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				RequestParams params = new RequestParams();
				// 只包含字符串参数时默认使用BodyParamsEntity，
				params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
				params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
				params.addBodyParameter("type", "order");
				params.addBodyParameter("part", "complain_main");
				params.addBodyParameter("complain_id",id);
				HttpUtils http = new HttpUtils();
				http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

				        @Override
				        public void onStart() {
				        	//开始请求
				        }

				        @Override
				        public void onLoading(long total, long current, boolean isUploading) {
				            if (isUploading) {
				            } else {
				            }
				        }

				        @Override
				        public void onSuccess(ResponseInfo<String> responseInfo) {
				        	//请求成功
				        	String str=responseInfo.result;
				        	TousuxiangqingBean bean=TousuxiangqingJsonParser.getbena(str);
				        	adddata(bean);
				        }

				       

						@Override
				        public void onFailure(HttpException error, String msg) {
				        }
				});
	}
	/**
	 * @2016-1-5下午11:27:32
	 * 初始化控件
	 */
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.tousuxiangqing_back);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TousuXiangqingActivity.this.finish();
			}
		});
		mHome=(ImageView) findViewById(R.id.tousuxiangqing_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TousuXiangqingActivity.this.finish();
				startActivity(new Intent(TousuXiangqingActivity.this,MainActivity.class));
			}
		});
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
	
	 /**
	 * @2016-1-6下午11:23:26
	 */
	private void adddata(TousuxiangqingBean bean) {
			// TODO Auto-generated method stub
			mBack.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					TousuXiangqingActivity.this.finish();
				}
			});
			mHome.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(TousuXiangqingActivity.this,MainActivity.class);
					startActivity(intent);
				}
			});
			mTousudianpu.setText(bean.getAccused_name());
//			if(bean.getComplain_state().equals("10")){
//				mTousujincheng.setText("新投诉");
//			}else if(bean.getComplain_state().equals("20")){
//				mTousujincheng.setText("投诉通过转给被投诉人");
//			}else if(bean.getComplain_state().equals("30")){
//				mTousujincheng.setText("被投诉人已申诉");
//			}else if(bean.getComplain_state().equals("40")){
//				mTousujincheng.setText("提交仲裁");
//			}
			mTousuneirong.setText(bean.getComplain_content());
			mTousushijian.setText(bean.getComplain_datetime());
			BitmapUtils bitmap=new BitmapUtils(TousuXiangqingActivity.this);
			bitmap.display(mTousuzhengju1,bean.getComplain_pic1());
			bitmap.display(mTousuzhengju1,bean.getComplain_pic2());
			bitmap.display(mTousuzhengju1,bean.getComplain_pic3());
			mTousuzhuti.setText(bean.getComplain_subject_content());
			
			
			
		}

}
