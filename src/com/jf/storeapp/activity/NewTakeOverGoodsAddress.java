package com.jf.storeapp.activity;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.jf.storeapp.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wheelview.CitiesActivity;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 下午1:44:33
 * 新建收货地址界面
 */  

public class NewTakeOverGoodsAddress extends Activity{
	
	private ImageView mBack,mHome;
	private EditText mName;
	private EditText mPhone;
	private EditText mYoubian;
	private RelativeLayout mSSX;//省市县
	private EditText mXXaddress;
	private TextView mSave;//保存
	
	private String province;
	private String city;
	private String district;
	
	/**
	 * 当前省的名称
	 */
	private String mCurrentProviceName;
	private String mCurrentProviceid;
	/**
	 * 当前市的名称
	 */
	private String mCurrentCityName;
	private String mCurrentCityid;
	
	/**
	 * 当前区的名称
	 */
	private String mCurrentAreaName;
	
	private Button button;
	
	private TextView shengshixian;

	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_newtaskeovergoodsaddress);
		initview();
		//省市县被点击的时候，弹出布局来进行处理
		mSSX.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(NewTakeOverGoodsAddress.this,CitiesActivity.class);
				startActivityForResult(intent, 2);
			}
		});
	}
	
	//获取用户用户名
	private String getUserName() {
			return SysApplication.getInstance().getUserInfo().getName();
		}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-11 下午2:16:13
	 */  
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.newtakeoveraddress_back);
		mHome=(ImageView) findViewById(R.id.newtakeoveraddress_home);
		mName=(EditText) findViewById(R.id.newtakeoveraddress_name);
		mPhone=(EditText) findViewById(R.id.newtakeoveraddress_phone);
		mYoubian=(EditText) findViewById(R.id.newtakeoveraddress_youzhenbianma);
		mXXaddress=(EditText) findViewById(R.id.newtakeoveraddress_detail_address);
		mSSX=(RelativeLayout) findViewById(R.id.newtakeoveraddress_shengshixian);
		mSave=(TextView) findViewById(R.id.newtakeoveraddress_save);
		shengshixian=(TextView) findViewById(R.id.newtake_shengshixian);
	}
	//获取省
	public void getprovince(String name,String phone,String youbian,String pid,String cid,String did,String mxx){
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "add_post_address");
		params.addBodyParameter("username",SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("tel_phone",phone);
		params.addBodyParameter("mob_phone",phone);
		params.addBodyParameter("realname",name);
		params.addBodyParameter("province",pid);
		params.addBodyParameter("city",cid);
		params.addBodyParameter("district",did);
		params.addBodyParameter("address",mxx);
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
		        	province=responseInfo.result;
		        	Log.i("网络请求下来的shengfen 参数是",province);
		        	
		        	
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
			if(resultCode==2){
				//说明是从地址选择界面传递过来的数据
				String pname=data.getStringExtra("pname");
				String cname=data.getStringExtra("cname");
				String dname=data.getStringExtra("dname");
				final String pid=data.getStringExtra("pid");
				final String cid=data.getStringExtra("cid");
				final String did=data.getStringExtra("did");
				Log.i("这里有值吗",pname);
				shengshixian.setText(pname+"-"+cname+"-"+dname);
				mSave.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(mName.getText().equals("")){
							Toast.makeText(NewTakeOverGoodsAddress.this,"姓名不能为空",1).show();
						}
						if(mPhone.getText().equals("")||mPhone.getText().length()<11){
							Toast.makeText(NewTakeOverGoodsAddress.this,"请检查手机号",1).show();
						}
						if(mYoubian.getText().equals("")||mYoubian.getText().length()!=6){
							Toast.makeText(NewTakeOverGoodsAddress.this,"邮编格式错误",1).show();
						}
						if(mXXaddress.getText().equals("")){
							Toast.makeText(NewTakeOverGoodsAddress.this,"请输入详细地址",1).show();
						}
						//现在开始网络请求，添加新地址
						getprovince(mName.getText().toString(), mPhone.getText().toString(), mYoubian.getText().toString(), pid, cid, did, mXXaddress.getText().toString());
						Intent intent=new Intent();
						NewTakeOverGoodsAddress.this.setResult(1,intent);
						NewTakeOverGoodsAddress.this.finish();
					}
				});
				
			}
		}


}