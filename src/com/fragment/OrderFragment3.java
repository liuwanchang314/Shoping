package com.fragment;

import com.activity.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("NewApi")
public class OrderFragment3 extends Fragment {
	
	private View mView;
	private ListView mListview;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView=inflater.inflate(R.layout.order_fragment3,null);
		mListview=(ListView) mView.findViewById(R.id.orderfragment3_listview);
		return mView;
	}

}
