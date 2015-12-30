package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015-12-30 下午11:38:54 
 * 类说明 
 */
public class DingDanXQActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dingdanxiangqingye);
	}

}
