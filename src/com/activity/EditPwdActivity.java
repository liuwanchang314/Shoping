package com.activity;

import java.util.HashMap;

import org.json.JSONObject;

import com.Application.SysApplication;
import com.Extension.DataService;
import com.Model.UserInfo;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.wifi.WifiConfiguration.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditPwdActivity extends Activity {

	Button btn_submit, btn_back;
	DataService client;
	Handler handler;
	HashMap<String, String> list = new HashMap<String, String>();
	String UserName;
	SysApplication Application = SysApplication.getInstance();
	EditText txtone, txttwo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_pwd);
		UserName = Application.getUserInfo().getName();
		txtone = (EditText) findViewById(R.id.editpwd_txt_one);
		txttwo = (EditText) findViewById(R.id.editpwd_txt_two);
		btn_submit = (Button) findViewById(R.id.editpwd_btn_submit);
		btn_back = (Button) findViewById(R.id.editpwd_btn_back);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				onBackPressed();
			}
		});
		btn_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				String pwd = txtone.getText().toString().trim();
				String pwd1 = txttwo.getText().toString().trim();
				if (!pwd.equals(pwd1)) {
					Toast.makeText(getApplicationContext(), "两次密码不一致", 2)
							.show();
					return;
				}
				list.put("type", "user");
				list.put("part", "update_password");
				list.put("username", UserName);
				list.put("password", pwd);
				client = new DataService(handler, 0, list);
				client.start();
			}
		});
		handler = new Handler() {
			public void handleMessage(Message msg) {
				String data = "";
				JSONObject jsonObject;
				try {

					jsonObject = new JSONObject(msg.obj.toString());
					data = jsonObject.getString("status");
					if (data.equals("1")) {

						Toast.makeText(getApplicationContext(), "重置成功", 2)
								.show();
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(getApplicationContext(), "重置失败", 2)
								.show();
						return;
					}
				} catch (Exception exp) {
				}
			}
		};
	}

}
