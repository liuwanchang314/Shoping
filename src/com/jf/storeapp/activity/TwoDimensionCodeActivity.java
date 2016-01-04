package com.jf.storeapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.jf.storeapp.R;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 下午1:28:22
 * 我的二维码页面
 */  

public class TwoDimensionCodeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_towdimensioncode);
		
	}

}
