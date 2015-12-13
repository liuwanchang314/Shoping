package com.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
	private ActionBar actionbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_takegoodsaddress);
		initview();
	}
	
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		actionbar=getActionBar();
		actionbar.hide();
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
