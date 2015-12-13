package com.activity;

import com.CommonConstants;
import com.Application.SysApplication;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends TabActivity {

	//协作开发测试第一次
	//协作开发测试arige-1
	
	RadioButton t1, t2, t3, t4;
	
	private static TabHost tabHost;
	private static Context context;
	// private TextView main_tab_new_message;
	// private static RadioButton main_tab_myExam;
	TabHost.TabSpec spec;
	Intent intent;
	private String TAG = this.getClass().getName();
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		context = this;
		initview();
//		RequestParams params = new RequestParams();
//		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
//		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
//		params.addBodyParameter("type", "adv");
//		params.addBodyParameter("part", "index_slide");
//		HttpUtils httpUtils = new HttpUtils();
//		httpUtils.send(HttpMethod.POST, CommonConstants.APP_URL,params, new RequestCallBack<String>() {
//
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				// TODO Auto-generated method stub
////				Log.e("tag", "======"+arg1);
//			}
//
//			@Override
//			public void onSuccess(ResponseInfo<String> arg0) {
//				// TODO Auto-generated method stub
//			}
//		});
		
	}

	private void setRadioColor() {
		t1.setTextColor(Color.rgb(142, 142, 142));
		t2.setTextColor(Color.rgb(142, 142, 142));
		t3.setTextColor(Color.rgb(142, 142, 142));
		t4.setTextColor(Color.rgb(142, 142, 142));

	}
	
	
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-7 上午10:00:01
	 * 初始化控件
	 */  
	public void initview(){
		
		t1 = (RadioButton) findViewById(R.id.tab_rb_1);
		t2 = (RadioButton) findViewById(R.id.tab_rb_2);
		t3 = (RadioButton) findViewById(R.id.tab_rb_3);
		t4 = (RadioButton) findViewById(R.id.tab_rb_4);

		tabHost = this.getTabHost();

		intent = new Intent().setClass(this, IndexActivity.class);
		spec = tabHost.newTabSpec("首页").setIndicator("首页").setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, ShopsActivity.class);
		spec = tabHost.newTabSpec("联系商家").setIndicator("联系商家")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, BuyActivity.class).addFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP);
		spec = tabHost.newTabSpec("购物车").setIndicator("购物车").setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, UserActivity.class);
		spec = tabHost.newTabSpec("个人中心").setIndicator("个人中心").setContent(intent);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);

		RadioGroup radioGroup = (RadioGroup) this
				.findViewById(R.id.tab_rg_menu);

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				setRadioColor();
				switch (checkedId) {
				case R.id.tab_rb_1:
					t1.setTextColor(Color.rgb(248, 78, 1));
					tabHost.setCurrentTabByTag("首页");
					break;
				case R.id.tab_rb_2:
					t2.setTextColor(Color.rgb(248, 78, 1));
					tabHost.setCurrentTabByTag("联系商家");
					break;
				case R.id.tab_rb_3:
					if(SysApplication.getInstance().getIsLogin()){
						t3.setTextColor(Color.rgb(248, 78, 1));
						tabHost.setCurrentTabByTag("购物车");
					}else{
						Intent intent = new Intent(MainActivity.this, LoginActivity.class);
						startActivityForResult(intent, CommonConstants.SHOP_CARD);
					}
					
					break;
				case R.id.tab_rb_4:
					if(SysApplication.getInstance().getIsLogin()){
						t4.setTextColor(Color.rgb(248, 78, 1));
						tabHost.setCurrentTabByTag("个人中心");
					}else{
						Intent intent = new Intent(MainActivity.this, LoginActivity.class);
						startActivityForResult(intent, CommonConstants.PERSONAL_CENTER);
					}
					
					break;
				default:
					// tabHost.setCurrentTabByTag("我的考试");
					break;
				}
			}
		});
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == CommonConstants.SHOP_CARD && resultCode == RESULT_OK){
			if(SysApplication.getInstance().getIsLogin()){
				t3.setTextColor(Color.rgb(248, 78, 1));
				tabHost.setCurrentTabByTag("购物车");
			}
			
		}
		
		if(requestCode == CommonConstants.PERSONAL_CENTER && resultCode == RESULT_OK){
			
			if(SysApplication.getInstance().getIsLogin()){
				t4.setTextColor(Color.rgb(248, 78, 1));
				tabHost.setCurrentTabByTag("个人中心");
			}
		}
	}
	
}
