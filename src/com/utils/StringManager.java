package com.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.LogManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class StringManager {

	/**
	 * 从json中获取MAP数组
	 * @param json
	 * @return
	 */
	public static ArrayList<Map<String, String>> getListMapByJson(Object json) {
		ArrayList<Map<String, String>> objs = new ArrayList<Map<String, String>>();
		JSONArray array = new JSONArray();
		// 尝试解析
		try {
			if (json == null)
				return objs;
			else if (json.getClass() == JSONArray.class)
				array = (JSONArray) json;
			else if (((String) json).length() == 0)
				return objs;
			else
				array = new JSONArray((String) json);
		} catch (JSONException e1) {
			try {
				array.put(new JSONObject((String) json));
			} catch (JSONException e2) {
				Log.e("Json解析", "Json无法解析");
			}
		}
		for (int i = 0; i < array.length(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			try {
				Iterator<?> it = array.getJSONObject(i).keys();
				while (it.hasNext()) {
					String key = (String) it.next();
					Object xx=array.getJSONObject(i).get(key);
					map.put(key, xx.toString());
				}
			} catch (Exception e) {
				// 直接取数组值到map中,key为空
				try {
					map.put("", array.get(i).toString());
				} catch (JSONException e1) {
					Log.e("json解析", "json无法解析");
				}
			}
			objs.add(map);
		}
		return objs;
	}
}
