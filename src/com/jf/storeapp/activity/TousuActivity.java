package com.jf.storeapp.activity;

import com.bean.OrderBean;
import com.jf.storeapp.R;
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
import android.view.Window;
import android.widget.ListView;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015-12-29 下午11:07:38 
 * 类说明 
 */
public class TousuActivity extends Activity {
	private ListView mListview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tousu);
		initview();
		Intent intent=getIntent();
		OrderBean bean=(OrderBean) intent.getSerializableExtra("bean");
		getdata(bean.getOrdergoods().getSpec_id());
		
	}
	/**
	 * @2015-12-30下午6:12:20
	 * @author JZKJ-LWC
	 * 初始化
	 */
	private void initview() {
		// TODO Auto-generated method stub
		mListview=(ListView) findViewById(R.id.tousu_listview);
	}
	
	/**
	 * @2015-12-30下午6:15:13
	 * @author JZKJ-LWC
	 * 根据specid来获取真正的颜色值与尺寸值
	 */
	public void getdata(String id){
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "goods");
		params.addBodyParameter("part", "get_spec_main");
		params.addBodyParameter("spec_id", id);
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
		        	Log.i("获取的规格id是", str);
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	
	

}
