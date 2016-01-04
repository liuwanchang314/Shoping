package com.jf.storeapp.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;

import com.Application.SysApplication;
import com.adapter.OrderAdatper;
import com.bean.OrderBean;
import com.jf.storeapp.R;
import com.jsonParser.OrderJsonParser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class oederactivity1 extends Activity {

	private ListView mListview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.orderactivity1);
		initview();
		getdatafromInternet();
	}
	
	private void getdatafromInternet() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "order_list_nokey");
		params.addBodyParameter("user_name",SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("limit", "30");
		params.addBodyParameter("limit_start", "0");
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
		        	Log.i("订单数据获取了没有", str);
		        	List<OrderBean> list=OrderJsonParser.getlist(str);
		        	Log.i("现在有多少条订单数据", list.size()+"");
		        	OrderAdatper adapter=new OrderAdatper(list, oederactivity1.this);
		        	mListview.setAdapter(adapter);
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	private void initview() {
		// TODO Auto-generated method stub
		mListview=(ListView) findViewById(R.id.order_all);
	}
}
