
package com.alljf.jf.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.Application.SysApplication;
import com.adapter.OrderAdatper;
import com.alljf.jf.R;
import com.bean.OrderBean;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.jsonParser.OrderJsonParser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class oederactivity1 extends Activity {

	private ListView mListview;
	private SpotsDialog mdialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.orderactivity1);
		SysApplication.getInstance().addActivity(this);
		initview();
		getdatafromInternet();
	}
	
	private void getdatafromInternet() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "order_list_nokey");
		params.addBodyParameter("user_name",SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("limit", "100");
		params.addBodyParameter("limit_start", "0");
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

		        @Override
		        public void onStart() {
		        	//开始请求
		        	mdialog.show();
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
		        	mdialog.dismiss();
		        	String str=responseInfo.result;
		        	Log.i("订单数据获取了没有", str);
		        	final List<OrderBean> list=OrderJsonParser.getlist(str);
		        	Log.i("现在有多少条订单数据", list.size()+"");
		        	OrderAdatper adapter=new OrderAdatper(list, oederactivity1.this);
		        	mListview.setAdapter(adapter);
		        	mListview.setEmptyView(findViewById(R.id.order_all_empty));
		        	TextView tv=(TextView) findViewById(R.id.order_all_liulan);
		        	tv.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent intent=new Intent(oederactivity1.this,AllProductActivity.class);
							startActivity(intent);
						}
					});
		        	mListview.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							Intent intent=new Intent(oederactivity1.this,DingDanXQActivity.class);
							intent.putExtra("id",list.get(arg2).getOrder_id());
							startActivity(intent);
						}
					});
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	private void initview() {
		SpotsDialog.TAG=R.style.SpotsDialogDefault;
		mdialog=new SpotsDialog(oederactivity1.this);
		mdialog.setCanceledOnTouchOutside(false);
		// TODO Auto-generated method stub
		mListview=(ListView) findViewById(R.id.order_all);
	}
}
