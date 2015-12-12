package com.activity;

import com.customview.PullToRefreshGridView;
import com.customview.PullToRefreshListView;
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
import android.widget.ImageView;


/**
 * @author JZKJ-LWC
 * @date : 2015-12-10 下午9:17:31
 * 全部商品界面
 */  
@SuppressLint("NewApi")
public class AllProductActivity extends Activity {
	
	private ImageView mTubiaoqiehuan;
	private  PullToRefreshGridView  mGridview;
	private PullToRefreshListView mListview;
	private ActionBar mactionbar;
	//http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2013/0626/1392.html
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allproduct);
		initview();
		GetData();
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-10 下午10:07:28
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		mactionbar=getActionBar();
		mactionbar.hide();
		mTubiaoqiehuan=(ImageView) findViewById(R.id.allproduct_tuwenbiebiao);
		mGridview=(PullToRefreshGridView) findViewById(R.id.allproduct_grid);
		mListview=(PullToRefreshListView) findViewById(R.id.allproduct_list);
	}
	
	
	private void GetData() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "goods");
		params.addBodyParameter("part", "get_goods_nokey");
		params.addBodyParameter("search_goodsname","");
		params.addBodyParameter("goods_class", "");
		params.addBodyParameter("order", "time");
		params.addBodyParameter("limit", "");
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
		        	Log.i("网络请求下来的参数是",str);
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	

}
