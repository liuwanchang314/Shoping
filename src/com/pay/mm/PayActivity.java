package com.pay.mm;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PayActivity extends Activity {

	private static final String TAG = "MicroMsg.SDKSample.PayActivity";
	
	private IWXAPI api;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay);
		
		api = WXAPIFactory.createWXAPI(this, CommonConstants.WXAPP_ID);

		Button payBtn = (Button) findViewById(R.id.pay_btn);
		payBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new GetAccessTokenTask().execute();
			}
		});
		
		Button checkPayBtn = (Button) findViewById(R.id.check_pay_btn);
		checkPayBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
				Toast.makeText(PayActivity.this, String.valueOf(isPaySupported), Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	/**
	 * 微信公众平台商户模块和商户约定的密钥
	 * 
	 * 注意：不能hardcode在客户端，建议genPackage这个过程由服务器端完成
	 */
	private static final String PARTNER_KEY = "8934e7d15453e97507ef794cf7b0519d";
	
	private String genPackage(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(PARTNER_KEY); // 注意：不能hardcode在客户端，建议genPackage这个过程都由服务器端完成
		
		// 进行md5摘要前，params内容为原始内容，未经过url encode处理
		String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
		
		return URLEncodedUtils.format(params, "utf-8") + "&sign=" + packageSign;
	}
	
	 /**
     * 微信开放平台和商户约定的密钥
     * 
     * 注意：不能hardcode在客户端，建议genSign这个过程由服务器端完成
     */
	private static final String APP_SECRET = CommonConstants.WXSECRET; // wxd930ea5d5a258f4f 对应的密钥
	
	/**
     * 微信开放平台和商户约定的支付密钥
     * 
     * 注意：不能hardcode在客户端，建议genSign这个过程由服务器端完成
     */
	private static final String APP_KEY = CommonConstants.WXSIGN; // wxd930ea5d5a258f4f 对应的支付密钥
	
	private class GetAccessTokenTask extends AsyncTask<Void, Void, GetAccessTokenResult> {

		private ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(PayActivity.this, "title", "正在获取token");
		}

		@Override
		protected void onPostExecute(GetAccessTokenResult result) {
			if (dialog != null) {
				dialog.dismiss();
			}
			
			if (result.localRetCode == LocalRetCode.ERR_OK) {
				Toast.makeText(PayActivity.this, "token 成功", Toast.LENGTH_LONG).show();
				Log.d(TAG, "onPostExecute, accessToken = " + result.accessToken);
				
				GetPrepayIdTask getPrepayId = new GetPrepayIdTask(result.accessToken);
				getPrepayId.execute();
			} else {
				Toast.makeText(PayActivity.this, "获取token失败", Toast.LENGTH_LONG).show();
			}
		}

		@Override
		protected GetAccessTokenResult doInBackground(Void... params) {
			GetAccessTokenResult result = new GetAccessTokenResult();

			String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
					CommonConstants.WXAPP_ID, APP_SECRET);
			Log.d(TAG, "get access token, url = " + url);
			
			byte[] buf = Util.httpGet(url);
			if (buf == null || buf.length == 0) {
				result.localRetCode = LocalRetCode.ERR_HTTP;
				return result;
			}
			
			String content = new String(buf);
			result.parseFrom(content);
			return result;
		}
	}
	
	private class GetPrepayIdTask extends AsyncTask<Void, Void, GetPrepayIdResult> {

		private ProgressDialog dialog;
		private String accessToken;
		
		public GetPrepayIdTask(String accessToken) {
			this.accessToken = accessToken;
		}
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(PayActivity.this, "title", "huoqu preid");
		}

		@Override
		protected void onPostExecute(GetPrepayIdResult result) {
			if (dialog != null) {
				dialog.dismiss();
			}
			
			if (result.localRetCode == LocalRetCode.ERR_OK) {
				Toast.makeText(PayActivity.this, "prepayid_succ", Toast.LENGTH_LONG).show();
				sendPayReq(result);
			} else {
				Toast.makeText(PayActivity.this, "get_prepayid_fail", Toast.LENGTH_LONG).show();
			}
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected GetPrepayIdResult doInBackground(Void... params) {

			String url = String.format("https://api.weixin.qq.com/pay/genprepay?access_token=%s", accessToken);
			String entity = genProductArgs();
			
			Log.d(TAG, "doInBackground, url = " + url);
			Log.d(TAG, "doInBackground, entity = " + entity);
			
			GetPrepayIdResult result = new GetPrepayIdResult();
			
			byte[] buf = Util.httpPost(url, entity);
			if (buf == null || buf.length == 0) {
				result.localRetCode = LocalRetCode.ERR_HTTP;
				return result;
			}
			
			String content = new String(buf);
			Log.d(TAG, "doInBackground, content = " + content);
			result.parseFrom(content);
			return result;
		}
	}

	private static enum LocalRetCode {
		ERR_OK, ERR_HTTP, ERR_JSON, ERR_OTHER
	}
	
	private static class GetAccessTokenResult {
		
		private static final String TAG = "MicroMsg.SDKSample.PayActivity.GetAccessTokenResult";
		
		public LocalRetCode localRetCode = LocalRetCode.ERR_OTHER;
		public String accessToken;
		public int expiresIn;
		public int errCode;
		public String errMsg;
		
		public void parseFrom(String content) {

			if (content == null || content.length() <= 0) {
				Log.e(TAG, "parseFrom fail, content is null");
				localRetCode = LocalRetCode.ERR_JSON;
				return;
			}
			
			try {
				JSONObject json = new JSONObject(content);
				if (json.has("access_token")) { // success case
					accessToken = json.getString("access_token");
					expiresIn = json.getInt("expires_in");
					localRetCode = LocalRetCode.ERR_OK;
				} else {
					errCode = json.getInt("errcode");
					errMsg = json.getString("errmsg");
					localRetCode = LocalRetCode.ERR_JSON;
				}
				
			} catch (Exception e) {
				localRetCode = LocalRetCode.ERR_JSON;
			}
		}
	}
	
	private static class GetPrepayIdResult {
		
		private static final String TAG = "MicroMsg.SDKSample.PayActivity.GetPrepayIdResult";
		
		public LocalRetCode localRetCode = LocalRetCode.ERR_OTHER;
		public String prepayId;
		public int errCode;
		public String errMsg;
		
		public void parseFrom(String content) {
			
			if (content == null || content.length() <= 0) {
				Log.e(TAG, "parseFrom fail, content is null");
				localRetCode = LocalRetCode.ERR_JSON;
				return;
			}
			
			try {
				JSONObject json = new JSONObject(content);
				if (json.has("prepayid")) { // success case
					prepayId = json.getString("prepayid");
					localRetCode = LocalRetCode.ERR_OK;
				} else {
					localRetCode = LocalRetCode.ERR_JSON;
				}
				
				errCode = json.getInt("errcode");
				errMsg = json.getString("errmsg");
				
			} catch (Exception e) {
				localRetCode = LocalRetCode.ERR_JSON;
			}
		}
	}
	
	private String genNonceStr() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	}
	
	private long genTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}
	
	/**
	 * 建议 traceid 字段包含用户信息及订单信息，方便后续对订单状态的查询和跟踪
	 */
	private String getTraceId() {
		return "crestxu_" + genTimeStamp(); 
	}
	
	/**
	 * 注意：商户系统内部的订单号,32个字符内、可包含字母,确保在商户系统唯一
	 */
	private String genOutTradNo() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	}
	
	private long timeStamp;
	private String nonceStr, packageValue; 
	
	private String genSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		for (; i < params.size() - 1; i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append(params.get(i).getName());
		sb.append('=');
		sb.append(params.get(i).getValue());
		
		String sha1 = Util.sha1(sb.toString());
		Log.d(TAG, "genSign, sha1 = " + sha1);
		return sha1;
	}
	
	private String genProductArgs() {
		JSONObject json = new JSONObject();
		
		try {
			json.put("appid", CommonConstants.WXAPP_ID);
			String traceId = getTraceId();  // traceId 由开发者自定义，可用于订单的查询与跟踪，建议根据支付用户信息生成此id
			json.put("traceid", traceId);
			nonceStr = genNonceStr();
			json.put("noncestr", nonceStr);
			
			List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			packageParams.add(new BasicNameValuePair("bank_type", "WX"));
			packageParams.add(new BasicNameValuePair("body", "千足金箍棒"));
			packageParams.add(new BasicNameValuePair("fee_type", "1"));
			packageParams.add(new BasicNameValuePair("input_charset", "UTF-8"));
			packageParams.add(new BasicNameValuePair("notify_url", "http://weixin.qq.com"));
			packageParams.add(new BasicNameValuePair("out_trade_no", genOutTradNo()));
			packageParams.add(new BasicNameValuePair("partner", "1900000109"));
			packageParams.add(new BasicNameValuePair("spbill_create_ip", "196.168.1.1"));
			packageParams.add(new BasicNameValuePair("total_fee", "1"));
			packageValue = genPackage(packageParams);
			
			json.put("package", packageValue);
			timeStamp = genTimeStamp();
			json.put("timestamp", timeStamp);
			
			List<NameValuePair> signParams = new LinkedList<NameValuePair>();
			signParams.add(new BasicNameValuePair("appid", CommonConstants.WXAPP_ID));
			signParams.add(new BasicNameValuePair("appkey", APP_KEY));
			signParams.add(new BasicNameValuePair("noncestr", nonceStr));
			signParams.add(new BasicNameValuePair("package", packageValue));
			signParams.add(new BasicNameValuePair("timestamp", String.valueOf(timeStamp)));
			signParams.add(new BasicNameValuePair("traceid", traceId));
			json.put("app_signature", genSign(signParams));
			
			json.put("sign_method", "sha1");
		} catch (Exception e) {
			Log.e(TAG, "genProductArgs fail, ex = " + e.getMessage());
			return null;
		}
		
		return json.toString();
	}
	
	private void sendPayReq(GetPrepayIdResult result) {
		
		PayReq req = new PayReq();
		req.appId = CommonConstants.WXAPP_ID;
		req.partnerId = CommonConstants.WXSHANGHAO;
		req.prepayId = result.prepayId;
		req.nonceStr = nonceStr;
		req.timeStamp = String.valueOf(timeStamp);
		req.packageValue = "Sign=" + packageValue;
		
		List<NameValuePair> signParams = new LinkedList<NameValuePair>();
		signParams.add(new BasicNameValuePair("appid", req.appId));
		signParams.add(new BasicNameValuePair("appkey", APP_KEY));
		signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
		signParams.add(new BasicNameValuePair("package", req.packageValue));
		signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
		signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
		signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
		req.sign = genSign(signParams);
		
		// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
		api.sendReq(req);
	}
}
