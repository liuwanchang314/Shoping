package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 下午3:35:16
 * 注册界面
 */  
public class RegnActivity extends Activity {

	
	private EditText regn_txt_one;
	private EditText regn_txt_two, regn_txt_thr;
	private TextView regn_code_two;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_regn);
		initView();
	}
	
	private void initView(){
		
	}

}
