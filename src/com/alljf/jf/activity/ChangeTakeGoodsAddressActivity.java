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
import android.widget.LinearLayout;
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
import com.wheelview.CitiesActivity;

/**
 * @author S_arige
 * 2016年1月4日：下午9:18:31
 */
public class ChangeTakeGoodsAddressActivity extends Activity {
	
	private ImageView mBack;//返回
	private TextView mQuxiao;//取消修改
	private EditText mName;//姓名
	private EditText mPhone;//电话
	private EditText mYoubian;//邮编
	private TextView mSuozaidiqu;//所在地区
	private EditText mXXaddress;//详细地址
	private LinearLayout mDelete;//删除该地址
	private LinearLayout mSet;//设置为默认
	private TextView mSure;//确认修改
	private String name;
	private String phone;
	private String xxdz;
	private String defaults;
	private  String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_changetakegoodsaddress);
		SysApplication.getInstance().addActivity(this);
		initview();
		Intent intent=getIntent();
		name=intent.getStringExtra("name");
		phone=intent.getStringExtra("phone");
		xxdz=intent.getStringExtra("xxdz");
		defaults=intent.getStringExtra("default");
		id=intent.getStringExtra("id");
		if(defaults.equals("1")){
			//说明是修改默认地址
			mSet.setVisibility(View.GONE);
			mName.setText(name);
			mPhone.setText(phone);
			mXXaddress.setText(xxdz);
			mYoubian.setText("100100");//因为在最初获取地址信息的时候，没有邮编这个参数
			mSuozaidiqu.setText("请选择所在省市县");
			mSuozaidiqu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//界面跳转到选择地址界面
					Intent intents=new Intent(ChangeTakeGoodsAddressActivity.this,CitiesActivity.class);
					startActivityForResult(intents, 2);
					
				}
			});
			
			
		}else{
			//说明是修改普通地址
			mSet.setVisibility(View.VISIBLE);
			mName.setText(name);
			mPhone.setText(phone);
			mXXaddress.setText(xxdz);
			mYoubian.setText("100100");//因为在最初获取地址信息的时候，没有邮编这个参数
			mSuozaidiqu.setText("请选择所在省市县");
			mSuozaidiqu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//界面跳转到选择地址界面
					Intent intents=new Intent(ChangeTakeGoodsAddressActivity.this,CitiesActivity.class);
					startActivityForResult(intents, 2);
					
				}
			});
			mSet.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//设置为默认地址
					setmoren(id);
				}
			});
		}
		
		
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-18 上午10:13:57
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.changeaddress_back);
		mQuxiao=(TextView) findViewById(R.id.changeaddress_quxiao);
		mName=(EditText) findViewById(R.id.changeaddress_shouhuorennanme);
		mPhone=(EditText) findViewById(R.id.changeaddress_pbone);
		mYoubian=(EditText) findViewById(R.id.changeaddress_youzhenbianma);
		mSuozaidiqu=(TextView) findViewById(R.id.changeaddress_suozaidiqu);
		mXXaddress=(EditText) findViewById(R.id.changeaddress_xiangxidizhi);
		mDelete=(LinearLayout) findViewById(R.id.changeaddress_shanchu);
		mSet=(LinearLayout) findViewById(R.id.changeaddress_shezhiweimoren);
		mSure=(TextView) findViewById(R.id.changeaddress_tijiao);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ChangeTakeGoodsAddressActivity.this.finish();
			}
		});
		mQuxiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ChangeTakeGoodsAddressActivity.this.finish();
			}
		});
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==2){
			//说明是从地址选择界面跳转过来
			String pname=data.getStringExtra("pname");
			String cname=data.getStringExtra("cname");
			String dname=data.getStringExtra("dname");
			final String pid=data.getStringExtra("pid");
			final String cid=data.getStringExtra("cid");
			final String did=data.getStringExtra("did");
			
			mSuozaidiqu.setText(pname+"-"+cname+"-"+dname);
			mDelete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					deleteAddress(id);
					Intent intent=new Intent();
					ChangeTakeGoodsAddressActivity.this.setResult(3,intent);
					ChangeTakeGoodsAddressActivity.this.finish();
				}
			});
			mSure.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(ChangeTakeGoodsAddressActivity.this,"提交",1).show();
					//首先需要将数据提交上去，然后finsh掉这个界面，跳回地址列表界面
					if(mName.getText().equals("")){
						Toast.makeText(ChangeTakeGoodsAddressActivity.this,"姓名不能为空",1).show();
					}
					if(mPhone.getText().equals("")||mPhone.getText().length()<11){
						Toast.makeText(ChangeTakeGoodsAddressActivity.this,"请检查手机号",1).show();
					}
					if(mYoubian.getText().equals("")||mYoubian.getText().length()!=6){
						Toast.makeText(ChangeTakeGoodsAddressActivity.this,"邮编格式错误",1).show();
					}
					if(mXXaddress.getText().equals("")){
						Toast.makeText(ChangeTakeGoodsAddressActivity.this,"请输入详细地址",1).show();
					}
					updateAddress(id,mName.getText().toString(), mPhone.getText().toString(),mYoubian.getText().toString(), pid, cid, did, mXXaddress.getText().toString());
					Intent intent=new Intent();
					ChangeTakeGoodsAddressActivity.this.setResult(3,intent);
					ChangeTakeGoodsAddressActivity.this.finish();
				}
			});
		}
	}
	
	
	public void setmoren(String sid) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "default_post_address");
		params.addBodyParameter("address_id",sid);
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
		        	String status = null;
		        	Log.i("网络请求下来的参数是",str);
		        	try {
						JSONObject obj=new JSONObject(str);
						status=obj.getString("status");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	if(status.equals("1")){
		        		Toast.makeText(ChangeTakeGoodsAddressActivity.this,"设置成功",1).show();
		        	}else if(status.equals("0")){
		        		Toast.makeText(ChangeTakeGoodsAddressActivity.this,"设置失败",1).show();
		        	}
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	public void deleteAddress(String sid) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "delete_post_address");
		params.addBodyParameter("address_id",sid);
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
		        	String status = null;
		        	Log.i("网络请求下来的参数是",str);
		        	try {
						JSONObject obj=new JSONObject(str);
						status=obj.getString("status");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	if(status.equals("1")){
		        		Toast.makeText(ChangeTakeGoodsAddressActivity.this,"删除成功",1).show();
		        	}else if(status.equals("0")){
		        		Toast.makeText(ChangeTakeGoodsAddressActivity.this,"删除失败",1).show();
		        	}
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	
	public void updateAddress(String ids,String name,String phone,String youbian,String pid,String cid,String did,String mxx){
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "update_post_address");
		params.addBodyParameter("address_id",ids);
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
		        	String str=responseInfo.result;
		        	String status = null;
		        	Log.i("网络请求下来的shengfen 参数是",str);
		        	try {
						JSONObject obj=new JSONObject(str);
						status=obj.getString("status");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	if(status.equals("1")){
		        		Toast.makeText(ChangeTakeGoodsAddressActivity.this,"修改成功",1).show();
		        	}else if(status.equals("0")){
		        		Toast.makeText(ChangeTakeGoodsAddressActivity.this,"修改失败",1).show();
		        	}
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	

}
