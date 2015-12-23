
package com.activity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.Application.SysApplication;
import com.adapter.PayMoneyAdatper;
import com.bean.BuyCartBean;
import com.customview.Mylistview;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PayMoneyActivity extends Activity {
	
	private LinearLayout layout_shouhuodizhi;
	private TextView mName;
	private TextView mPhone;
	private TextView mAddress;
	private Mylistview mlistview;
	private LinearLayout mBillInfo;//发票信息
	private TextView mBillInfoTextviw;
	private TextView mFapiao;
	private TextView shangjiafahuo;
	private TextView totalprice;
	
//	//最底部的布局
//	private CheckBox yuncunkuan;//是否使用预存款
//	private TextView dangqianyue;//当前余额
//	private RelativeLayout xuyaoyincang;//需要隐藏的部分
//	private EditText zhifumima;//支付密码
//	private TextView yanzhengmima;//验证密码
	
	private boolean yanzhengTag=false;
	
	private TextView quedinganniu;
	private List<BuyCartBean> list;
	private String inv_id=null;
	private String zenpinid=null;
	private String addressid=null;
	private String kuaidiid=null;
	
	private String fahuoTAG="sj";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_paymoney);
		Intent intent=getIntent();
		list=(List<BuyCartBean>) getIntent().getSerializableExtra("list");
		Log.i("现在传递过来了多少数据",list.size()+"");
		initview();
		String price=intent.getStringExtra("price");
		totalprice.setText(price);
		//准备adapter
		PayMoneyAdatper adapter=new PayMoneyAdatper(list, PayMoneyActivity.this);
		mlistview.setAdapter(adapter);
		
		mBillInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(PayMoneyActivity.this,BillActivity.class);
				startActivityForResult(intent, 4);
			}
		});
		shangjiafahuo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fahuoTAG="sj";
				shangjiafahuo.setTextColor(Color.RED);
				Intent intents=new Intent(PayMoneyActivity.this,ShangJiaSendGoodsActivity.class);
				intents.putExtra("mode",shangjiafahuo.getText().toString());
				startActivityForResult(intents, 3);
			}
		});
//		yuncunkuan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				// TODO Auto-generated method stub
//				if(isChecked){
//					xuyaoyincang.setVisibility(View.VISIBLE);
//				}else{
//					xuyaoyincang.setVisibility(View.GONE);
//				}
//			}
//		});
//		yanzhengmima.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//调用方法进行密码验证，如果验证成功，则再次发出请求，获取用户当前余额，显示在可用资金框中
//				String password=zhifumima.getText().toString();
//				getdatayanzhenmima(password);
//				if(yanzhengTag){
//					//说明验证成功了
//					//获取当前用户余额
//					RequestParams params = new RequestParams();
//					// 只包含字符串参数时默认使用BodyParamsEntity，
//					params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
//					params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
//					params.addBodyParameter("type", "finance");
//					params.addBodyParameter("part", "user_finance_91");
//					params.addBodyParameter("user_name", SysApplication.getInstance().getUserInfo().getName());
//					HttpUtils http = new HttpUtils();
//					http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {
//
//					        @Override
//					        public void onStart() {
//					        	//开始请求
//					        }
//
//					        @Override
//					        public void onLoading(long total, long current, boolean isUploading) {
//					            if (isUploading) {
//					            } else {
//					            }
//					        }
//
//					        @Override
//					        public void onSuccess(ResponseInfo<String> responseInfo) {
//					        	//请求成功
//					        	String str=responseInfo.result;
//					        	Log.i("caiwu 请求下来的参数是",str);
//					        	
////					        	这里接口有问题，需要改动
//					        	try {
//									JSONObject obj=new JSONObject(str);
//									JSONObject objs=obj.getJSONObject("data");
//									String banace=objs.getString("user_balance");
//									dangqianyue.setText(banace);
//									String user_conbalance=objs.getString("user_conbalance");
//								} catch (JSONException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//					        }
//
//					        @Override
//					        public void onFailure(HttpException error, String msg) {
//					        }
//					});
//				}else{
//					//说明验证失败
//					Toast.makeText(PayMoneyActivity.this,"密码错误",1).show();
//				}
//			}
//		});
		quedinganniu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
						StringBuffer sb = new StringBuffer();
						for(int i=0;i<list.size();i++){
								sb.append(list.get(i).getCart_id());
								sb.append(",");
						}
						String cartids=sb.toString();
						String cartid=cartids.substring(0,cartids.length()-1);
						
						if(addressid==null){
							//说明为空
							Toast.makeText(PayMoneyActivity.this,"请选择收货地址",1).show();
						}else{
							String dizhiid=addressid;
							if(kuaidiid==null){
								//说明为空
								Toast.makeText(PayMoneyActivity.this,"请选择配送方式",1).show();
							}else{
								String kuaidiids=kuaidiid;
								if(inv_id==null){
									String fapiaoids="";
									if(zenpinid==null){
										String zenpinids="";
									}else{
										String zenpinids=zenpinid;
										Log.i("提交订单的时候商品id是", cartid);
										Log.i("提交订单的时候发票id是", fapiaoids);
										Log.i("提交订单的时候赠品id是", zenpinids);
										Log.i("提交订单的时候地址id是", dizhiid);
										Log.i("提交订单的时候快递公司id是", kuaidiids);
										getdatashengchengdingdan(cartid,dizhiid, fapiaoids, zenpinids,kuaidiids,"商家发货");
									}
								}else{
									String fapiaoids=inv_id;
									
									if(zenpinid==null){
										String zenpinids="";
									}else{
										String zenpinids=zenpinid;
										Log.i("提交订单的时候商品id是", cartid);
										Log.i("提交订单的时候发票id是", fapiaoids);
										Log.i("提交订单的时候赠品id是", zenpinids);
										Log.i("提交订单的时候地址id是", dizhiid);
										Log.i("提交订单的时候快递公司id是", kuaidiids);
										getdatashengchengdingdan(cartid,dizhiid, fapiaoids, zenpinids,kuaidiids,"商家发货");
									}
									
								}
								
								
								
							}
							
						}
			}
		});
	}

	@SuppressLint("CutPasteId")
	private void initview() {
		// TODO Auto-generated method stub
		layout_shouhuodizhi=(LinearLayout) findViewById(R.id.pay_shouhuodizhi);
		layout_shouhuodizhi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(PayMoneyActivity.this,TakeGoodsAddressActivity.class);
				startActivityForResult(intent,1);
			}

		});
		mName=(TextView) findViewById(R.id.paymoney_activity_tv_shouhuorennanme);
		mPhone=(TextView) findViewById(R.id.paymoney_activity_tv_shouhuorendianhua);
		mAddress=(TextView) findViewById(R.id.paymoney_activity_tv_shouhuorenaddress);
		mlistview=(Mylistview) findViewById(R.id.paymoney_activity_listview);
		mBillInfo=(LinearLayout) findViewById(R.id.pay_bill);
		mBillInfoTextviw=(TextView) findViewById(R.id.paymoney_activity_tv_fapiaoxinxi);
		mFapiao=(TextView) findViewById(R.id.paymoney_activity_tv_fapiaoxinxi);
		shangjiafahuo=(TextView) findViewById(R.id.paymoney_activity_tv_shangjiafahuo);
		totalprice=(TextView) findViewById(R.id.pay_totalprice);
