package com.alljf.jf.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.Application.SysApplication;
import com.adapter.ComplaintAdapter;
import com.alljf.jf.R;
import com.bean.ComplaintBean;
import com.jsonParser.ComplaitJsonParser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
/**
 * 投诉列表界面
 * */
public class ComplaintActivity extends Activity {
	
	private ImageView mBack;
	private ImageView mHome;
	private ListView mListview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_complaint);
		SysApplication.getInstance().addActivity(this);
		initview();
		getdata();
	}

	private void getdata() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "complain_list_nokey");
		params.addBodyParameter("limit","20");
		params.addBodyParameter("user_name",SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("limit_start","0");
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
		        	Log.i("投诉数据获取了没有", str);
		        	List<ComplaintBean> list=ComplaitJsonParser.getlist(str);
		        	ComplaintAdapter adapter=new ComplaintAdapter(list, ComplaintActivity.this);
		        	mListview.setAdapter(adapter);
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}

	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.complaint_back);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ComplaintActivity.this.finish();
			}
		});
		mHome=(ImageView) findViewById(R.id.complaint_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ComplaintActivity.this.finish();
				Intent intent=new Intent(ComplaintActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
		mListview=(ListView) findViewById(R.id.complaint_listview);
	}

}
