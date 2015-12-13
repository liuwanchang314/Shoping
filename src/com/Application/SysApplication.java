package com.Application;

import java.util.LinkedList;
import java.util.List;



import com.Model.UserInfo;

import android.app.Activity;
import android.app.Application;

public class SysApplication extends Application {

	private List<Activity> mlist = new LinkedList<Activity>();

	private static UserInfo user;

	private static SysApplication instance;

	private Activity tempActivitie = new Activity();
	
	private boolean  islogin = false;
	
	
	public boolean getIsLogin(){
		return islogin;
	}

	public void logOut(){
		islogin = false;
		user = null;
	}

	// ��ȡʵ��
	public synchronized static SysApplication getInstance() {
		if (instance == null)
			instance = new SysApplication();
		return instance;
	}

	// �����Ҫ����ɾ���ҳ��
	public void addActivity(Activity activity) {
		mlist.add(activity);
	}

	// ���õ�½�û�����Ϣ
	public void addUserInfo(UserInfo model) {
		islogin = true;
		user = model;
	}

	// ��ȡ��½�û�����Ϣ
	public UserInfo getUserInfo() {
		return user;
	}

	// �����˳�
	public void exit() {
		for (Activity activity : mlist)
			if (activity != null)
				activity.finish();
	}

	// ���Ҫˢ�µ�ҳ��
	public void addTempActivity(Activity activity) {
		tempActivitie = activity;
	}

	// �ȹر�Ҫˢ�µ�ҳ��
	public void refurbish() {
		tempActivitie.finish();
		tempActivitie = null;
	}

}
