package com.alljf.jf.activity;



import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.alljf.jf.R.string;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ShopsActivity extends Activity implements OnClickListener{

	private MapView mMapView = null;
	private BaiduMap mBaidumap;
	private RelativeLayout mDetails;//详情
	private TextView back;
	private TextView home;
	private String username=new String();
	private TextView mGongsimincheng;
	private TextView mName;
	private TextView mPhone;
	private TextView mQQstring;
	private TextView mAddress;
	private String map=new String();
	private Handler handle;
	private ImageView mLogo;
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
		if(username.equals("user")){
			//说明是空值
			getdataFromeInternet();
		}else{
			getdataFromeInternets(username);
		}
		handle=new Handler(){
			public void handleMessage(android.os.Message msg) {
				switch (msg.arg1) {
				case 0:
					Log.i("现在地图数据是多少",(String) msg.obj);
					String str=(String) msg.obj;
					if(str.contains(",")){
						String[] key_vealue=str.split(",");
						String key=key_vealue[0];
						String value=key_vealue[1];
						double weidu=Double.parseDouble(key);
						double jingdu=Double.parseDouble(value);
						changeMap(weidu,jingdu);
					}else{
						changeMap(31.973215,121.112759);
					}
					
					break;

				default:
					break;
				}
			}

			
		};
		
	}
	/**
	 * 动态修改地图上面的坐标点
	 * @param jingdu 
	 * @param weidu 
	 * @2016-1-14下午1:24:42
	 */
	private void changeMap(double weidu, double jingdu) {
		// TODO Auto-generated method stub
		//定义Maker坐标点  
				mBaidumap = mMapView.getMap();
				LatLng point = new LatLng(jingdu,weidu);  
				//构建Marker图标  
				//设定中心点坐标 
				 //定义地图状态 
				MapStatus mMapStatus = new MapStatus.Builder() 
				 .target(point) 
				 .zoom(14) 
				 .build(); 
				 //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化 

				MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus); 
				 //改变地图状态 
				mBaidumap.setMapStatus(mMapStatusUpdate); 
				BitmapDescriptor bitmap = BitmapDescriptorFactory  
				    .fromResource(R.drawable.icon_gcoding);  
				//构建MarkerOption，用于在地图上添加Marker  
				OverlayOptions option = new MarkerOptions()  
				    .position(point)  
				    .icon(bitmap);  
				//在地图上添加Marker，并显示  
				mBaidumap.addOverlay(option);
				
	};
	/**
	 * @param username2 
	 * @2016-1-10下午5:29:23
	 * 网络获取店铺信息数据
	 */
	private void getdataFromeInternet() {
				RequestParams params = new RequestParams();
				params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
				params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
				params.addBodyParameter("type", "user");
				params.addBodyParameter("part", "storeinfo");
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
				        	try {
								JSONObject obj=new JSONObject(str);
								JSONObject objs=obj.getJSONObject("data");
								String storename=objs.getString("company_name");
								String name=objs.getString("real_name");
								String phone=objs.getString("link_tel");
								String logo=objs.getString("logo");
								String QQstring=objs.getString("qqnum");
								String address=objs.getString("address");
								String map=objs.getString("map");
								Message msg=handle.obtainMessage();
								msg.arg1=0;
								msg.obj=map;
								handle.sendMessage(msg);
								BitmapUtils bmp=new BitmapUtils(ShopsActivity.this);
								Log.i("路径是多少",logo);
								bmp.display(mLogo,CommonConstants.APP_IMG_URL+logo);
								mGongsimincheng.setText(storename);
								mName.setText(name);
								mPhone.setText(phone);
								mQQstring.setText(QQstring);
								mAddress.setText(address);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				        }

				        @Override
				        public void onFailure(HttpException error, String msg) {
				        }
				});
	}
	private void getdataFromeInternets(String username2) {
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
		        	try {
						JSONObject obj=new JSONObject(str);
						JSONObject objs=obj.getJSONObject("data");
						String storename=objs.getString("company_name");
						String name=objs.getString("real_name");
						String phone=objs.getString("link_tel");
						String logo=objs.getString("logo");
						String QQstring=objs.getString("qqnum");
						String address=objs.getString("address");
						String map=objs.getString("map");
						Message msg=handle.obtainMessage();
						msg.arg1=0;
						msg.obj=map;
						handle.sendMessage(msg);
						BitmapUtils bmp=new BitmapUtils(ShopsActivity.this);
						Log.i("路径是多少",logo);
						bmp.display(mLogo,CommonConstants.APP_IMG_URL+logo);
						mGongsimincheng.setText(storename);
						mName.setText(name);
						mPhone.setText(phone);
						mQQstring.setText(QQstring);
						mAddress.setText(address);
					} catch (JSONException e) {
						e.printStackTrace();
					}
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
}
	private void initviemapw() {
		// TODO Auto-generated method stub
//		String[] key_vealue=str.split(",");
//		String key=key_vealue[0];
//		String value=key_vealue[1];
//		int weidu=Integer.parseInt(key);
//		int jingdu=Integer.parseInt(value);
//		mMapView = (MapView) findViewById(R.id.bmapView); 
		mBaidumap = mMapView.getMap();
		//定义Maker坐标点  
		LatLng point = new LatLng(121.36,35.69);  
		//定义地图状态
		MapStatus mMapStatus = new MapStatus.Builder()
		.target(point)
		.zoom(16)
		.build();
		//定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
		MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
		//改变地图状态
		mBaidumap.setMapStatus(mMapStatusUpdate); 
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
		mLogo=(ImageView) findViewById(R.id.person_logo);
		mMapView = (MapView) findViewById(R.id.bmapView); 
		//初始化地图
		//定义中心点
		//设定中心点坐标 
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
				startActivity(new Intent(ShopsActivity.this,MainActivity.class));
				ShopsActivity.this.finish();
			}
		});
		mGongsimincheng=(TextView) findViewById(R.id.relation_tv_goongsimingcheng);
		mName=(TextView) findViewById(R.id.relation_tv_relationname);
		mPhone=(TextView) findViewById(R.id.relation_tv_relationphone);
		mQQstring=(TextView) findViewById(R.id.relation_tv_relaitonQQ);
		mAddress=(TextView) findViewById(R.id.relation_tv_relaitonadress);
		
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
//			startActivity(new Intent(ShopsActivity.this,CompanyInfoActivity.class));
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
