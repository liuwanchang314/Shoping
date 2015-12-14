package com.activity;

import com.bean.Dynamicdetailsbean;
import com.jsonParser.DyJsonpaser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
/**
 * @author JZKJ-LWC
 * @date : 2015-12-7 下午4:53:24
 * 动态详情页
 * 此页当中需操作的控件有
 * 1.标题，
 * 2.时间
 * 3.内容
 * 4.返回按钮
 * 5.主页按钮
 */  
public class DynamicDetailsActivity extends Activity implements OnClickListener{
	
	private TextView mCallback;//返回
	private TextView mHome;//主页
	private TextView mTitle;//标题
	private TextView mTime;//时间
	private WebView mContent;//内容
	
	private String mId;//从跳转界面传过来的ID
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dynamicdetails);
		Intent intent=getIntent();
		Bundle budle=intent.getExtras();
		mId=budle.getString("id");
		initview();
		GetData();
	}

	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-10 下午12:44:39
	 *该方法用来从网络动态获取数据
	 */  
	private void GetData() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "article");
		params.addBodyParameter("part", "article_main");
		params.addBodyParameter("article_id", mId);//这里的mid需要从列表页传递过来
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
		        	Log.i("网络请求下来的参数是",str);
		        	//这里得到的就是文章内容结果集
		        	Dynamicdetailsbean bean=DyJsonpaser.getlist(str);
		        	mTitle.setText(bean.getArticle_title());
		        	mTime.setText(bean.getArticle_time());
		        	mContent.loadDataWithBaseURL(null, bean.getArticle_content(), "text/html", "utf-8", null);  
		        	
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}

	private void initview() {
		// TODO Auto-generated method stub
		mCallback=(TextView) findViewById(R.id.dynamicdetails_top_textview_back);
		mHome=(TextView) findViewById(R.id.dynamicdetails_top_textview_home);
		mTitle=(TextView) findViewById(R.id.dynamicdetais_tv_title);
		mTime=(TextView) findViewById(R.id.dynamicdetais_tv_time);
		mContent=(WebView) findViewById(R.id.dy_webview);
//		WebSettings webSettings = mContent.getSettings();
//		mContent.setWebChromeClient(new WebChromeClient());
//		webSettings.setSavePassword(false);
//		webSettings.setSaveFormData(false);
//		webSettings.setJavaScriptEnabled(true);
//		webSettings.setSupportZoom(false);
//		webSettings.setUseWideViewPort(true); 
//		webSettings.setLoadWithOverviewMode(true); 
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.dynamicdetails_top_textview_back://返回操作
			
			break;
		case R.id.dynamicdetails_top_textview_home://主页操作
			
			break;
		default:
			break;
		}
	}
	
	
	

}
