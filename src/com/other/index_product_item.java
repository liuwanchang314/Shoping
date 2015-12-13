package com.other;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import com.activity.R;
import com.adapter.indexListviewAdapter;
import com.customview.MyGridView;
import com.customview.Mylistview;
import com.utils.StringManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.Visibility;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class index_product_item extends RelativeLayout {

	private Context context;
	private Map<String, String> map;
	private WebView  wv;
	private TextView tv;
	private MyGridView mlistview;
	public index_product_item(Context context) {
		super(context);
		this.context = context;
		View v = inflate(context, R.layout.product_item, null);
		wv = (WebView) v.findViewById(R.id.proItem_wvTop);
		tv = (TextView) v.findViewById(R.id.proItem_tvTop);
		mlistview = (MyGridView) v.findViewById(R.id.proitem_lv);
		addView(v);
	}
	
	@SuppressLint("NewApi")
	public void setDate(Map<String, String> map){
		this.map = map;
		bindDate();
	}
	
	@SuppressLint("NewApi")
	public void bindDate(){
		String name = map.get("class_name");
		if(name.isEmpty()||name == null){
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
		if(listString != null && !listString.isEmpty()){
			List<Map<String, String>> list = StringManager.getListMapByJson(listString);
			indexListviewAdapter adapter = new indexListviewAdapter(context, list);
			mlistview.setAdapter(adapter);
		}
		
	}

}
