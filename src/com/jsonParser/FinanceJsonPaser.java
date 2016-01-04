package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.FinanceBean;

public class FinanceJsonPaser {
	
	public static List<FinanceBean> getlist(String str){
		
		List<FinanceBean> list=new ArrayList<FinanceBean>();
		
		try {
			JSONObject obj=new JSONObject(str);
			JSONArray array=obj.getJSONArray("data");
			for(int i=0;i<array.length();i++){
				FinanceBean bean=new FinanceBean();
				JSONObject objs=array.getJSONObject(i);
				bean.setLog_des(objs.getString("log_des"));
				bean.setLog_id(objs.getString("log_id"));
				bean.setLog_price(obj.getString("log_price"));
				bean.setLog_time(objs.getString("log_time"));
				bean.setLog_type(objs.getString("log_type"));
				bean.setLog_typename(objs.getString("log_typename"));
				list.add(bean);
				
			}
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
