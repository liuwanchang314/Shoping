package com.activity;

import android.app.Activity;
import android.os.Bundle;

import android.view.Window;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 下午3:35:16
 * 注册界面
 */  
public class RegnActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_regn);
	}

}
