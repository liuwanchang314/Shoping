package com.alljf.jf.activity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.adapter.TakeGoodsAddressAdapter;
import com.alljf.jf.R;
import com.bean.TakeGoodsAddressBean;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.jsonParser.TakeGoodsAddressJsonpaser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 下午3:03:40
 *收货地址展示界面
 */  
public class TakeGoodsAddressActivity extends Activity implements OnClickListener,OnItemClickListener{
	
	private ImageView mBack;
	private ImageView mHome;
	private ListView mListview;
	private TextView mAdd;
	private TakeGoodsAddressAdapter adapter;
	/**
	 * 进度条
	 */
	private SpotsDialog mdialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_takegoodsaddress);
		SysApplication.getInstance().addActivity(this);
		initview();
		getdata();
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-14 下午11:31:35
	 */ 

	//获取用户用户名
		private String getUserName() {
			return SysApplication.getInstance().getUserInfo().getName();
		}
	private void getdata() {
		
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "post_address_nokey");
		params.addBodyParameter("username", getUserName());
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
		        		//获取的数据无格式说明
		        	final List<TakeGoodsAddressBean> list=TakeGoodsAddressJsonpaser.getlist(str);
		        	Log.i("现在地址有多少",list.size()+"");
		        	//给listview准备适配器
		        	adapter=new TakeGoodsAddressAdapter(list, TakeGoodsAddressActivity.this);
		        	mListview.setAdapter(adapter);
		        	mListview.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
							Intent intent=new Intent();
							intent.putExtra("name",list.get(arg2).getReceive_name());
							intent.putExtra("phone",list.get(arg2).getMob_phone());
							intent.putExtra("xxaddress",list.get(arg2).getArea_info());
							intent.putExtra("id",list.get(arg2).getAddress_id());
							TakeGoodsAddressActivity.this.setResult(1,intent);
							TakeGoodsAddressActivity.this.finish();
						}
					});
		        	mListview.setOnItemLongClickListener(new OnItemLongClickListener() {

						@Override
						public boolean onItemLongClick(AdapterView<?> arg0,
								View arg1, final int arg2, long arg3) {
							// TODO Auto-generated method stub
							AlertDialog.Builder dialog=new AlertDialog.Builder(TakeGoodsAddressActivity.this);
							View dialogView = LayoutInflater.from(TakeGoodsAddressActivity.this).inflate(R.layout.takeaddress_dialogview,null);
							dialog.setView(dialogView);
							TextView shezhi=(TextView) dialogView.findViewById(R.id.take_long_shezhi);
							TextView xiugai=(TextView) dialogView.findViewById(R.id.take_long_xiugai);
							shezhi.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Toast.makeText(TakeGoodsAddressActivity.this,"设置",1).show();	
									//调用方法，将该地址设置为默认地址
									setmoren(list.get(arg2).getAddress_id());
									getdata();
									adapter.notifyDataSetChanged();
								}

								
							});
							xiugai.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Toast.makeText(TakeGoodsAddressActivity.this,"修改",1).show();	
									//进行界面跳转，
									Intent intent=new Intent(TakeGoodsAddressActivity.this,ChangeTakeGoodsAddressActivity.class);
									//这里还需要intent带一部分数据过去
									intent.putExtra("name",list.get(arg2).getReceive_name());
									intent.putExtra("phone",list.get(arg2).getMob_phone());
//									intent.putExtra("youbian",list.get(arg2).get)//网络请求下来的参数就没有邮政编码，所以这里先不写
									intent.putExtra("xxdz",list.get(arg2).getArea_info());
									intent.putExtra("default",list.get(arg2).getDefaults());
									intent.putExtra("id",list.get(arg2).getAddress_id());
									startActivityForResult(intent, 3);
								}
							});
							dialog.create().show();
							return true;
						}
					});
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}

	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		SpotsDialog.TAG=R.style.SpotsDialogDefault_UPaddress;
		mdialog=new SpotsDialog(TakeGoodsAddressActivity.this);
		mdialog.setCanceledOnTouchOutside(false);
		mdialog.setCanceledOnTouchOutside(false);
		mBack=(ImageView) findViewById(R.id.takegoodsaddress_back);
		mBack.setOnClickListener(this);
		mHome=(ImageView) findViewById(R.id.takegoodsaddress_home);
		mHome.setOnClickListener(this);
		mAdd=(TextView) findViewById(R.id.takegoodsaddress_add);
		mAdd.setOnClickListener(this);
		mListview=(ListView) findViewById(R.id.takegoodsaddress_listview);
		mListview.setOnItemClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.takegoodsaddress_back:
			TakeGoodsAddressActivity.this.finish();
			break;
		case R.id.takegoodsaddress_home:
			TakeGoodsAddressActivity.this.finish();
			startActivity(new Intent(TakeGoodsAddressActivity.this,MainActivity.class));
			break;
		case R.id.takegoodsaddress_add:
			Toast.makeText(TakeGoodsAddressActivity.this,"添加", Toast.LENGTH_SHORT).show();
			startActivityForResult((new Intent(TakeGoodsAddressActivity.this,NewTakeOverGoodsAddress.class)),1);
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==1){
			getdata();
			adapter.notifyDataSetChanged();
		}
		if(resultCode==3){
			getdata();
			adapter.notifyDataSetChanged();
		}
	}
	public void setmoren(String id) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "default_post_address");
		params.addBodyParameter("address_id",id);
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
		        		Toast.makeText(TakeGoodsAddressActivity.this,"设置成功",1).show();
		        	}else if(status.equals("0")){
		        		Toast.makeText(TakeGoodsAddressActivity.this,"设置失败",1).show();
		        	}
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	
	
	

}
