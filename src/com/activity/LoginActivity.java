package com.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.Application.SysApplication;
import com.Extension.DataService;
import com.Model.UserInfo;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tvone = (TextView) findViewById(R.id.login_tv_one);// 第一行标题
		tvtwo = (TextView) findViewById(R.id.login_tv_two);// 第二行标题
		codetwo = (TextView) findViewById(R.id.login_code_two);// 第二行验证码&显示
		txtone = (EditText) findViewById(R.id.login_txt_one);// 第一行文本
		txttwo = (EditText) findViewById(R.id.login_txt_two);
		btn_left = (Button) findViewById(R.id.login_btn_left);
		btn_submit = (Button) findViewById(R.id.login_btn_submit);
		btn_right = (Button) findViewById(R.id.login_btn_right);
		btn_reg = (Button) findViewById(R.id.login_btn_reg);
		getPwd = (TextView) findViewById(R.id.login_tv_getPwd);
		check = (CheckBox) findViewById(R.id.login_check_pwd);
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
				try {

					jsonObject = new JSONObject(msg.obj.toString());
					data = jsonObject.getString("login_user_status");
					if (data.equals("1")) {
						UserInfo model = new UserInfo();
						String txtoneString = txtone.getText().toString()
								.trim();
						String txttwoString = txttwo.getText().toString()
								.trim();
						if (check.isChecked()) {
							SharedPreferences share = getSharedPreferences(
									"log_share", MODE_PRIVATE);
							Editor editor = share.edit();
							editor.putString("log_name", txtoneString);
							editor.commit();
						}
						model.setName(txtoneString);
						model.setPassWord(txttwoString);
						application.addUserInfo(model);
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(getApplicationContext(), "用户名或密码错误", 2)
								.show();
						return;
					}
				} catch (Exception exp) {

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
				break;
			case R.id.login_btn_submit:
				Login();
				break;
			case R.id.login_btn_reg:
				break;
			case R.id.login_code_two:
				isPassWord();
				break;
			case R.id.login_tv_getPwd:
				Intent intent = new Intent(getApplicationContext(),
						RetrieveActivity.class);
				startActivity(intent);
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

	private void Login() {
		String txtoneString = txtone.getText().toString().trim();
		String txttwoString = txttwo.getText().toString().trim();
		if (ViewState) {
			list.put("type", "user");
			list.put("part", "userlogin");
			list.put("username", txtoneString);
			list.put("password", txttwoString);
			client = new DataService(handler, 0, list);
			client.start();
		} else {

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
			
		}
	}

	private void setting_one() {
		tvone.setText("手机号码");
		tvtwo.setText("手机验证码");
		codetwo.setText("获取验证码");
		txtone.setHint("输入手机号码");
		txttwo.setHint("输入验证码");
		btn_left.setText("账号登录");
		isPassWord();
		txttwo.setText("");
		txtone.setText("");
	}

	private void setting_two() {
		tvone.setText("账户");
		tvtwo.setText("登录密码");
		codetwo.setText("显示");
		txtone.setHint("手机/会员名/邮箱");
		txttwo.setHint("请输入密码");
		btn_left.setText("手机号码登录");
		isPassWord();
		txttwo.setText("");
		txtone.setText("");
	}
}
