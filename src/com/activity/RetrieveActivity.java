package com.activity;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import org.json.JSONObject;

import com.Extension.DataService;
import com.Model.RelInfo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Spinner;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 下午1:31:57
 *找回密码界面
 */  
public class RetrieveActivity extends Activity {

	Spinner spinner;
	List<RelInfo> mlist = new ArrayList<RelInfo>();
	DataService client;
	Handler handler;
	HashMap<String, String> list = new HashMap<String, String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retrieve);
		spinner = (Spinner) findViewById(R.id.ret_txt_one);

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String data = "";
				JSONObject jsonObject;
				try {

					jsonObject = new JSONObject(msg.obj.toString());
					data = jsonObject.getString("questions");

				} catch (Exception exp) {

				}
			}
		};
		addRetItem();

	}

	private void addRetItem() {
		list.put("type", "system");
		list.put("part", "question");
		client = new DataService(handler, 0, list);
		client.start();
	}

}
