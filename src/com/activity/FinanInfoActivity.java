package com.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_finan_info);
		initview();
	}

	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mcallback=(Button) findViewById(R.id.finance_btn_back);
		mHome=(TextView) findViewById(R.id.finance_btn_home);
		mListview=(ListView) findViewById(R.id.financedetail_listview);
	}

}
