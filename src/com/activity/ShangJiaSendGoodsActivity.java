package com.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adapter.BuyCartAdapter;
import com.adapter.ShangjiazenpinAdapter;
import com.bean.BuyCartBean;
import com.bean.ShangjiazengpingBean;
import com.jsonParser.BuyCartJsonP;
import com.jsonParser.ShangjiazenpinJsonparser;
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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShangJiaSendGoodsActivity extends Activity {
	
	private ImageView mBack,mHome;
	private RelativeLayout layout_zengpin;
	private RelativeLayout layout_kuaidi;
	private ListView listview_zengpin;
	private ListView listview_kuaidi;
	private TextView xiayibu;
	private TextView wanceng;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shangjiafahuo);
		initview();
		Intent in=getIntent();
		final String modeString=in.getStringExtra("mode");
		getgoodsdata(modeString);
		//首先显示的是选择赠品的部分
		layout_zengpin.setVisibility(View.VISIBLE);
		layout_kuaidi.setVisibility(View.GONE);
		xiayibu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				layout_zengpin.setVisibility(View.GONE);
				layout_kuaidi.setVisibility(View.VISIBLE);
				getkuaididata(modeString);
			}
		});
		wanceng.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				ShangJiaSendGoodsActivity.this.setResult(3,intent);
				ShangJiaSendGoodsActivity.this.finish();
			}
		});
		
	}
	/**获取赠品数据
	 * @author JZKJ-LWC
	 * @date : 2015-12-20 下午12:10:27
	 */  
	private void getgoodsdata(String mode) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "get_gift_nokey");
		params.addBodyParameter("shipping_mode",mode);
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
		        	String CallBackString=responseInfo.result;
		        	Log.i("商家赠品请求有数据吗？",CallBackString);
		        	List<ShangjiazengpingBean> list=ShangjiazenpinJsonparser.getlist(CallBackString);
		        	ShangjiazenpinAdapter adapter=new ShangjiazenpinAdapter(list, ShangJiaSendGoodsActivity.this);
		        	listview_zengpin.setAdapter(adapter);
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	
	private void getkuaididata(String mode) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "get_store__express_nokey");
		params.addBodyParameter("shipping_mode",mode);
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
		        	String CallBackString=responseInfo.result;
		        	Log.i("商家快递请求有数据吗？",CallBackString);
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-19 下午11:53:05
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.shangjiafahuo_back);
		mHome=(ImageView) findViewById(R.id.shangjiafahuo_home);
		layout_kuaidi=(RelativeLayout) findViewById(R.id.shangjiafahuo_layout_xuanzekuaidi);
		layout_zengpin=(RelativeLayout) findViewById(R.id.shangjiafahuo_layouy_xuanzezengpin);
		listview_kuaidi=(ListView) findViewById(R.id.shangjiafahuo_listview_kuaidi);
		listview_zengpin=(ListView) findViewById(R.id.shangjiafahuo_listview_zengpin);
		xiayibu=(TextView) findViewById(R.id.shangjiafahuo_xiayibu);
		wanceng=(TextView) findViewById(R.id.shangjiafahuo_wancheng);
	}
	

}
