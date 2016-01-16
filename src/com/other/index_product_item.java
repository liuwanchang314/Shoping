package com.other;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.indexListviewAdapter;
import com.alljf.jf.R;
import com.alljf.jf.activity.Product_infoActivity;
import com.customview.MyGridView;
import com.utils.StringManager;

public class index_product_item extends RelativeLayout {

	private Context context;
	private Map<String, String> map;
	private WebView  wv;
	private TextView tv;
	private MyGridView mlistview;
	@SuppressLint("NewApi")
	public index_product_item(Context contexts) {
		super(contexts);
		this.context = contexts;
		@SuppressWarnings("static-access")
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.product_item, null);
		wv = (WebView) v.findViewById(R.id.proItem_wvTop);
		WebSettings webSettings = wv.getSettings();
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);
		webSettings.setUseWideViewPort(true); 
		webSettings.setLoadWithOverviewMode(true); 
		tv = (TextView) v.findViewById(R.id.proItem_tvTop);
		mlistview = (MyGridView) v.findViewById(R.id.proitem_lv);
		addView(v);
	}
	
	public void setDate(Map<String, String> map){
		this.map = map;
		bindDate();
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public void bindDate(){
		String name = map.get("class_name");
		if(name.equals("")||name == null){
			tv.setVisibility(View.GONE);
			wv.setVisibility(View.VISIBLE);
			 wv.loadDataWithBaseURL(null, map.get("class_info"), "text/html", "utf-8", null);  
		        wv.getSettings().setJavaScriptEnabled(true);  
		        wv.setWebChromeClient(new WebChromeClient()); 
		}else{
			wv.setVisibility(View.GONE);
			tv.setVisibility(View.VISIBLE);
			tv.setText(name);
		}
		
		String listString  = map.get("class_goods");
		if(listString != null && !listString.equals("")){
			final List<Map<String, String>> list = StringManager.getListMapByJson(listString);
			indexListviewAdapter adapter = new indexListviewAdapter(context, list);
			mlistview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
				  Intent intent = new Intent(context, Product_infoActivity.class);
				  Bundle bundle = new Bundle();
				  bundle.putCharSequence("id", list.get(position).get("goods_id"));
				  intent.putExtras(bundle);
				  context.startActivity(intent);
				}
			});
			mlistview.setAdapter(adapter);
		}
		
	}

}
