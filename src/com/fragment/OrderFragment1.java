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
 * @date : 2015-12-8 ����11:41:44
 * ���fragment����ʾ����ȫ������
 * һ��listview�У�ÿ��item����ʾ�����ݲ�һ����ͬ�����ָ�ʽҲһ�������ԣ������
 * listview��Ҫ��2��item���֣������������ж�������жϾͺ�
 * 2�ֲ��ֱַ�Ϊ
 * 1��������
 * 2�����ۣ�����һ��ɾ��������ť
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
