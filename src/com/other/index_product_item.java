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
import android.annotation.TargetApi;
import android.content.Context;
import android.opengl.Visibility;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class index_product_item extends RelativeLayout {

	private Context context;
	private Map<String, String> map;
	private WebView  wv;
	private TextView tv;
	private MyGridView mlistview;
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
		// �����һ�仰�Ǳ���ģ�����Ҫ��javaScript��Ȼ����һ�ж���ͽ�͵�
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
			List<Map<String, String>> list = StringManager.getListMapByJson(listString);
			indexListviewAdapter adapter = new indexListviewAdapter(context, list);
			mlistview.setAdapter(adapter);
		}
		
	}

}
