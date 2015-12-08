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
