package com.alljf.jf.wxapi;





import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.Extension.DataService;
import com.Model.UserInfo;
import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.utils.StringManager;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler{

	
	// IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private String openid;
    private String access_token;
    DataService client;
    TextView tv;
    private String gettokenurl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+CommonConstants.WXAPP_ID+"&secret="+CommonConstants.WXSECRET+"&code=";
	private String getWXUserUrl ="https://api.weixin.qq.com/sns/userinfo?access_token=";
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weixin);
		tv =(TextView) findViewById(R.id.wx_res);
		// 通过WXAPIFactory工厂，获取IWXAPI的实例
    	api = WXAPIFactory.createWXAPI(this, CommonConstants.WXAPP_ID, false);
    	 api.handleIntent(getIntent(), this);
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
//		super.onNewIntent(intent);
		
		setIntent(intent);
        api.handleIntent(intent, this);
	}
	@Override
	public void onReq(BaseReq req) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onResp(BaseResp resp) {
		// TODO Auto-generated method stub
          int result = 0;
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			result = R.string.errcode_success;
			if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
					Toast.makeText(this, "code = " + ((SendAuth.Resp)resp).code,
							Toast.LENGTH_SHORT).show();
					gettokenurl = gettokenurl+((SendAuth.Resp)resp).code+"&grant_type=authorization_code";
					getHttpThread(gettokenurl, 1, handler).start();
			
			}
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			result = R.string.errcode_cancel;
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			result = R.string.errcode_deny;
			break;
		default:
			result = R.string.errcode_unknown;
			break;
		}
		
		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}


	private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case 0:
				try {
					JSONObject jsonObject = new JSONObject(msg.obj.toString());
					if(jsonObject.has("status")){
						String status = jsonObject.getString("status");
						tv.setText(status+"charujieguo"+msg.obj.toString());
						if(status.equals("1")){
							UserInfo model = UserInfo.getInstance();
							String data = jsonObject.getString("data");
							JSONObject jsonObject1 = new JSONObject(data);
							tv.setText(status+"----"+jsonObject1.getString("user_name"));
							model.setName(jsonObject1.getString("user_name"));
							SysApplication.getInstance().addUserInfo(model);
							Toast.makeText(WXEntryActivity.this, "denglu chenggong",
									Toast.LENGTH_LONG).show();
							finish();
						}else{
//							getWXUserUrl = getWXUserUrl+access_token+"&openid="+openid;
//							getHttpThread(getWXUserUrl, 2, handler).start();
						}
					}else{
						tv.setText("status is null"+msg.obj.toString());
					}
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 1:
				List<Map<String, String>> list = StringManager.getListMapByJson(msg.obj.toString());
				Map<String, String> map = list.get(0);
				tv.setText("1111111");
				access_token = map.get("access_token");
				openid = map.get("openid");
				HashMap<String, String> paramsmap = new HashMap<String, String>();
				paramsmap.put("type", "user");
				paramsmap.put("part", "wxlogin");
				paramsmap.put("wxopen_id", openid);
				DataService client1 = new DataService(handler, 0, paramsmap);
				client1.start();
				tv.setText("1111111------");
//				tv.setText(access_token);
				break;
//			case 2:
//				List<Map<String, String>> listmap = StringManager.getListMapByJson(msg.obj.toString());
//				Map<String, String> userinfomap = listmap.get(0);
//				HashMap<String, String> paramsmap1 = new HashMap<String, String>();
//				paramsmap1.put("type", "user");
//				paramsmap1.put("part", "insert_wechat");
//				
//				paramsmap1.put("username", userinfomap.get("nickname"));
//				paramsmap1.put("wx_openid", userinfomap.get("openid"));
//				client = new DataService(handler, 3, paramsmap1);
//				client.start();
//				break;
			case 3:
//				tv.setText("baocun  chenggong");
				break;
			default:
				break;
			}
		};
	};
	
	
	private Thread getHttpThread(final String url, final int arg1,final Handler handler){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpGet httpGet = new HttpGet(url);
	            HttpClient httpClient = new DefaultHttpClient();
	            try {
	            	HttpResponse response = httpClient.execute(httpGet);
	            	if(response.getStatusLine().getStatusCode() == 200){
	            		String result = EntityUtils.toString(response.getEntity());
	            		Message msg = handler.obtainMessage();
	            		msg.arg1 =arg1;
	            		msg.obj =result;
	            		handler.sendMessage(msg);
	            	}
	            	
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		return thread;
	}
	
	
}