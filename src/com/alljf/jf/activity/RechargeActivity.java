package com.alljf.jf.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.alljf.jf.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.other.InternerIsConnection;
import com.other.NetReceiver;
import com.pay.ali.Ali_Pay;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-7 下午9:59:40
 * 充值界面
 * 
 */  
public class RechargeActivity extends Activity implements OnClickListener{
	private ImageView mback;//返回
	private ImageView mhome;//主页
	private EditText mJine;//输入的金额
	private TextView mCurrentPrice;//当前金额
	private TextView mcurrentrechange;//当前充值金额
	private TextView mPayclass;//支付方式
	private ImageView mZzhifubao;//支付宝
	private RelativeLayout mBgzhifubao;//支付宝需改变背景
	private ImageView mWeixin;//微信
	private RelativeLayout mBgweixin;//微信需改变北京
	private TextView mPay;//确认支付
	private String TAG=new String();
	private boolean isconnection;
	private ConnectivityManager manager;//网络管理器对象
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_recharge);
		SysApplication.getInstance().addActivity(this);
		initview();
		isconnecions();
		initmanager();
		if(isconnection){
			getdatatixianedu();
		}else{
			Toast.makeText(RechargeActivity.this,"请检查网络设置",1).show();
		}
		initdata();
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-27 下午9:49:45
	 * 用于实时检测网络是否连接
	 */  
	private void isconnecions() {
		// TODO Auto-generated method stub
		NetReceiver mReceiver = new NetReceiver();
	    IntentFilter mFilter = new IntentFilter();
		 mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mReceiver, mFilter);
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-27 下午9:11:36
	 * 初始化网络管理对象
	 */  
	private void initmanager() {
		// TODO Auto-generated method stub
		manager =(ConnectivityManager) RechargeActivity.this.getSystemService(RechargeActivity.this.CONNECTIVITY_SERVICE);//获得网络连接的管理者对象
		isconnection=InternerIsConnection.network(manager,RechargeActivity.this);
	}
	private void initdata() {
		// TODO Auto-generated method stub
	}
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mback=(ImageView) findViewById(R.id.rechrageactivity_imageView_callback);
		mback.setOnClickListener(this);
		mhome=(ImageView) findViewById(R.id.rechrageactivity_imageView_home);
		mhome.setOnClickListener(this);
		mJine=(EditText) findViewById(R.id.rechrageactivity_edittext_jine);
		mCurrentPrice=(TextView) findViewById(R.id.rechrageactivity_current_jine);
		mcurrentrechange=(TextView) findViewById(R.id.rechrageactivity_textview_jine);
		mPayclass=(TextView) findViewById(R.id.rechrageactivity_textview_payclass);
		mZzhifubao=(ImageView) findViewById(R.id.rechrageactivity_imageview_zhifubao);
		mZzhifubao.setOnClickListener(this);
		mBgzhifubao=(RelativeLayout) findViewById(R.id.rechrageactivity_bg_zhifubao);
		mWeixin=(ImageView) findViewById(R.id.rechrageactivity_imageview_weixin);
		mWeixin.setOnClickListener(this);
		mBgweixin=(RelativeLayout) findViewById(R.id.rechrageactivity_bg_weixin);
		mPay=(TextView) findViewById(R.id.rechrageactivity_textview_querenzhifu);
		mPay.setOnClickListener(this);
		mBgzhifubao.setBackgroundColor(Color.WHITE);
		mBgweixin.setBackgroundColor(Color.WHITE);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rechrageactivity_imageView_callback://返回
			RechargeActivity.this.finish();
			break;
		case R.id.rechrageactivity_imageView_home://主页
			RechargeActivity.this.finish();
			startActivity(new Intent(RechargeActivity.this,MainActivity.class));
			break;
		case R.id.rechrageactivity_imageview_zhifubao://支付宝
			//进行背景填充
			TAG="ZFB";
			String str=mJine.getText().toString();
			mcurrentrechange.setText("￥"+str);
			mBgzhifubao.setBackgroundColor(Color.GRAY);
			mBgweixin.setBackgroundColor(Color.WHITE);
			//改变支付说明
			mPayclass.setText("你选择了支付宝支付");
			break;
		case R.id.rechrageactivity_imageview_weixin://威信
			TAG="WX";
			String strs=mJine.getText().toString();	
			mcurrentrechange.setText("￥"+strs);
			mBgweixin.setBackgroundColor(Color.GRAY);
			mBgzhifubao.setBackgroundColor(Color.WHITE);
			mPayclass.setText("你选择了微信支付");
			break;
		case R.id.rechrageactivity_textview_querenzhifu://支付
			mBgzhifubao.setBackgroundColor(Color.WHITE);
			mBgweixin.setBackgroundColor(Color.WHITE);
			if(TAG.equals("ZFB")){
				Ali_Pay ali = new Ali_Pay(RechargeActivity.this);
//				String []sa = price.split(".");
//				int p = Integer.parseInt(sa[0])*100+Integer.parseInt(sa[1]);

				if(mJine.getText().toString().equals("")){
					Toast.makeText(RechargeActivity.this,"请输入正确金额", 1).show();
				}else{
					ali.pay("支付", "008909980608698","余额充值",mJine.getText().toString());
				}
			}else if(TAG.equals("WX")){
				
			}
			break;
			

		default:
			break;
		}
	}
	private void getdatatixianedu() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "finance");
		params.addBodyParameter("part", "user_finance_91");
		params.addBodyParameter("user_name", SysApplication.getInstance().getUserInfo().getName());
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
		        	Log.i("caiwu 请求下来的参数是",str);
		        	
//		        	这里接口有问题，需要改动
		        	try {
						JSONObject obj=new JSONObject(str);
						JSONObject objs=obj.getJSONObject("data");
						String banace=objs.getString("user_balance");
						mCurrentPrice.setText("￥"+banace);
						String user_conbalance=objs.getString("user_conbalance");
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

}
