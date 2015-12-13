package com.activity;

import java.util.ArrayList;
import java.util.List;

import com.adapter.CompanyNoticeAdapter;
import com.bean.CompanyNoticeBean;
import com.jsonParser.CompanyNoticeJsonPaser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.view.Window;
import android.widget.ListView;

public class CompanyNoticeActivity extends Activity {
	
	private ListView mListview;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_companynotice);

		initview();
		//准备数据源
		GetData();
		
	}
	private void GetData() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "article");
		params.addBodyParameter("part", "article_list_nokey");
		params.addBodyParameter("type_id", "1");
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
		        	Log.i("公司公告网络请求下来的参数是",str);
		        	List<CompanyNoticeBean> list=new ArrayList<CompanyNoticeBean>();
		        	list=CompanyNoticeJsonPaser.GetBean(str);
		        	CompanyNoticeAdapter adapter=new CompanyNoticeAdapter(list, CompanyNoticeActivity.this);
		        	mListview.setAdapter(adapter);
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	private void initview() {
		// TODO Auto-generated method stub
		mListview=(ListView) findViewById(R.id.companynotice_listview);
	}

}
