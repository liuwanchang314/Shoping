package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adapter.myexlistviewadapter;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductClassNumberActivity extends Activity {
	
	
	private ImageView mBack;
	private ImageView mHome;
	private ExpandableListView mListview;
	private static final String G_TEXT = "g_text";//组标记
	private static final String C_TEXT1 = "c_text1";//子标记
	List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();//组数据源集合
	List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();//子数据源集合
	private myexlistviewadapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_productclassnumber);
		initview();
		GetData();
		initGroupDate();
		initChildDate();
		mAdapter=new myexlistviewadapter(groupData, childData, ProductClassNumberActivity.this);
		mListview.setAdapter(mAdapter);
		mListview.setGroupIndicator(null);
//		mListview.setDivider(null);
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-10 下午1:20:30
	 * 子菜单数据
	 */  
	private void initChildDate() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-10 下午1:19:54
	 * 组菜单数据源
	 */  
	private void initGroupDate() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
            Map<String, String> curGroupMap = new HashMap<String, String>();//该集合存储了一个组的菜单数据，组名和标记
            curGroupMap.put(G_TEXT, "Group " + i);
            groupData.add(curGroupMap);//将这个组添加到组集合中
            //一个组当中有多个子，所以这里生成集合来存储子列表项
            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            for (int j = 0; j < 5; j++) {
                Map<String, String> curChildMap = new HashMap<String, String>();//子菜单内容
                curChildMap.put(C_TEXT1, "Child " + j);
                children.add(curChildMap);//添加到集合中
            }
            childData.add(children);//循环结束，将该组的子菜单集合添加到子数据源中
        }
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-10 下午1:17:27
	 */  
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.productclassnumber_bake);
		mHome=(ImageView) findViewById(R.id.productclassnumber_home);
		mListview=(ExpandableListView) findViewById(R.id.productclassnumber_list);
	}
	private void GetData() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "goods");
		params.addBodyParameter("part", "goods_classes");
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

		        @Override
		        public void onStart() {
		        	//开始请求
		        }

		        @Override
		        public void onLoading(long total, long current, boolean isUploading) {
		            if (isUploading) {
		            } else {
		            }
		        }

		        @Override
		        public void onSuccess(ResponseInfo<String> responseInfo) {
		        	//请求成功
		        	String str=responseInfo.result;
		        	Log.i("网络请求下来的参数是",str);
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	
	
	
	
}
