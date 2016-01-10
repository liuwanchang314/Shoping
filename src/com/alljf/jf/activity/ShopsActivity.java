package com.alljf.jf.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.alljf.jf.R;
import com.alljf.jf.R.string;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class ShopsActivity extends Activity implements OnClickListener{

	private MapView mMapView = null;
	private BaiduMap mBaidumap;
	private RelativeLayout mDetails;//详情
	private TextView back;
	private TextView home;
	private String username=new String();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 SDKInitializer.initialize(getApplicationContext());
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		SysApplication.getInstance().addActivity(this);
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
		setContentView(R.layout.activity_shops);
		initview();
		initviemapw();
		username=SysApplication.getInstance().getUserInfo().getName();
		getdataFromeInternet(username);
	}
	/**
	 * @param username2 
	 * @2016-1-10下午5:29:23
	 * 网络获取店铺信息数据
	 */
	private void getdataFromeInternet(String username2) {
				RequestParams params = new RequestParams();
				params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
				params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
				params.addBodyParameter("type", "user");
				params.addBodyParameter("part", "userinfo");
				params.addBodyParameter("username",username2);
				HttpUtils http = new HttpUtils();
				http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

				        @Override
				        public void onStart() {
				        }

				        @Override
				        public void onLoading(long total, long current, boolean isUploading) {
				            if (isUploading) {
				            } else {
				            }
				        }

				        @Override
				        public void onSuccess(ResponseInfo<String> responseInfo) {
				        	String str=responseInfo.result;
				        	Log.i("店铺信息的数据是",str+"");
				        }

				        @Override
				        public void onFailure(HttpException error, String msg) {
				        }
				});
	}
	private void initviemapw() {
		// TODO Auto-generated method stub
		mMapView = (MapView) findViewById(R.id.bmapView); 
		mBaidumap = mMapView.getMap();
		//定义Maker坐标点  
		LatLng point = new LatLng(39.963175, 116.400244);  
		//构建Marker图标  
		BitmapDescriptor bitmap = BitmapDescriptorFactory  
		    .fromResource(R.drawable.icon_gcoding);  
		//构建MarkerOption，用于在地图上添加Marker  
		OverlayOptions option = new MarkerOptions()  
		    .position(point)  
		    .icon(bitmap);  
		//在地图上添加Marker，并显示  
		mBaidumap.addOverlay(option);
	}
	private void initview() {
		// TODO Auto-generated method stub
		//获取地图控件引用  
        
		mDetails=(RelativeLayout) findViewById(R.id.shops_companydetails);
		mDetails.setOnClickListener(this);
		back=(TextView) findViewById(R.id.relationactivity_top_textview_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		home=(TextView) findViewById(R.id.relationactivity_top_textview_home);
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShopsActivity.this.finish();
			}
		});
	}
	@Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
        mMapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume();  
        }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
        mMapView.onPause();  
        }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.shops_companydetails://点击查看详情
			startActivity(new Intent(ShopsActivity.this,CompanyInfoActivity.class));
			break;

		default:
			break;
		}
	}
	
	
	private long mExitTime;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "再点一次，退出程序",
							Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
					finish();
					SysApplication.getInstance().exit();
				}
			}
		return true;
	
	}
	
	


}
