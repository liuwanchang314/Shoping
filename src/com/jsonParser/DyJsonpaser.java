package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.bean.Dynamicdetailsbean;


/**
 * {
    "api": "APISUCCESS",
    "data": {
        "article_title": "新年快乐",
        "article_content": "<img src=\"/attachment/editor/image/20150210/20150210144221_63898.gif\" alt=\"\" />新年快乐哦~",
        "article_hit": "38",
        "article_time": "2015-02-10 14:42:28"
    },
    "status": 1
}
 * */
public class DyJsonpaser {
	
	public static Dynamicdetailsbean getlist(String str){
		
		Dynamicdetailsbean bean = null;
		try {
			JSONObject obj=new JSONObject(str);
			JSONObject objs=obj.getJSONObject("data");
			bean = new Dynamicdetailsbean();
			bean.setArticle_title(objs.getString("article_title"));
			bean.setArticle_time(objs.getString("article_time"));
			bean.setArticle_hit(objs.getString("article_hit"));
			bean.setArticle_content(objs.getString("article_content"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bean;
		
		
		
	}
	

}
