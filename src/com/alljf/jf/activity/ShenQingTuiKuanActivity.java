package com.alljf.jf.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.Application.SysApplication;
import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.bean.ChangeSafetBean;
import com.bean.OrderBean;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-12 下午9:32:09 
 * 类说明 
 */
public class ShenQingTuiKuanActivity extends Activity implements OnClickListener{
	
	private TextView mFuwuleixing;
	private TextView mHuowuzhuangtai;
	private TextView mTijiaoshenqing;
	private EditText mTuikuanshuoming;
	private RelativeLayout mXuanzefuwu;
	private RelativeLayout mXuanzehuowuzhuangtai;
	private ImageView mback,mhome;
	private OrderBean bean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shengqingtuikuan);
		initview();
		Intent intent=getIntent();
		bean=(OrderBean) intent.getSerializableExtra("bean");
	}

	/**
	 * 初始化view
	 * @2016-1-12下午9:33:10
	 */
	private void initview() {
		// TODO Auto-generated method stub
		mFuwuleixing=(TextView) findViewById(R.id.shenqingtuikuan_fuwu);
		mHuowuzhuangtai=(TextView) findViewById(R.id.shenqingtuikuan_huowuzhuangtai);
		mTijiaoshenqing=(TextView) findViewById(R.id.shenqingtuikuan_tijiaoshenqing);
		mTijiaoshenqing.setOnClickListener(this);
		mTuikuanshuoming=(EditText) findViewById(R.id.shenqingtuikuan_tuikuanshuoming);
		mXuanzefuwu=(RelativeLayout) findViewById(R.id.shenqingtuikuan_xuanzefuwu);
		mXuanzefuwu.setOnClickListener(this);
		mXuanzehuowuzhuangtai=(RelativeLayout) findViewById(R.id.shenqingtuikuan_xuanzehuowuzhuangtai);
		mXuanzehuowuzhuangtai.setOnClickListener(this);
		mback=(ImageView) findViewById(R.id.shenqingtuikuan_back);
		mback.setOnClickListener(this);
		mhome=(ImageView) findViewById(R.id.shenqingtuikuan_home);
		mhome.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.shenqingtuikuan_xuanzefuwu:
			Intent intent=new Intent(ShenQingTuiKuanActivity.this,XuanzefuwuActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.shenqingtuikuan_xuanzehuowuzhuangtai:
			
			break;
		case R.id.shenqingtuikuan_back:
			ShenQingTuiKuanActivity.this.finish();
			break;
		case R.id.shenqingtuikuan_home:
			ShenQingTuiKuanActivity.this.finish();
			startActivity(new Intent(ShenQingTuiKuanActivity.this,MainActivity.class));
			break;
		case R.id.shenqingtuikuan_tijiaoshenqing:
			//调用方法，进行退款
			getdata();
		default:
			break;
		}
	}
	
	//获取用户用户名
			private String getUserName() {
				return SysApplication.getInstance().getUserInfo().getName();
			}
	/**
	 * 
	 * @2016-1-12下午10:25:59
	 * 用来提交数据到服务器进行退款处理
	 */
	private void getdata() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "refund_all");
		params.addBodyParameter("user_name", getUserName());
		params.addBodyParameter("order_id",bean.getOrder_id());
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
		        	try {
						//请求成功
						String str=responseInfo.result;
						Log.i("退款请求下来的参数是",str);
						JSONObject obj=new JSONObject(str);
						String status=obj.getString("status");
						if(status.equals("0")){
							//失败
							Toast.makeText(ShenQingTuiKuanActivity.this,"申请失败，请稍候重试",1).show();
						}else if(status.equals("1")){
							//成功
							Toast.makeText(ShenQingTuiKuanActivity.this,"退款成功",1).show();
							ShenQingTuiKuanActivity.this.finish();
						}else if(status.equals("2")){
							Toast.makeText(ShenQingTuiKuanActivity.this,"已提交，正在处理中",1).show();
							ShenQingTuiKuanActivity.this.finish();
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (resultCode) {
		case 1:
			//是从选择服务界面过来的数据
			String fuwu=data.getStringExtra("fuwu");
			mFuwuleixing.setText(fuwu);
			break;

		default:
			break;
		}
	}
	

}
