package com.pay.mm;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.Extension.DataService;
import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WX_Pay {
	
	private IWXAPI api;
	DataService client;
	JSONObject jsonObject;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.arg1) {
			case 0:
				Log.e("wx===", "===="+msg.obj.toString());
				try {
					jsonObject = new JSONObject(msg.obj.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;

			default:
				break;
			}
		};
	};
	
	public WX_Pay(Context context){
		api = WXAPIFactory.createWXAPI(context, CommonConstants.WXAPP_ID);
	}
		
	public void pay(HashMap<String, String> map){
		client = new DataService(handler, 0, map);
		client.start();
	}
//		api = WXAPIFactory.createWXAPI(this, CommonConstants.WXAPP_ID);
//
//		Button appayBtn = (Button) findViewById(R.id.pay_btn);
//		appayBtn.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				final String url = "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";
//				Button payBtn = (Button) findViewById(R.id.pay_btn);
//				payBtn.setEnabled(false);
//				Toast.makeText(PayActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						
//				        try{
//							byte[] buf = Util.httpGet(url);
//							if (buf != null && buf.length > 0) {
//								String content = new String(buf);
//								Log.e("get server pay params:",content);
//					        	JSONObject json = new JSONObject(content); 
//								if(null != json && !json.has("retcode") ){
//									PayReq req = new PayReq();
//									//req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
//									req.appId			= json.getString("appid");
//									req.partnerId		= json.getString("partnerid");
//									req.prepayId		= json.getString("prepayid");
//									req.nonceStr		= json.getString("noncestr");
//									req.timeStamp		= json.getString("timestamp");
//									req.packageValue	= json.getString("package");
//									req.sign			= json.getString("sign");
//									
//									req.extData			= "app data"; // optional
////									Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
//									Log.e("PAY_GET", "diaoyong====");
//									// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//									api.sendReq(req);
//								}else{
//						        	Log.d("PAY_GET", "返回错误"+json.getString("retmsg"));
//						        	Toast.makeText(PayActivity.this, "返回错误"+json.getString("retmsg"), Toast.LENGTH_SHORT).show();
//								}
//							}else{
//					        	Log.d("PAY_GET", "服务器请求错误");
//					        	Toast.makeText(PayActivity.this, "服务器请求错误", Toast.LENGTH_SHORT).show();
//					        }
//				        }catch(Exception e){
//				        	Log.e("PAY_GET", "异常："+e.getMessage());
//				        	Toast.makeText(PayActivity.this, "异常："+e.getMessage(), Toast.LENGTH_SHORT).show();
//				        }
//				       
//					}
//				}).start();
//				 payBtn.setEnabled(true);
//			}
//		});		
//		Button checkPayBtn = (Button) findViewById(R.id.check_pay_btn);
//		checkPayBtn.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
//				Toast.makeText(PayActivity.this, String.valueOf(isPaySupported), Toast.LENGTH_SHORT).show();
//			}
//		});
	
	
}
