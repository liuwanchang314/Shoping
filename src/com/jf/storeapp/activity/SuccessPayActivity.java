package com.jf.storeapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.jf.storeapp.R;
/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 下午4:15:06
 * 支付成功界面
 */  
public class SuccessPayActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_successpay);
	}

}
