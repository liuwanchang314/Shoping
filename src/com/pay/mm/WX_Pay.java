package com.pay.mm;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONException;
import org.json.JSONObject;

import com.Extension.DataService;
import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.utils.StringManager;

import android.app.Activity;
import android.app.AlertDialog;
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
	private Context context;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.arg1) {
			case 0:
				try {
					jsonObject = new JSONObject(msg.obj.toString());
					String date = jsonObject.getString("data");
					Map<String, String> map = StringManager.getListMapByJson(date).get(0);
					PayReq req = new PayReq();
					//req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
					req.appId			= CommonConstants.WXAPP_ID;
					req.partnerId		= map.get("mch_id");
					req.prepayId		= map.get("prepay_id");
					req.nonceStr		= map.get("nonce_str");
					req.timeStamp		= map.get("timeStamp");
					req.packageValue	= "Sign=WXPay";
					req.sign			= map.get("callback_sign");
					
//					req.extData			= "app data"; // optional
//					
					
					// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
					final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);

					// 将该app注册到微信
					msgApi.registerApp(CommonConstants.WXAPP_ID);
					api.sendReq(req);
					
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
		this.context = context;
		api = WXAPIFactory.createWXAPI(context, CommonConstants.WXAPP_ID);
	}
		
	public void pay(HashMap<String, String> map){
		client = new DataService(handler, 0, map);
		client.start();
	}

	
	
}
