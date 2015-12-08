package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
	private TextView mContent;//内容
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamicdetails);
		
		initview();
	}

	private void initview() {
		// TODO Auto-generated method stub
		mCallback=(TextView) findViewById(R.id.dynamicdetails_top_textview_back);
		mHome=(TextView) findViewById(R.id.dynamicdetails_top_textview_home);
		mTitle=(TextView) findViewById(R.id.dynamicdetais_tv_title);
		mTime=(TextView) findViewById(R.id.dynamicdetais_tv_time);
		mContent=(TextView) findViewById(R.id.dynamicdetais_tv_content);
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
