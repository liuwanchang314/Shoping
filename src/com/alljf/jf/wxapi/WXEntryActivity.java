package com.alljf.jf.wxapi;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler{

	
	// IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    TextView tv;
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
		Log.e("wx=============", "code =======");
		// TODO Auto-generated method stub
//         int result = 0;
//		
//		switch (req.getType()) {
//		case BaseReq.:
//			result = R.string.errcode_success;
//			
//			break;
//		case BaseResp.ErrCode.ERR_USER_CANCEL:
//			result = R.string.errcode_cancel;
//			break;
//		case BaseResp.ErrCode.ERR_AUTH_DENIED:
//			result = R.string.errcode_deny;
//			break;
//		default:
//			result = R.string.errcode_unknown;
//			break;
//		}
//		
//		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onResp(BaseResp resp) {
		// TODO Auto-generated method stub
          int result = 0;
		Log.e("wx=============", "code ======="+resp.errCode);
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			result = R.string.errcode_success;
			
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

//	@Override
//	public void onReq(com.tencent.mm.sdk.modelbase.BaseReq arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onResp(com.tencent.mm.sdk.modelbase.BaseResp arg0) {
//		// TODO Auto-generated method stub
//		
//	}
	
}