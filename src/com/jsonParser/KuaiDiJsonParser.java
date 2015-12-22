package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.KuaiDiBean;

/**
 * 
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "transport_id": "3",
            "express_title": "ÉêÍ¨¿ìµÝ",
            "express_id": null,
            "express_note": ""
        },
        {
            "transport_id": "4",
            "express_title": "TNT¿ìµÝ",
            "express_id": null,
            "express_note": ""
        }
    ],
    "status": 1
 * */
public class KuaiDiJsonParser {

	
	public static List<KuaiDiBean> getlist(String str){
		List<KuaiDiBean> list = null;
		try {
			list = new ArrayList<KuaiDiBean>();
			JSONObject obj=new JSONObject(str);
			
			JSONArray array=obj.getJSONArray("data");
			for(int i=0;i<array.length();i++){
				JSONObject objs=array.getJSONObject(i);
				KuaiDiBean bean=new KuaiDiBean();
				bean.setExpress_id(objs.getString("express_id"));
				bean.setExpress_note(objs.getString("express_note"));
				bean.setExpress_title(objs.getString("express_title"));
				bean.setTransport_id(objs.getString("transport_id"));
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
