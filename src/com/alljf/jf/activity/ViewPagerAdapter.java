package com.alljf.jf.activity;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 
 * 类说明
 * 
 * @author fangood163@163.com
 * 
 * @since 创建时间：2015年4月7日 下午2:34:46
 * 
 * @version V1.0
 * 
 *
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragmentList = new ArrayList<Fragment>();

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public ViewPagerAdapter(FragmentManager fragmentManager,
			List<Fragment> arrayList) {
		super(fragmentManager);
		this.fragmentList = arrayList;
	}

	@Override
	public Fragment getItem(int arg0) {
		return fragmentList.get(arg0);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

}
