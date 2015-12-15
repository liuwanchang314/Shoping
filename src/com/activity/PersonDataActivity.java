package com.activity;

import com.Application.SysApplication;
import com.Extension.RoundImageView;
import com.adapter.MyAllproductAdapter;
import com.bean.PersonDataBean;
import com.jsonParser.AllProductDataJson;
import com.jsonParser.PersonDataJsonprser;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


import android.view.Window;


/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 下午1:33:15
 * 个人资料界面
 */  

public class PersonDataActivity extends Activity implements OnClickListener{
	
	private ImageView mBack,mHome;
	private RoundImageView mHeadPic;//自定义圆形头像
	private TextView mPhone;//电话
	private TextView mEmail;//邮箱
	private LinearLayout mAnquan;//安全信息
	private LinearLayout mPayinfo;//支付
	private LinearLayout mCard;//名片
	private String UserName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_persondata);
		initview();
		UserName=getUserName();
		Log.i("获取到username没有",UserName);
		GetData();
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-14 下午9:56:46
	 * 获取登陆数据
	 */  
	private void GetData() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "get_userinfo");
		params.addBodyParameter("username", UserName);
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
		        	PersonDataBean bean=PersonDataJsonprser.getbean(str);
		        	mPhone.setText(bean.getTel());
		        	mEmail.setText(bean.getEmail());
		        	String imageurl=bean.getHead_img();
		        	if(imageurl.equals("")||imageurl==null){
		        		//不做任何操作，选择默认图片
		        	}else{
		        		BitmapUtils bitmapUtils=new BitmapUtils(PersonDataActivity.this);
		        		bitmapUtils.display(mHeadPic, imageurl);
		        	}
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}

	//获取用户用户名
	private String getUserName() {
		return SysApplication.getInstance().getUserInfo().getName();
	}
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.persondata_back);
		mBack.setOnClickListener(this);
		mHome=(ImageView) findViewById(R.id.persondata_home);
		mHome.setOnClickListener(this);
		mHeadPic=(RoundImageView) findViewById(R.id.persondata_touxiang);
		mPhone=(TextView) findViewById(R.id.persondata_shoujihao);
		mEmail=(TextView) findViewById(R.id.persondata_dianziyouxiang);
		mAnquan=(LinearLayout) findViewById(R.id.persondata_anquanxinxi);
		mAnquan.setOnClickListener(this);
		mPayinfo=(LinearLayout) findViewById(R.id.persondata_zhifumima);
		mPayinfo.setOnClickListener(this);
		mCard=(LinearLayout) findViewById(R.id.persondata_mingpian);
		mCard.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.persondata_back://返回
			Toast.makeText(PersonDataActivity.this,"返回",Toast.LENGTH_SHORT).show();
			break;
		case R.id.persondata_home://zhuye 
			Toast.makeText(PersonDataActivity.this,"主页",Toast.LENGTH_SHORT).show();
			break;
		case R.id.persondata_anquanxinxi://安全
			Toast.makeText(PersonDataActivity.this,"安全",Toast.LENGTH_SHORT).show();
			startActivity(new Intent(PersonDataActivity.this, ChangeSafetyActivity.class));
			break;
		case R.id.persondata_zhifumima://密码
			Toast.makeText(PersonDataActivity.this,"密码",Toast.LENGTH_SHORT).show();
			startActivity(new Intent(PersonDataActivity.this,PayPassWordFindBackActivity.class ));
			break;
		case R.id.persondata_mingpian://名片
			Toast.makeText(PersonDataActivity.this,"名片",Toast.LENGTH_SHORT).show();
			startActivity( new Intent(PersonDataActivity.this,TwoDimensionCodeActivity.class));
			break;

		default:
			break;
		}
	}
	}


