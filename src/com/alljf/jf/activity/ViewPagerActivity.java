package com.alljf.jf.activity;

import java.util.ArrayList;
import java.util.List;
import com.Application.SysApplication;
import com.alljf.jf.R;
import com.alljf.jf.activity.activity_qidongye;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * 
 * 引导页
 * 
 * @author fangood163@163.com
 * 
 * @since 创建时间：2015年4月7日 下午2:11:09
 * 
 * @version V1.0
 * 
 *
 */
public class ViewPagerActivity extends FragmentActivity {

	private ViewPager viewPage;
	private Fragment1 mFragment1;
	private Fragment2 mFragment2;
	private Fragment3 mFragment3;
	private PagerAdapter mPgAdapter;
	private RadioGroup dotLayout;
	private List<Fragment> mListFragment = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_viewpager);
		SysApplication.getInstance().addActivity(this);
		//首先，需要一个sharedpreference对象，用来存储配置文件信息
		SharedPreferences preferences=getSharedPreferences("loading",Context.MODE_PRIVATE);
		//首先先读取数据
		boolean TAG=preferences.getBoolean("LoadingTAG",false);//如果不存在，说明是第一次加载，所以这里需要启动引导页
		if(TAG){
		//为真，则不是第一次加载，所以直接跳转到启动页
		Intent intent=new Intent(this,activity_qidongye.class);
		startActivity(intent);
		}else{
		//则是第一次加载，所以，
		Editor editor=preferences.edit();
		editor.putBoolean("LoadingTAG",true);
		editor.commit();
		initView();
		viewPage.setOnPageChangeListener(new MyPagerChangeListener());
		}
	}

	private void initView() {
		dotLayout = (RadioGroup) findViewById(R.id.advertise_point_group);
		viewPage = (ViewPager) findViewById(R.id.viewpager);
		mFragment1 = new Fragment1();
		mFragment2 = new Fragment2();
		mFragment3 = new Fragment3();
		mListFragment.add(mFragment1);
		mListFragment.add(mFragment2);
		mListFragment.add(mFragment3);
		mPgAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
				mListFragment);
		viewPage.setAdapter(mPgAdapter);

	}

	public class MyPagerChangeListener implements OnPageChangeListener {

		public void onPageSelected(int position) {

		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			((RadioButton) dotLayout.getChildAt(position)).setChecked(true);
		}

	}
}

