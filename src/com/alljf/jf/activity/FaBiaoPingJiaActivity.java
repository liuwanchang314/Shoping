package com.alljf.jf.activity;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.alljf.jf.R;
import com.bean.OrderBean;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015-12-30 下午10:04:35 
 * 类说明   发表评价界面
 */
public class FaBiaoPingJiaActivity extends Activity {
	private ImageView mBack;
	private ImageView mHome;
	private ImageView mGoodsImage;
	private EditText mPingjia;
	private CheckBox mBBPF1,mBBPF2,mBBPF3,mBBPF4,mBBPF5;//宝贝评分
	private CheckBox mBBYMSXF1,mBBYMSXF2,mBBYMSXF3,mBBYMSXF4,mBBYMSXF5;//宝贝与描述相符
	private CheckBox mMJDFWTD1,mMJDFWTD2,mMJDFWTD3,mMJDFWTD4,mMJDFWTD5;//卖家的服务态度
	private CheckBox mMJDFHSD1,mMJDFHSD2,mMJDFHSD3,mMJDFHSD4,mMJDFHSD5;//卖家的发货速度
	private TextView mFabiaopingjia;//发表评价
	/**
	 * 用于记录宝贝评分
	 */
	private int mBaobeipingfenNum=0;
	/**
	 * 用于记录宝贝描述相符度
	 */
	private int mBaobeixiangfuduNum=0;
	/**
	 * 用于记录卖家服务态度
	 */
	private int mMaijiafuwutaiduNum=0;
	/**
	 * 用于记录卖家发货速度
	 */
	private int mMaijiafahuosuduNum=0;
	/**
	 * 传递过来的商品id
	 */
	private OrderBean bean;
	/**
	 * 是否选择匿名评价
	 */
	private String isnona;
	/**
	 * 进度条
	 */
	private SpotsDialog mdialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pingjia);
		SysApplication.getInstance().addActivity(this);
		initview();
		Intent intent=getIntent();
		bean=(OrderBean) intent.getSerializableExtra("bean");
		BitmapUtils bitmaputils=new BitmapUtils(FaBiaoPingJiaActivity.this);
		bitmaputils.display(mGoodsImage,bean.getOrdergoods().getGoods_image());
	}
	/**
	 * @2015-12-30下午10:11:38
	 * @author JZKJ-LWC
	 * 初始化控件
	 */
	private void initview() {
		// TODO Auto-generated method stub
		SpotsDialog.TAG=R.style.SpotsDialogDefault_tijiao;
		mdialog=new SpotsDialog(FaBiaoPingJiaActivity.this);
		mdialog.setCanceledOnTouchOutside(false);
		mBack=(ImageView) findViewById(R.id.pingjia_back);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FaBiaoPingJiaActivity.this.finish();
			}
		});
		mHome=(ImageView) findViewById(R.id.pingjia_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FaBiaoPingJiaActivity.this.finish();
				startActivity(new Intent(FaBiaoPingJiaActivity.this, MainActivity.class));
			}
		});
		mGoodsImage=(ImageView) findViewById(R.id.pingjia_goodsimage);
		mPingjia=(EditText) findViewById(R.id.pingjia_pingjia);
		mBBPF1=(CheckBox) findViewById(R.id.checkBox_baobeipingjia1);
		mBBPF1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mBaobeipingfenNum=mBaobeipingfenNum+1;
				}else{
					mBaobeipingfenNum=mBaobeipingfenNum-1;
				}
			}
		});
		mBBPF2=(CheckBox) findViewById(R.id.checkBox_baobeipingjia2);
		mBBPF2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mBaobeipingfenNum=mBaobeipingfenNum+1;
				}else{
					mBaobeipingfenNum=mBaobeipingfenNum-1;
				}
			}
		});
		mBBPF3=(CheckBox) findViewById(R.id.checkBox_baobeipingjia3);
		mBBPF3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mBaobeipingfenNum=mBaobeipingfenNum+1;
				}else{
					mBaobeipingfenNum=mBaobeipingfenNum-1;
				}
			}
		});
		mBBPF4=(CheckBox) findViewById(R.id.checkBox_baobeipingjia4);
		mBBPF4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mBaobeipingfenNum=mBaobeipingfenNum+1;
				}else{
					mBaobeipingfenNum=mBaobeipingfenNum-1;
				}
			}
		});
		mBBPF5=(CheckBox) findViewById(R.id.checkBox_baobeipingjia5);
		mBBPF5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mBaobeipingfenNum=mBaobeipingfenNum+1;
				}else{
					mBaobeipingfenNum=mBaobeipingfenNum-1;
				}
			}
		});
		mBBYMSXF1=(CheckBox) findViewById(R.id.checkBox_baobeiyumiaoshuiangfu1);
		mBBYMSXF1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mBaobeixiangfuduNum=mBaobeixiangfuduNum+1;
				}else{
					mBaobeixiangfuduNum=mBaobeixiangfuduNum-1;
				}
			}
		});
		mBBYMSXF2=(CheckBox) findViewById(R.id.checkBox_baobeiyumiaoshuiangfu2);
		mBBYMSXF2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mBaobeixiangfuduNum=mBaobeixiangfuduNum+1;
				}else{
					mBaobeixiangfuduNum=mBaobeixiangfuduNum-1;
				}
			}
		});
		mBBYMSXF3=(CheckBox) findViewById(R.id.checkBox_baobeiyumiaoshuiangfu3);
		mBBYMSXF3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mBaobeixiangfuduNum=mBaobeixiangfuduNum+1;
				}else{
					mBaobeixiangfuduNum=mBaobeixiangfuduNum-1;
				}
			}
		});
		mBBYMSXF4=(CheckBox) findViewById(R.id.checkBox_baobeiyumiaoshuiangfu4);
		mBBYMSXF4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mBaobeixiangfuduNum=mBaobeixiangfuduNum+1;
				}else{
					mBaobeixiangfuduNum=mBaobeixiangfuduNum-1;
				}
			}
		});
		mBBYMSXF5=(CheckBox) findViewById(R.id.checkBox_baobeiyumiaoshuiangfu5);
		mBBYMSXF5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mBaobeixiangfuduNum=mBaobeixiangfuduNum+1;
				}else{
					mBaobeixiangfuduNum=mBaobeixiangfuduNum-1;
				}
			}
		});
		mMJDFWTD1=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu1);
		mMJDFWTD1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mMaijiafuwutaiduNum=mMaijiafuwutaiduNum+1;
				}else{
					mMaijiafuwutaiduNum=mMaijiafuwutaiduNum-1;
				}
			}
		});
		mMJDFWTD2=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu2);
		mMJDFWTD2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mMaijiafuwutaiduNum=mMaijiafuwutaiduNum+1;
				}else{
					mMaijiafuwutaiduNum=mMaijiafuwutaiduNum-1;
				}
			}
		});
		mMJDFWTD3=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu3);
		mMJDFWTD3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mMaijiafuwutaiduNum=mMaijiafuwutaiduNum+1;
				}else{
					mMaijiafuwutaiduNum=mMaijiafuwutaiduNum-1;
				}
			}
		});
		mMJDFWTD4=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu4);
		mMJDFWTD4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mMaijiafuwutaiduNum=mMaijiafuwutaiduNum+1;
				}else{
					mMaijiafuwutaiduNum=mMaijiafuwutaiduNum-1;
				}
			}
		});
		mMJDFWTD5=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu5);
		mMJDFWTD5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mMaijiafuwutaiduNum=mMaijiafuwutaiduNum+1;
				}else{
					mMaijiafuwutaiduNum=mMaijiafuwutaiduNum-1;
				}
			}
		});
		mMJDFHSD1=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu1);
		mMJDFHSD1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mMaijiafahuosuduNum=mMaijiafahuosuduNum+1;
				}else{
					mMaijiafahuosuduNum=mMaijiafahuosuduNum-1;
				}
			}
		});
		mMJDFHSD2=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu2);
		mMJDFHSD2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mMaijiafahuosuduNum=mMaijiafahuosuduNum+1;
				}else{
					mMaijiafahuosuduNum=mMaijiafahuosuduNum-1;
				}
			}
		});
		mMJDFHSD3=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu3);
		mMJDFHSD3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mMaijiafahuosuduNum=mMaijiafahuosuduNum+1;
				}else{
					mMaijiafahuosuduNum=mMaijiafahuosuduNum-1;
				}
			}
		});
		mMJDFHSD4=(CheckBox) findViewById(R.id.checkBox_maijiadefuwutaidu4);
		mMJDFHSD4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mMaijiafahuosuduNum=mMaijiafahuosuduNum+1;
				}else{
					mMaijiafahuosuduNum=mMaijiafahuosuduNum-1;
				}
			}
		});
		mMJDFHSD5=(CheckBox)findViewById(R.id.checkBox_maijiadefuwutaidu5);
		mMJDFHSD5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					mMaijiafahuosuduNum=mMaijiafahuosuduNum+1;
				}else{
					mMaijiafahuosuduNum=mMaijiafahuosuduNum-1;
				}
			}
		});
		mFabiaopingjia=(TextView) findViewById(R.id.pingjia_fabiao);
		mFabiaopingjia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("宝贝评分是",mBaobeipingfenNum+"");
				Log.i("宝贝与描述相符",mBaobeixiangfuduNum+"");
				Log.i("卖家服务态度",mMaijiafuwutaiduNum+"");
				Log.i("卖家发货速度",mMaijiafahuosuduNum+"");
				//调用方法，进行评价
				//先弹出提示框，让用户选择是否匿名
				AlertDialog.Builder dialog=new AlertDialog.Builder(FaBiaoPingJiaActivity.this);
				dialog.setTitle("温馨提示");
				dialog.setMessage("请选择您的评价方式");
				dialog.setNegativeButton("匿名",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						isnona=1+"";
						String orderid=bean.getOrder_id();
						String miaoshu = bean.getOrdergoods().getGoods_id()+"<!--nabin score-->"+mBaobeipingfenNum+""+"<!--nabin content-->"+mPingjia.getText();
						String baobeimiaoshhu=mBaobeixiangfuduNum+"";
						String maijiafuwutaidu = mMaijiafuwutaiduNum+"";
						String maijiafahuosudu = mMaijiafahuosuduNum+"";
						getDataFaBiaoPingjia(isnona,orderid,miaoshu,baobeimiaoshhu,maijiafuwutaidu,maijiafahuosudu);
						
					}
				});
				dialog.setPositiveButton("不匿名",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						isnona="0";
						String orderid=bean.getOrder_id();
						String miaoshu = bean.getOrdergoods().getGoods_id()+"<!--nabin score-->"+mBaobeipingfenNum+""+"<!--nabin content-->"+mPingjia.getText();
						String baobeimiaoshhu=mBaobeixiangfuduNum+"";
						String maijiafuwutaidu = mMaijiafuwutaiduNum+"";
						String maijiafahuosudu = mMaijiafahuosuduNum+"";
						getDataFaBiaoPingjia(isnona,orderid,miaoshu,baobeimiaoshhu,maijiafuwutaidu,maijiafahuosudu);
					}
				});
				dialog.create().show();
				
				
				
			}

			
		});
		
	}
	
	/**
	 * @2016-1-10下午2:17:24
	 * 将用户评价数据进行上传，进行评价
	 */
	private void getDataFaBiaoPingjia(String isanony,String id,String miaoshu,
			String baobeimiaoshu, String maijiafuwutaidu,
			String maijiafahuosudu) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "do_evaluate");
		params.addBodyParameter("order_id",id);
		params.addBodyParameter("id_anony",isanony);
		params.addBodyParameter("user_name",SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("goods_values",miaoshu);
		params.addBodyParameter("store_desccredit",baobeimiaoshu);
		params.addBodyParameter("store_servicecredit",maijiafuwutaidu);
		params.addBodyParameter("store_deliverycredit",maijiafahuosudu);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

		        @Override
		        public void onStart() {
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
		        	mdialog.dismiss();
		        	String str=responseInfo.result;
		        	Log.i("评价返回的数据是",str+"");
		        	try {
						JSONObject obj=new JSONObject(str);
						String status=obj.getString("status");
						if(status.equals("0")){
							Toast.makeText(FaBiaoPingJiaActivity.this,"呀，失败了",1).show();
						}else if(status.equals("1")){
							Toast.makeText(FaBiaoPingJiaActivity.this,"评价成功",1).show();
							FaBiaoPingJiaActivity.this.finish();
						}else if(status.equals("2")){
							Toast.makeText(FaBiaoPingJiaActivity.this,"订单已评价",1).show();
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
