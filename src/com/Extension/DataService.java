package com.Extension;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.os.Message;

public class DataService extends Thread {
	Handler handler;
	int state;
	HashMap<String, String> mlistHashMap;

	public DataService(Handler _handler, int _state,
			HashMap<String, String> _paramsList) {
		handler = _handler;
		state = _state;
		mlistHashMap = _paramsList;

	}

	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		try {
			String result = null;

			try {
				result = getRemoteInfo(state, mlistHashMap);//调用方法，开始获取数据，传入参数
				if (result.equals("FunctionError")) {
					return;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			msg.obj = result;
			msg.what = 0;
			msg.arg1=state;
			handler.sendMessage(msg);

		} catch (InterruptedException e) {
			e.printStackTrace();
			msg.obj = "runError";
			msg.what = 0;
			handler.sendMessage(msg);
		}

	}

	public String getRemoteInfo(int _state,
			HashMap<String, String> _paramsList) throws InterruptedException,
			IOException {

		String requestUrl = "http://www.91jf.com/api.php";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(requestUrl);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		_paramsList.put("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		_paramsList.put("key", "71d1dd35b75718a722bae7068bdb3e1a");
		@SuppressWarnings("rawtypes")
		Iterator it = _paramsList.keySet().iterator();//迭代集合
		while (it.hasNext()) {
			String keyString = (String) it.next();

			String valueString = _paramsList.get(keyString).toString();
			params.add(new BasicNameValuePair(keyString, valueString));//添加参数

		}

		try {
			//开始进行网络请求，来获取数据
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = client.execute(post);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity());
				return result;
			} else {
				return "";
			}
		} catch (Exception e) {

			return "FunctionError";
		}

	}

}
