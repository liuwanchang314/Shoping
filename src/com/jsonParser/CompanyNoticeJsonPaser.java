package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.CompanyNoticeBean;

/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "article_title": "新年快乐",
            "article_hit": "38",
            "article_id": "209",
            "article_time": "2015-02-10 14:42:28"
        },
        {
            "article_title": "公司公告",
            "article_hit": "60",
            "article_id": "208",
            "article_time": "2015-02-10 14:40:11"
        }
    ],
    "status": 1
}
 * */
public class CompanyNoticeJsonPaser {
	
	public static List<CompanyNoticeBean> GetBean(String str){
		List<CompanyNoticeBean> list=new ArrayList<CompanyNoticeBean>();
		
		try {
			JSONObject obj=new JSONObject(str);
			JSONArray array=obj.getJSONArray("data");
			int count=array.length();
			for(int i=0;i<count;i++){
				JSONObject objs=array.getJSONObject(i);
				CompanyNoticeBean bean=new CompanyNoticeBean();
				bean.setArticle_hit(objs.getString("article_hit"));
				bean.setArticle_id(objs.getString("article_id"));
				bean.setArticle_time(objs.getString("article_time"));
				bean.setArticle_title(objs.getString("article_title"));
				list.add(bean);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
	}

}
