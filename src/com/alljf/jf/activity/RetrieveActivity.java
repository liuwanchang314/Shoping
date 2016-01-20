package com.alljf.jf.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.Application.SysApplication;
import com.Extension.DataService;
import com.Model.RelInfo;
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

/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 下午1:31:57
 *找回密码界面
 */  
public class RetrieveActivity extends Activity {

	Spinner spinner;
	List<RelInfo> mlist = new ArrayList<RelInfo>();
	DataService client;
	Handler handler;
	HashMap<String, String> list = new HashMap<String, String>();
	private String name;
	private Button back;
	private TextView question;
	private Button tijiao;
	private EditText daan;
	private SpotsDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_retrieve);
		SysApplication.getInstance().addActivity(this);
		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		SpotsDialog.TAG=R.style.SpotsDialogDefault;
		dialog=new SpotsDialog(RetrieveActivity.this);
		Log.i("用户民是",name);
		back=(Button) findViewById(R.id.ret_btn_back);
		question=(TextView) findViewById(R.id.zhaohuimima_wenti);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		tijiao=(Button) findViewById(R.id.ret_btn_submit);
		daan=(EditText) findViewById(R.id.ret_txt_two);
		getdate();
	}
	private void getdate() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				RequestParams params = new RequestParams();
				// 只包含字符串参数时默认使用BodyParamsEntity，
				params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
				params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
				params.addBodyParameter("type", "user");
				params.addBodyParameter("part", "get_userinfo");
				params.addBodyParameter("username",name);
				HttpUtils http = new HttpUtils();
				http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

				        @Override
				        public void onStart() {
				        	//开始请求
				        	dialog.show();
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
				        	dialog.dismiss();
				        	String str=responseInfo.result;
				        	Log.i("找回密码了没有", str);
				        	try {
								JSONObject obj=new JSONObject(str);
								JSONObject objs=obj.getJSONObject("data");
								final String answer=objs.getString("answer");
								String questions=objs.getString("question");
								if(questions.equals("1")){
									question.setText("你的小学叫什么");
								}else if(questions.equals("2")){
									question.setText("你家乡的名称是什么");
								}else if(questions.equals("3")){
									question.setText("你最喜欢的格言是什么");
								}else if(questions.equals("4")){
									question.setText("你最喜欢的歌曲是什么");
								}else if(questions.equals("5")){
									question.setText("你最喜欢的偶像叫什么");
								}else if(questions.equals("5")){
									question.setText("你的母亲叫什么名字");
								}
								tijiao.setOnClickListener(new OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										if(daan.getText().toString().equals(answer)){
											//说明匹配成功
											Intent intent=new Intent(RetrieveActivity.this,ChangeSafetyActivity.class);
											intent.putExtra("username",name);
											startActivity(intent);
											RetrieveActivity.this.finish();
										}
									}
								});
								
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