//		yuncunkuan=(CheckBox) findViewById(R.id.paymoney_checbox_xshiyongyucunkuan);
//		dangqianyue=(TextView) findViewById(R.id.paymoney_activity_tv_dangqianyue);
//		xuyaoyincang=(RelativeLayout) findViewById(R.id.paymoney_shiyongyucunuan_yincang);
//		zhifumima=(EditText) findViewById(R.id.paymoney_activity_ed_shurujine);
//		yanzhengmima=(TextView) findViewById(R.id.paymoney_activity_tv_zhifu);
		quedinganniu=(TextView) findViewById(R.id.pay_quedinganniu);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==1){
			//说明值传递了过来
			mName.setText(data.getStringExtra("name"));
			mPhone.setText(data.getStringExtra("phone"));
			mAddress.setText(data.getStringExtra("xxaddress"));
			addressid=data.getStringExtra("id");
		}else if(resultCode==4){
			//说明是从发票界面传递过来的
			String title=data.getStringExtra("title");
			inv_id=data.getStringExtra("fapiaoid");
			mFapiao.setText(title);
		}else if(resultCode==3){
			//说明是从商家发货界面过来的数据
			zenpinid=data.getStringExtra("zenpinid");
			kuaidiid=data.getStringExtra("kuaidiid");
		}
	}
	
	//验证支付密码
	public void getdatayanzhenmima(String str){
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "pay_password");
		params.addBodyParameter("user_name", SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("password",str);
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
						String status=obj.getString("check_status");
						if(status!=null&&status.equals("1")){
							//正确
							yanzhengTag=true;
						}else if(status!=null&&status.equals("0")){
							//错误
							yanzhengTag=false;
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
	
	public void getdatashengchengdingdan(String cartids,String addressid,String fapiaoid,String giftids,String kuaidiid,
			String peisongfangshi){
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "do_order");
		params.addBodyParameter("user_name", SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("cart_ids",cartids);
		params.addBodyParameter("address_id",addressid);
		params.addBodyParameter("invoice_id",fapiaoid);
		params.addBodyParameter("pay_mode","online");
		params.addBodyParameter("gift_ids",giftids);
		params.addBodyParameter("express_id",kuaidiid);
		params.addBodyParameter("shipping_mode",peisongfangshi);
		params.addBodyParameter("order_des","");
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
		        	Log.i("生成订单了么请求下来的参数是",str);
		        	try {
						JSONObject obj=new JSONObject(str);
						String status=obj.getString("status");
						String order_id=obj.getString("order_id");
						String order_sn=obj.getString("order_sn");
						String pay_sn=obj.getString("pay_sn");
						if(status.equals("1")){
							//提交订单成功
							Toast.makeText(PayMoneyActivity.this,"订单提交成功",1).show();
							//自动跳转到支付界面，来进行支付
							Intent intent=new Intent(PayMoneyActivity.this, PayForActivity.class);
							intent.putExtra("price",totalprice.getText().toString());
							if(fahuoTAG.equals("sj")){
								intent.putExtra("fhfs","商家发货");
							}else if(fahuoTAG.equals("jh")){
								intent.putExtra("fhfs", "嘉禾配送");
							}
							intent.putExtra("order",order_sn);
							startActivity(intent);
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
