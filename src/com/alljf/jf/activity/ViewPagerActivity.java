package com.alljf.jf.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.alljf.jf.R;
/**
 * ViewPager 引导
 *com.zhangyx.MyLauncherGuide.activity.viewPage.ViewPagerActivity
 *
 */
public class ViewPagerActivity extends FragmentActivity {
	private ViewPager mVPActivity;
	private Fragment1 mFragment1;
	private Fragment2 mFragment2;
	private Fragment3 mFragment3;
	private List<Fragment> mListFragment = new ArrayList<Fragment>();
	private PagerAdapter mPgAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_viewpager);
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
				}
	}

	private void initView() {
		mVPActivity = (ViewPager) findViewById(R.id.vp_activity);
		mFragment1 = new Fragment1();
		mFragment2 = new Fragment2();
		mFragment3 = new Fragment3();
		mListFragment.add(mFragment1);
		mListFragment.add(mFragment2);
		mListFragment.add(mFragment3);
		mPgAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
				mListFragment);
		mVPActivity.setAdapter(mPgAdapter);
	}
}
