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
import android.widget.ListView;
import android.widget.TextView;

import com.adapter.CompanyNoticeAdapter;
import com.alljf.jf.R;
import com.bean.CompanyNoticeBean;
import com.jsonParser.CompanyNoticeJsonPaser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class SoldOutActivity extends Activity {
	
	private TextView mBack;
	private TextView mHome;
	private ListView mListview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_soldout);
		initview();
		GetData();
	}
	
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(TextView) findViewById(R.id.soldout_back);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SoldOutActivity.this.finish();
			}
		});
		
		mHome=(TextView) findViewById(R.id.soldout_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SoldOutActivity.this.finish();
				startActivity(new Intent(SoldOutActivity.this,MainActivity.class));
			}
		});
		mListview=(ListView) findViewById(R.id.soldout_listview);
		
	}
	
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-10 下午3:21:19
	 * 下架公告获取数据
	 */  
	private void GetData() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "article");
		params.addBodyParameter("part", "article_list_nokey");
		params.addBodyParameter("type_id", "2");
		params.addBodyParameter("limit", "20");
		params.addBodyParameter("limit_start", "1");
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
		        	Log.i("下架公告网络请求下来的参数是",str);
		        	List<CompanyNoticeBean> list=new ArrayList<CompanyNoticeBean>();
		        	list=CompanyNoticeJsonPaser.GetBean(str);
		        	CompanyNoticeAdapter adapter=new CompanyNoticeAdapter(list, SoldOutActivity.this);
		        	mListview.setAdapter(adapter);
		        	mListview.setEmptyView(findViewById(R.id.empty));
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}

}
