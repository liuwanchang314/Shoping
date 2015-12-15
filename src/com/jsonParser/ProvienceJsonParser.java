package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.ProvienceBean;

/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "name": "±±¾©",
            "id": "001001"
        },
        {
            "name": "Ìì½ò",
            "id": "001002"
        },
 * */
public class ProvienceJsonParser {

	
	public static List<ProvienceBean> getlist(String str){
		List<ProvienceBean> list=new ArrayList<ProvienceBean>();
		
		try {
			JSONObject obj=new JSONObject(str);
			JSONArray array=obj.getJSONArray("data");
			for(int i=0;i<array.length();i++){
				ProvienceBean bean=new ProvienceBean();
				JSONObject objs=array.getJSONObject(i);
				bean.setId(objs.getString("id"));
				bean.setName(objs.getString("name"));
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
