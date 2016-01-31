package com.alljf.jf.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
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
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 下午3:52:55
 * 提现
 */  
public class WithDrawAcivity extends Activity implements OnClickListener{
	
	private ImageView mBack;
	private ImageView mHome;//
	private TextView mCurrent;//当前余额
	private EditText mMoney;//要提现的金额
	private EditText mBankName;//银行名称
	private EditText mName;//开户名成
	private EditText mBranchName;//分行名称
	private EditText mNumber;//卡号
	private TextView mSure;//确认提现
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_withdraw);
		SysApplication.getInstance().addActivity(this);
		initview();
		//首先，在进入该界面以后，先要网络获取用户的可提现额度
		getdatatixianedu();
	}
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.withdraw_back);
		mBack.setOnClickListener(this);
		mHome=(ImageView) findViewById(R.id.withdraw_home);
		mHome.setOnClickListener(this);
		mCurrent=(TextView) findViewById(R.id.withdraw_ketixianjine);
		mMoney=(EditText) findViewById(R.id.withdraw_yaotixianjine);
		mBankName=(EditText) findViewById(R.id.withdraw_yinhangmingcheng);
		mName=(EditText) findViewById(R.id.withdraw_xingming);
		mBranchName=(EditText) findViewById(R.id.withdraw_kaihuhangmingcheng);
		mNumber=(EditText) findViewById(R.id.withdraw_yinhangzhanghao);
		mSure=(TextView) findViewById(R.id.withdraw_querentixian);
		mSure.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.withdraw_back:
			WithDrawAcivity.this.finish();
			break;
		case R.id.withdraw_home:
			WithDrawAcivity.this.finish();
			startActivity(new Intent(WithDrawAcivity.this,MainActivity.class));
			break;
		case R.id.withdraw_querentixian:
			if(mMoney.getText().toString().equals("")){
				Toast.makeText(WithDrawAcivity.this,"请检查金额",1).show();
			}else{
				if(mBankName.getText().toString().equals("")){
					Toast.makeText(WithDrawAcivity.this,"请输入银行名称",1).show();
				}else{
					if(mNumber.getText().toString().equals("")){
						Toast.makeText(WithDrawAcivity.this,"请输入银行卡号",1).show();
					}else{
						if(mName.getText().toString().equals("")){
							Toast.makeText(WithDrawAcivity.this,"请输入姓名",1).show();
						}else{
							if(mBranchName.getText().toString().equals("")){
								Toast.makeText(WithDrawAcivity.this,"请输入分行名称",1).show();
							}else{
								getdatatixian();
							}
						}
					}
				}
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
						mCurrent.setText(banace);
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
	private void getdatatixian() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "finance");
		params.addBodyParameter("part", "do_deposit_91");
		params.addBodyParameter("user_name", SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("deposit_price", mMoney.getText().toString());
		params.addBodyParameter("deposit_type","1");
		params.addBodyParameter("deposit_bankname",mBankName.getText().toString());
		params.addBodyParameter("deposit_bankcode",mNumber.getText().toString());
		params.addBodyParameter("deposit_bankuser",mName.getText().toString());
		params.addBodyParameter("deposit_bankaddress", mBranchName.getText().toString());
		params.addBodyParameter("deposit_alipay","");
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
		        	Log.i("tixian请求下来的参数是",str);
		        	try {
						JSONObject obj=new JSONObject(str);
						String status=obj.getString("status");
						if(status!=null&&status.equals("1")){
							Toast.makeText(WithDrawAcivity.this,"成功",1).show();
						}else if(status!=null&&status.equals("0")){
							Toast.makeText(WithDrawAcivity.this,"失败",1).show();
						}else if(status!=null&&status.equals("2")){
							Toast.makeText(WithDrawAcivity.this,"余额不足",1).show();
						}
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
