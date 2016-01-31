package com.alljf.jf.wxapi;







import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alljf.jf.CommonConstants;
import com.alljf.jf.activity.SuccessPayActivity;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.utils.FileUtils;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler{
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);
        
    	api = WXAPIFactory.createWXAPI(this, CommonConstants.WXAPP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("title");
			builder.setMessage(String.valueOf(resp.errCode));
			builder.show();
		}
		if(resp.errCode == 0){
			Intent intent = new Intent(this, SuccessPayActivity.class);
			intent.putExtra("sfk", FileUtils.getSFKAndClear(this));
			intent.putExtra("orderid", FileUtils.getOrderidAndClear(this));
			startActivity(intent);
		}else{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("支付结果");
			builder.setMessage("支付失败");
			builder.show();
			FileUtils.clearPayInfo(this);
		}
	}

//	@Override
//	public void onResp(BaseResp arg0) {
//		// TODO Auto-generated method stub
//		
//	}
}