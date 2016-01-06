package com.alljf.jf.activity;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import com.Application.SysApplication;
import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.other.NetReceiver;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class MainActivity extends TabActivity {

	//协作开发测试第一次
	//协作开发测试arige-1
	NetReceiver mReceiver = new NetReceiver();
    IntentFilter mFilter = new IntentFilter();
	RadioButton t1, t2, t3, t4;
	
	private static TabHost tabHost;
	private static Context context;
	// private TextView main_tab_new_message;
	// private static RadioButton main_tab_myExam;
	TabHost.TabSpec spec;
	Intent intent;
	private String TAG = this.getClass().getName();
	private IWXAPI wxapi;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		 mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mReceiver, mFilter);
		context = this;
		regToWx();
		initview();
		
		
	}

	private void regToWx(){
		wxapi = WXAPIFactory.createWXAPI(context, CommonConstants.WXAPP_ID,true);
		wxapi.registerApp(CommonConstants.WXAPP_ID);
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
//					
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
