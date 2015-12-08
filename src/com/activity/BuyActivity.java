package com.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BuyActivity extends Activity {

	private TextView mBack;//返回按钮
	private TextView mHome;//主页按钮
	private ImageView mNameXuanDing;//选定名称
	private TextView mChange;//编辑
	private TextView mCName;//公司名称
	private ListView mListview;
	private ImageView mQuanxuan;//全选
	private TextView mTotalPrice;//总价
	private TextView mJiesuan;//结算
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy);
		
	}
	
	
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-7 下午1:49:29
	 * 
	 */
	public void initview(){
		mBack=(TextView) findViewById(R.id.buyactivity_top_textview_back);
		mHome=(TextView) findViewById(R.id.buyactivity_top_textview_home);
		mNameXuanDing=(ImageView) findViewById(R.id.buyactivity_imageview_gongsimingchengxuanding);
		mChange=(TextView) findViewById(R.id.buyactivity_textview_change);
		mCName=(TextView) findViewById(R.id.buyactivity_textview_gongsimingcheng);
		mListview=(ListView) findViewById(R.id.butactivity_listview);
		mQuanxuan=(ImageView) findViewById(R.id.buyactivity_radiobutton_quanxuan);
		mTotalPrice=(TextView) findViewById(R.id.buyactivity_textview_totalprice);
		mJiesuan=(TextView) findViewById(R.id.buyacttivity_textview_jiesuan);
	}


}
