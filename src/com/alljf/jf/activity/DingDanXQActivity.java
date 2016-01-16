package com.alljf.jf.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.adapter.DingdanxiangqingAdapter;
import com.alljf.jf.R;
import com.bean.OrderBean;
import com.customview.Mylistview;
import com.jsonParser.OrderDataJsonParser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

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
		SysApplication.getInstance().addActivity(this);
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
		//根据传递过来的orderid来获取订单详情数据
		//也就是说，需要将order页面中传递的值改变
		String orderids=intent.getStringExtra("id");
		if(orderids==null){
//			getdata(orderid);
		}else{
			getdata(orderids);
		}
		
		//由于无订单详情接口，而且订单列表接口里面也无此数据，所以暂时不写
	}
	/**
	 * @2016-1-15上午10:01:45
	 * 根据订单id来获取订单详细数据
	 * 
	 */
	private void getdata(String orderid) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "order_main");
		params.addBodyParameter("order_id",orderid);
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
		        	Log.i("订单详情请求下来的参数是",str);
		        	final OrderBean bean=OrderDataJsonParser.getbean(str);
		        	List<OrderBean> list=new ArrayList<OrderBean>();
		    		list.add(bean);
		    		adapter=new DingdanxiangqingAdapter(list,DingDanXQActivity.this);
		    		Mlistview.setAdapter(adapter);
		    		//下面开始配置时间
		    		mDingdanbianhao.setText(bean.getOrder_sn());
		    		mChuangjianshijian.setText(bean.getOrder_time());
		    		mFukuanshijian.setText(bean.getPay_time());
		    		mFahuoshijian.setText(bean.getShipping_time());
		    		mChengjiaoshijian.setText(bean.getPay_time());
		    		//下面数据也部分没有
		    		mShouhuoName.setText(bean.getReciver_name());
		    		mShouhuophone.setText(bean.getRecieveinfobean().getPhone());
		    		mShouhuoAddress.setText(bean.getRecieveinfobean().getAddress());
		    		//顶部标题部分
		    		mYunshuzhuangtai.setText("暂无");
		    		mShuoming.setText("期待再次为您服务");
		    		mYunshuTime.setText("2013.3.3");
		    		mPingjia.setOnClickListener(new OnClickListener() {
		    			
		    			@Override
		    			public void onClick(View v) {
		    				// TODO Auto-generated method stub
		    				Intent intent=new Intent(DingDanXQActivity.this, FaBiaoPingJiaActivity.class);
		    				intent.putExtra("bean",bean);
		    				startActivity(intent);
		    			}
		    		});
		    		mChakanwuliu.setOnClickListener(new OnClickListener() {
		    			
		    			@Override
		    			public void onClick(View v) {
		    				// TODO Auto-generated method stub
		    				Intent intent=new Intent(DingDanXQActivity.this, CheckLogisticsActivity.class);
		    				intent.putExtra("bean",bean);
		    				startActivity(intent);
		    			}
		    		});
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
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
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DingDanXQActivity.this.finish();
			}
		});
		mHome=(ImageView) findViewById(R.id.dingdanxiangqing_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DingDanXQActivity.this.finish();
				startActivity(new Intent(DingDanXQActivity.this,MainActivity.class));
			}
		});
		mPingjia=(TextView) findViewById(R.id.ddxq_pingjia);
		mChakanwuliu=(TextView) findViewById(R.id.ddxq_chakanwuliu);
	}

}
