package com.alljf.jf.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.alljf.jf.R;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015-12-29 下午10:04:52 
 * 类说明   确认收货界面
 */
public class SureTakeGoodsActivity extends Activity {

	private EditText mPassword;//密码
	private TextView mDingdanhao;//订单号
	private TextView mSure;//确认
	private ImageView mBack;
	private ImageView mHome;
	/**
	 * 进度条
	 */
	private SpotsDialog mdialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_confirmationtakegoods);
		SysApplication.getInstance().addActivity(this);
		initview();
		initdata();
		
		
	}
	
	/**
	 * @2015-12-29下午10:16:57
	 * @author JZKJ-LWC
	 * 初始化数据
	 */
	private void initdata() {
		// TODO Auto-generated method stub
		Intent intent=getIntent();
		final String orderid=intent.getStringExtra("order");
		mDingdanhao.setText(orderid);
		if(mPassword.getText().toString()==null){
			Toast.makeText(SureTakeGoodsActivity.this,"请输入支付密码",1).show();
		}else{
			mSure.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getdata(orderid, mPassword.getText().toString());
				}
			});
		}
		
	}

	/**
	 * @2015-12-29下午10:12:53
	 * @author JZKJ-LWC
	 * @category 用来初始化ui控件
	 */
	private void initview() {
		// TODO Auto-generated method stub
		SpotsDialog.TAG=R.style.SpotsDialogDefault_tijiao;
		mdialog=new SpotsDialog(SureTakeGoodsActivity.this);
		mdialog.setCanceledOnTouchOutside(false);
		mPassword=(EditText) findViewById(R.id.confirmationtakegoods_mimakuang);
		mDingdanhao=(TextView) findViewById(R.id.confirmationtakegoods_dingdanbianhao);
		mSure=(TextView) findViewById(R.id.confirmationtakegoods_queren);
		mBack=(ImageView) findViewById(R.id.confirmationtakegoods_back);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SureTakeGoodsActivity.this.finish();
			}
		});		
		mHome=(ImageView) findViewById(R.id.confirmationtakegoods_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SureTakeGoodsActivity.this.finish();
				startActivity(new Intent(SureTakeGoodsActivity.this,MainActivity.class));
			}
		});
	}
	
	/**
	 * @2015-12-29下午10:24:49
	 * @author JZKJ-LWC
	 * 进行收货处理
	 */
	public void getdata(String id,String password){
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "receive_order");
		params.addBodyParameter("user_name",SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("order_id", id);
		params.addBodyParameter("pay_password",password);
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
		        	Log.i("确认收货界面数据返回", str);
		        	try {
						JSONObject obj=new JSONObject(str);
						String strs=obj.getString("status");
						if(strs.equals("0")){
							Toast.makeText(SureTakeGoodsActivity.this,"确认失败",1).show();
						}else{
							Toast.makeText(SureTakeGoodsActivity.this,"确认成功",1).show();
							SureTakeGoodsActivity.this.finish();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}

	
}
