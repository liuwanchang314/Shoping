package com.alljf.jf.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.alljf.jf.R.string;
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
 * @date : 2015-12-10 下午11:12:54
 * 支付密码找回界面
 */  
public class PayPassWordFindBackActivity extends Activity implements OnClickListener{
	
	private ImageView mBack,mHome;
	private EditText mPhone;//
	private EditText mYanzm;//yanzhengma
	private EditText mNewPassword;	
	private EditText mSurewore;//确认密码
	private TextView mTijiao;
	private TextView mHuoquyanzhengma;//获取验证码
	private int i;
	private SpotsDialog mdialog;
	private String Yanzhengma;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_paypasswordfindback);
		SysApplication.getInstance().addActivity(this);
		initview();
	}

	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-11 上午10:56:49
	 */  
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		SpotsDialog.TAG=R.style.SpotsDialogDefault_tijiao;
		mdialog=new SpotsDialog(PayPassWordFindBackActivity.this);
		mdialog.setCanceledOnTouchOutside(false);
		mBack=(ImageView) findViewById(R.id.passwordfindback_back);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PayPassWordFindBackActivity.this.finish();
			}
		});
		mHome=(ImageView) findViewById(R.id.passwordfindback_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PayPassWordFindBackActivity.this.finish();
				startActivity(new Intent(PayPassWordFindBackActivity.this,MainActivity.class));
			}
		});
		mPhone=(EditText) findViewById(R.id.passwordfindback_phone);
		mYanzm=(EditText) findViewById(R.id.passwordfindback_yanzhengma);
		mNewPassword=(EditText) findViewById(R.id.passwordfindback_newpaypassword);
		mSurewore=(EditText) findViewById(R.id.passwordfindback_argainpaypassword);
		mTijiao=(TextView) findViewById(R.id.passwordfindback_go);
		mTijiao.setOnClickListener(this);
		mHuoquyanzhengma=(TextView) findViewById(R.id.passwordfindback_huoquyanzhengma);
		mHuoquyanzhengma.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.passwordfindback_huoquyanzhengma:
			mHuoquyanzhengma.setClickable(false);
			if(!TextUtils.isEmpty(mPhone.getText().toString())){
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for(i=59;i>=0;i--)
						{
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							mHuoquyanzhengma.post(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									mHuoquyanzhengma.setText(i+"");
								}
							});
							
						}
						mHuoquyanzhengma.post(new Runnable() {
							
							@Override
							public void run() {
								mHuoquyanzhengma.setText("重新获取");
							}
						});
					}
				}).start();
				//调用方法，获取验证码
				getyanzhengma(mPhone.getText().toString());
			}else{
				Toast.makeText(PayPassWordFindBackActivity.this,"请输入手机号",1).show();
			}
			
			
			break;
		case R.id.passwordfindback_go:
			//获取界面数据，调用方法，进行密码修改
			//先匹配验证码
			String code=mYanzm.getText().toString();
			String newPW=mNewPassword.getText().toString();
			String oldPW=mSurewore.getText().toString();
			if(code.equals(Yanzhengma)){
				if(newPW.equals(oldPW)){
					getdatachange(oldPW);
				}else{
					Toast.makeText(PayPassWordFindBackActivity.this,"密码不一致",1).show();
				}
			}else{
				Toast.makeText(PayPassWordFindBackActivity.this,"请输入正确的验证码",1).show();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * @2016-1-7下午11:09:56
	 * 提交修改
	 */
	private void getdatachange(String oldPW) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "update_pay_password");
		params.addBodyParameter("username",SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("password", oldPW);
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
					try {
						JSONObject obj=new JSONObject(str);
						String status=obj.getString("status");
						if(status.equals("1")){
							Toast.makeText(PayPassWordFindBackActivity.this,"修改成功",1).show();
							PayPassWordFindBackActivity.this.finish();
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        	Toast.makeText(PayPassWordFindBackActivity.this,"请输入正确的手机号",1).show();
		        }
		});
	}

	/**
	 * @2016-1-7下午10:53:41
	 * 获取验证码
	 */
	private void getyanzhengma(String phone) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "system");
		params.addBodyParameter("part", "message");
		params.addBodyParameter("mobilphone",phone );
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
		        	try {
						JSONObject obj=new JSONObject(str);
						String status=obj.getString("send_status");
						if(status.equals("1")){
							Toast.makeText(PayPassWordFindBackActivity.this,"已发送",1).show();
						}else{
							Toast.makeText(PayPassWordFindBackActivity.this,"请输入正确的手机号",1).show();
						}
						Yanzhengma=obj.getString("message_code");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        	Toast.makeText(PayPassWordFindBackActivity.this,"请输入正确的手机号",1).show();
		        }
		});
	}

}
