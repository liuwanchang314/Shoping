package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015-12-29 下午11:07:38 
 * 类说明 
 */
public class TousuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tousu);
	}
	
	

}
