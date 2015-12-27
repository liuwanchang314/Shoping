package com.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class ZJorderActivity extends TabActivity {
	
	private TabHost _tabHost;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dingdan);
		 _tabHost = getTabHost();
		 AddTabPage1();
	     AddTabPage2();
	     AddTabPage3();
	     AddTabPage4();
	     AddTabPage5();
	}
	private void AddTabPage1() {
		// TODO Auto-generated method stub
		
		Intent internt1 = new Intent();
		internt1.setClass(ZJorderActivity.this,oederactivity1.class);
		
		TabSpec tabSpec = _tabHost.newTabSpec("order1");
		//指定选项卡的显示名称
		tabSpec.setIndicator("全部");
//		TextView tv=new TextView(ZJorderActivity.this);
//		tv.setText("全部");
//		tv.setGravity(Gravity.CENTER);
//		tv.setTextSize(12);
//		tabSpec.setIndicator(tv);
		//指定跳转方向
		tabSpec.setContent(internt1);          
		_tabHost.addTab(tabSpec);
	}
	private void AddTabPage2() {
		// TODO Auto-generated method stub
		Intent internt1 = new Intent();
		internt1.setClass(ZJorderActivity.this,oederactivity2.class);
		TabSpec tabSpec = _tabHost.newTabSpec("order2");
		//指定选项卡的显示名称
		tabSpec.setIndicator("待付款");
//		TextView tv=new TextView(ZJorderActivity.this);
//		tv.setText("待付款");
//		tv.setGravity(Gravity.CENTER);
//		tv.setTextSize(12);
//		tv.setHeight(30);
//		tabSpec.setIndicator(tv);
		//指定跳转方向
		tabSpec.setContent(internt1);          
		_tabHost.addTab(tabSpec);
	}
	private void AddTabPage3() {
		// TODO Auto-generated method stub
		Intent internt1 = new Intent();
		internt1.setClass(ZJorderActivity.this,oederactivity3.class);
		
		TabSpec tabSpec = _tabHost.newTabSpec("order3");
		//指定选项卡的显示名称
		tabSpec.setIndicator("待发货");
//		TextView tv=new TextView(ZJorderActivity.this);
//		tv.setText("待发货");
//		tv.setGravity(Gravity.CENTER);
//		tv.setTextSize(12);
//		tabSpec.setIndicator(tv);
		//指定跳转方向
		tabSpec.setContent(internt1);          
		_tabHost.addTab(tabSpec);
	}
	private void AddTabPage4() {
		// TODO Auto-generated method stub
		Intent internt1 = new Intent();
		internt1.setClass(ZJorderActivity.this,oederactivity4.class);
		
		TabSpec tabSpec = _tabHost.newTabSpec("order4");
		//指定选项卡的显示名称
		tabSpec.setIndicator("待收货");
//		TextView tv=new TextView(ZJorderActivity.this);
//		tv.setText("待收货");
//		tv.setGravity(Gravity.CENTER);
//		tv.setTextSize(12);
//		tabSpec.setIndicator(tv);
		//指定跳转方向
		tabSpec.setContent(internt1);          
		_tabHost.addTab(tabSpec);
	}
	private void AddTabPage5() {
		// TODO Auto-generated method stub
		Intent internt1 = new Intent();
		internt1.setClass(ZJorderActivity.this,oederactivity5.class);
		
		TabSpec tabSpec = _tabHost.newTabSpec("order5");
		//指定选项卡的显示名称
		tabSpec.setIndicator("待评价");
//		TextView tv=new TextView(ZJorderActivity.this);
//		tv.setText("评价");
//		tv.setGravity(Gravity.CENTER);
//		tv.setTextSize(12);
//		tabSpec.setIndicator(tv);
		//指定跳转方向
		tabSpec.setContent(internt1);          
		_tabHost.addTab(tabSpec);
	}

}
