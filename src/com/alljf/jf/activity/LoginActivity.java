package com.alljf.jf.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.Extension.DataService;
import com.Model.UserInfo;
import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class LoginActivity extends Activity {

	SysApplication application = SysApplication.getInstance();
	boolean ViewState = false;// true:账号 false:手机
	boolean isPassWord = true;
	TextView tvone, tvtwo, codetwo, getPwd;
	EditText txtone, txttwo;
	CheckBox check;
	Handler handler;
	HashMap<String, String> list = new HashMap<String, String>();
	Button btn_submit, btn_reg, btn_left, btn_right;
	DataService client;
	private String message_code = "";
	private Map<String, String> userMap = new HashMap<String, String>();

	private String name;
	private String pwd;
	
	private IWXAPI api;
	private int i;//读秒计数
	private Button back;
	private SpotsDialog mdialog;
	/**
	 * 动态改变按钮数字的线程
	 */
	private Thread thread;
	@Override
	protected void onResume() {
		super.onResume();
		getUserMapFromSP();
	}
	
	private void getUserMapFromSP(){
		SharedPreferences share = getSharedPreferences(
				"log_share", MODE_PRIVATE);
		name = share.getString("log_name", "");
		userMap.put("name", name);
		pwd = share.getString("log_pwd", "");
		userMap.put("pwd", pwd);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		SysApplication.getInstance().addActivity(this);
		api = WXAPIFactory.createWXAPI(this, CommonConstants.WXAPP_ID, true);
		SpotsDialog.TAG=R.style.SpotsDialogDefault_denglu;
		mdialog=new SpotsDialog(LoginActivity.this);
		tvone = (TextView) findViewById(R.id.login_tv_one);// 第一行标题
		tvtwo = (TextView) findViewById(R.id.login_tv_two);// 第二行标题
		codetwo = (TextView) findViewById(R.id.login_code_two);// 第二行验证码&显示
		txtone = (EditText) findViewById(R.id.login_txt_one);// 第一行文本
		back=(Button) findViewById(R.id.login_btn_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoginActivity.this.finish();
				startActivity(new Intent(LoginActivity.this,MainActivity.class));
			}
		});
		txttwo = (EditText) findViewById(R.id.login_txt_two);
		btn_left = (Button) findViewById(R.id.login_btn_left);
		btn_submit = (Button) findViewById(R.id.login_btn_submit);
		btn_right = (Button) findViewById(R.id.login_btn_right);
		btn_reg = (Button) findViewById(R.id.login_btn_reg);
		getPwd = (TextView) findViewById(R.id.login_tv_getPwd);
		check = (CheckBox) findViewById(R.id.login_check_pwd);
		txtone.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				String ss = txtone.getText().toString();
					if(ss.equals(name)){
						txttwo.setText(pwd);
					}
				
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		codetwo.setOnClickListener(new onClick());
		btn_left.setOnClickListener(new onClick());
		btn_right.setOnClickListener(new onClick());
		btn_submit.setOnClickListener(new onClick());
		btn_reg.setOnClickListener(new onClick());
		getPwd.setOnClickListener(new onClick());
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String data = "";
				JSONObject jsonObject;
				if(msg.arg1 == 0){
					mdialog.dismiss();
					try {

						jsonObject = new JSONObject(msg.obj.toString());
						data = jsonObject.getString("login_user_status");
						if (data.equals("1")) {
							UserInfo model = UserInfo.getInstance();
							String txtoneString = txtone.getText().toString()
									.trim();
							String txttwoString = txttwo.getText().toString()
									.trim();
							if (check.isChecked()) {
								SharedPreferences share = getSharedPreferences(
										"log_share", MODE_PRIVATE);
								Editor editor = share.edit();
								editor.putString("log_name", txtoneString);
								editor.putString("log_pwd", txttwoString);
								editor.commit();
							}else{
								SharedPreferences share = getSharedPreferences(
										"log_share", MODE_PRIVATE);
								Editor editor = share.edit();
								editor.putString("log_name", "");
								editor.putString("log_pwd", "");
								editor.commit();
							}
							model.setName(txtoneString);
							model.setPassWord(txttwoString);
							application.addUserInfo(model);
							setResult(RESULT_OK);
							finish();
						} else {
							Toast.makeText(getApplicationContext(), "用户名或密码错误", 1)
									.show();
							return;
						}
					} catch (Exception exp) {

					}
				}else if(msg.arg1 == 1){
					try {
						jsonObject = new JSONObject(msg.obj.toString());
						data = jsonObject.getString("send_status");
						if(data.equals("1")){
							message_code = jsonObject.getString("message_code");
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
			}
		};
	}

	public class onClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.login_btn_left:
				LeftClint();
				break;
			case R.id.login_btn_right:
				rightClint();
				break;
			case R.id.login_btn_submit:
				mdialog.show();
				Login();
				break;
			case R.id.login_btn_reg:
				Intent intent1 = new Intent(getApplicationContext(),
						RegnActivity.class);
				startActivity(intent1);
				break;
			case R.id.login_code_two:
				isPassWord();
				break;
			case R.id.login_tv_getPwd:
				if(txtone.getText().toString().equals("")){
					Toast.makeText(LoginActivity.this, "用户名不能为空", 0).show();
					return;
				}
				Intent intent = new Intent(getApplicationContext(),
						RetrieveActivity.class);
				intent.putExtra("name", txtone.getText().toString());
				startActivity(intent);
				break;
			default:
				break;
			}
		}

	}

	private void LeftClint() {
		if (ViewState) {
			ViewState = false;
			setting_one();
		} else {
			ViewState = true;
			setting_two();
		}
	}

	private void rightClint(){
		Toast.makeText(this, "weixin denglu", 0).show();;
SendAuth.Req req = new SendAuth.Req();
	    req.scope = "snsapi_userinfo";
	    req.state = "wechat_sdk_demo_test";
	    api.sendReq(req);
	}
	private void Login() {
		String txtoneString = txtone.getText().toString().trim();
		String txttwoString = txttwo.getText().toString().trim();
		
		if (ViewState) {
			if(txtoneString.length() == 0 || txttwoString.length() == 0){
				Toast.makeText(getApplicationContext(), "用户名或密码不能为空", 1)
				.show();
				return;
			}
			list.clear();
			list.put("type", "user");
			list.put("part", "userlogin");
			list.put("username", txtoneString);
			list.put("password", txttwoString);
			client = new DataService(handler, 0, list);
			client.start();
		} else {
			if(txtoneString.length() == 0 || txttwoString.length() == 0){
				Toast.makeText(getApplicationContext(), "手机号码和验证码都不能为空", 1)
				.show();
				return;
			}
			if(!txttwoString.equals(message_code)){
				Toast.makeText(getApplicationContext(), "验证码错误", 1)
				.show();
				return;
			}
			list.clear();
			list.put("type", "user");
			list.put("part", "mobillogin");
			list.put("mobilphone", txtoneString);
			list.put("code", txttwoString);
			client = new DataService(handler, 0, list);
			client.start();
		}
	}

	private void isPassWord() {
		if (ViewState) {

			if (isPassWord) {
				isPassWord = false;
				txttwo.setTransformationMethod(PasswordTransformationMethod
						.getInstance());
				codetwo.setText("显示");
			} else {
				isPassWord = true;
				txttwo.setTransformationMethod(HideReturnsTransformationMethod
						.getInstance());
				codetwo.setText("隐藏");
			}
		}else {
			String txtoneString = txtone.getText().toString().trim();
			if(txtoneString.length() == 11){
				
				list.clear();
				list.put("type", "system");
				list.put("part", "message");
				list.put("mobilphone", txtoneString);
				client = new DataService(handler, 1, list);
				client.start();
				codetwo.setClickable(false);
				//同时开启一个子线程，目的是为了改变控件上的时间数字
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						//先让线程睡上1000毫秒，
						for(i=59;i>0;i--)
						{
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							codetwo.post(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									//更新ui上面的数字
									codetwo.setText(i+"");
								}
							});
							
						}
						codetwo.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								//更新ui上面的数字
								codetwo.setText("获取验证码");
								codetwo.setClickable(true);
							}
						});
					}
				}).start();
			}else{
				Toast.makeText(getApplicationContext(), "手机号码格式错误", 1)
				.show();
			}

		}
	}

	private void setting_one() {
		tvone.setText("手机号码");
		tvtwo.setText("手机验证码");
//		codetwo.setVisibility(View.VISIBLE);
		codetwo.setText("获取验证码");
		txtone.setHint("输入手机号码");
		check.setVisibility(View.GONE);
		txttwo.setHint("输入验证码");
		btn_left.setText("账号登录");
//		isPassWord();
		txttwo.setText("");
		txtone.setText("");
	}

	private void setting_two() {
		tvone.setText("账户");
		tvtwo.setText("登录密码");
		codetwo.setText("显示");
//		codetwo.setVisibility(View.GONE);
		txtone.setHint("手机/会员名/邮箱");
		txttwo.setHint("请输入密码");
		check.setVisibility(View.VISIBLE);
		btn_left.setText("手机号码登录");
		isPassWord();
		txttwo.setText("");
		txtone.setText("");
	}
}
