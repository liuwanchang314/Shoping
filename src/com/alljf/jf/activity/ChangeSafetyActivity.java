package com.alljf.jf.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Application.SysApplication;
import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.bean.ChangeSafetBean;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 上午11:24:22
 * 修改安全信息界面
 */  
public class ChangeSafetyActivity extends Activity {
	
	private ImageView mBack,mHome;
	private TextView mQuestion;//问题按钮
	private EditText mAnswer;//答案
	private EditText mNewpw;//新密码
	private EditText mSpd;//确认密码
	private TextView mTG;//提交
	private ActionBar actionbar;
	private SpotsDialog mdialog;
	private String username;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_changesafety);
		SysApplication.getInstance().addActivity(this);
		Intent intent=getIntent();
		username=intent.getStringExtra("username");
		initview();
		
	}
	/**
	 * 修改密码需要三个参数
	 * 
	 * */
	private void getdata() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "update_password");
		params.addBodyParameter("username",username);
		params.addBodyParameter("password",mNewpw.getText().toString());
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
		        	Log.i("网络请求下来的参数是",str);
		        		//不做任何操作，选择默认图片
		        	ChangeSafetBean bean=getbean(str);
		        	if(bean.getApi().equals("APISUCCESS")||bean.getStatus().equals("1")){
		        		//说明修改成功
		        		AlertDialog.Builder builder=new AlertDialog.Builder(ChangeSafetyActivity.this);
						builder.setTitle("温馨提示");
						builder.setMessage("由于您当前修改了密码，请重新进行登陆");
						builder.setPositiveButton("登录",new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(ChangeSafetyActivity.this, LoginActivity.class);
								startActivityForResult(intent, CommonConstants.SHOP_CARD);
								ChangeSafetyActivity.this.finish();
							}
						});
						
						builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						});
						builder.create().show();
		        	}
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-11 上午11:43:52
	 */  
	private void initview() {
		SpotsDialog.TAG=R.style.SpotsDialogDefault_tijiao;
		mdialog=new SpotsDialog(ChangeSafetyActivity.this);
		mdialog.setCanceledOnTouchOutside(false);
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.changesafety_back);
		mHome=(ImageView) findViewById(R.id.changesafety_home);
		mQuestion=(TextView) findViewById(R.id.changesafety_wentianniu);
		mAnswer=(EditText) findViewById(R.id.changesafety_daan);
		mNewpw=(EditText) findViewById(R.id.changesafety_newpaypassword);
		mSpd=(EditText) findViewById(R.id.changesafety_argainpaypassword);
		mTG=(TextView) findViewById(R.id.changesafety_go);
		mTG.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getdata();
			}
		});
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ChangeSafetyActivity.this.finish();
			}
		});
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ChangeSafetyActivity.this.finish();
				Intent intent=new Intent(ChangeSafetyActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public ChangeSafetBean getbean(String str){
		ChangeSafetBean bean=new ChangeSafetBean();
		try {
			JSONObject obj=new JSONObject(str);
			bean.setApi(obj.getString("api"));
			bean.setStatus(obj.getString("status"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bean;
		
	}

}
