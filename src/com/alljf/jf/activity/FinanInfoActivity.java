package com.alljf.jf.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.Application.SysApplication;
import com.adapter.FinanceAdapter;
import com.alljf.jf.R;
import com.bean.FinanceBean;
import com.jsonParser.FinanceJsonPaser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class FinanInfoActivity extends Activity {
	
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-7 下午5:13:50
	 * 财务支出页
	 * 需操作控件有
	 * 1返回
	 * 2.主页
	 * 3.listview
	 */  
	
	private Button mcallback;
	private TextView mHome;
	private ListView mListview;
	private FinanceAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_finan_info);
		initview();
		getdata();
	}

	private void getdata() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "finance");
		params.addBodyParameter("part", "user_finance_log_domain_nokey");
		params.addBodyParameter("user_name", SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("limit","30");
		params.addBodyParameter("limit_start","1");
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
		        	Log.i("caiwu 请求下来的参数是",str);
		        	List<FinanceBean> list=FinanceJsonPaser.getlist(str);
		        	adapter=new FinanceAdapter(list, FinanInfoActivity.this);
		        	mListview.setAdapter(adapter);
		        	mListview.setEmptyView(findViewById(R.id.finance_empty));
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}

	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mcallback=(Button) findViewById(R.id.finance_btn_back);
		mcallback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FinanInfoActivity.this.finish();
			}
		});
		mHome=(TextView) findViewById(R.id.finance_btn_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FinanInfoActivity.this.finish();
				startActivity(new Intent(FinanInfoActivity.this,MainActivity.class));
			}
		});
		mListview=(ListView) findViewById(R.id.financedetail_listview);
	}

}
