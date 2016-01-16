package com.alljf.jf.activity;

import java.util.ArrayList;
import java.util.List;

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
import com.adapter.DingdanxiangqingAdapter;
import com.alljf.jf.R;
import com.alljf.jf.R.string;
import com.bean.OrderBean;
import com.jsonParser.OrderDataJsonParser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 下午4:15:06
 * 支付成功界面
 */  
public class SuccessPayActivity extends Activity implements OnClickListener{
	
	
	private ImageView mback,mhome;
	private String price;
	private String orderid;
	private TextView mShifukuan;
	private TextView mName;
	private TextView mPhone;
	private TextView mAddress;
	private TextView mDingdanxiangqing;
	private TextView mFanhuizhuye;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_successpay);
		SysApplication.getInstance().addActivity(this);
		Intent intent=getIntent();
		price=intent.getStringExtra("sfk");
		orderid=intent.getStringExtra("orderid");
		Log.i("这个时候订单号是多少",orderid);
		//用该订单号来请求数据，然后展示部分数据在该界面
		initview();
		getdata(orderid);
	}

	/**
	 * @2016-1-7下午10:20:07
	 */
	private void initview() {
		mback=(ImageView) findViewById(R.id.successpay_back);
		mback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SuccessPayActivity.this.finish();
			}
		});
		mhome=(ImageView) findViewById(R.id.successpay_home);
		mhome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SuccessPayActivity.this.finish();
				startActivity(new Intent(SuccessPayActivity.this,MainActivity.class));
			}
		});
		
		mShifukuan=(TextView) findViewById(R.id.successpay_price);
		mName=(TextView) findViewById(R.id.successpay_name);
		mPhone=(TextView) findViewById(R.id.success_dianhua);
		mAddress=(TextView) findViewById(R.id.successpay_address);
		mDingdanxiangqing=(TextView) findViewById(R.id.successpay_dingdanxiangqing);
		mDingdanxiangqing.setOnClickListener(this);
		mFanhuizhuye=(TextView) findViewById(R.id.successpay_fanhuizhuye);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.successpay_dingdanxiangqing:
			SuccessPayActivity.this.finish();
			Intent intent=new Intent(SuccessPayActivity.this,DingDanXQActivity.class);
			intent.putExtra("id",orderid);
			startActivity(intent);
			break;
		case R.id.successpay_fanhuizhuye:
			SuccessPayActivity.this.finish();
			Intent intents=new Intent(SuccessPayActivity.this,MainActivity.class);
			startActivity(intents);
		default:
			break;
		}
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
		        	mShifukuan.setText(bean.getOrdergoods().getGoods_pay_price());
		        	mName.setText(bean.getReciver_name());
		        	mPhone.setText(bean.getRecieveinfobean().getPhone());
		        	mAddress.setText(bean.getRecieveinfobean().getAddress());
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	


}
