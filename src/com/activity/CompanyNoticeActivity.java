package com.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class CompanyNoticeActivity extends Activity {
	
	private ActionBar actionbar;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_companynotice);
		actionbar=getActionBar();
		actionbar.hide();
	}

}
