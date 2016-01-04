package com.jf.storeapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.jf.storeapp.R;

public class oederactivity3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.orderactivity3);
	}
}
