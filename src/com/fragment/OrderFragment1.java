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

/**
 * @author JZKJ-LWC
 * @date : 2015-12-8 上午11:41:44
 * 这个fragment所显示的是全部订单
 * 一个listview中，每个item所显示的数据不一定相同，布局格式也一样，所以，这里的
 * listview需要有2种item布局，在适配器当中对其进行判断就好
 * 2种布局分别为
 * 1另外三种
 * 2待评价，多了一个删除本条按钮
 */  

@SuppressLint("NewApi")
public class OrderFragment1 extends Fragment {
	
	
	private View mView;
	
	private ListView mListview;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView=inflater.inflate(R.layout.order_fragment1,null);
		mListview=(ListView) mView.findViewById(R.id.orderfragment1_listview);
		
		return mView;
	}

}
