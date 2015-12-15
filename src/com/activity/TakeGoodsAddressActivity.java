package com.activity;

import java.util.List;

import com.CommonConstants;
import com.Application.SysApplication;
import com.adapter.TakeGoodsAddressAdapter;
import com.bean.ChangeSafetBean;
import com.bean.TakeGoodsAddressBean;
import com.jsonParser.TakeGoodsAddressJsonpaser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 下午3:03:40
 *收货地址展示界面
 */  
public class TakeGoodsAddressActivity extends Activity implements OnClickListener,OnItemClickListener{
	
	private ImageView mBack;
	private ImageView mHome;
	private ListView mListview;
	private TextView mAdd;
	private TakeGoodsAddressAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_takegoodsaddress);
		initview();
		getdata();
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-14 下午11:31:35
	 */ 

	//获取用户用户名
		private String getUserName() {
			return SysApplication.getInstance().getUserInfo().getName();
		}
	private void getdata() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "post_address_nokey");
		params.addBodyParameter("username", getUserName());
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
		        		//获取的数据无格式说明
		        	List<TakeGoodsAddressBean> list=TakeGoodsAddressJsonpaser.getlist(str);
		        	Log.i("现在地址有多少",list.size()+"");
		        	//给listview准备适配器
		        	adapter=new TakeGoodsAddressAdapter(list, TakeGoodsAddressActivity.this);
		        	mListview.setAdapter(adapter);
		        	
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}

	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.takegoodsaddress_back);
		mBack.setOnClickListener(this);
		mHome=(ImageView) findViewById(R.id.takegoodsaddress_home);
		mHome.setOnClickListener(this);
		mAdd=(TextView) findViewById(R.id.takegoodsaddress_add);
		mAdd.setOnClickListener(this);
		mListview=(ListView) findViewById(R.id.takegoodsaddress_listview);
		mListview.setOnItemClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.takegoodsaddress_back:
			Toast.makeText(TakeGoodsAddressActivity.this,"返回", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.takegoodsaddress_home:
			Toast.makeText(TakeGoodsAddressActivity.this,"主页", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.takegoodsaddress_add:
			Toast.makeText(TakeGoodsAddressActivity.this,"添加", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(TakeGoodsAddressActivity.this,NewTakeOverGoodsAddress.class));
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	

}
