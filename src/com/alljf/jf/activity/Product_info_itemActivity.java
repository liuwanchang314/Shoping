package com.alljf.jf.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.alljf.jf.R;


public class Product_info_itemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_product_info_item);
	}


}
