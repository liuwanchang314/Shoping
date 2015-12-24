package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.productclasschild;
import com.bean.productclassgroup;
/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "class_id": "720",
            "class_name": "蓝色海洋",
            "class_list": []
        },
        {
            "class_id": "721",
            "class_name": "田园风光",
            "class_list": []
        },
        {
            "class_id": "727",
            "class_name": "青春岁月",
            "class_list": [
                {
                    "class_id": "745",
                    "class_name": "可爱卡通"
                },
                {
                    "class_id": "744",
                    "class_name": "迷人风情"
                }
            ]
        }
    ],
    "status": 1
}
 * */
public class ProductClassJsonpaser {
	
	public static List<productclassgroup> getlist(String string){
		List<productclassgroup> list=new ArrayList<productclassgroup>();
		try {
			
			JSONObject  obj=new JSONObject(string);
			JSONArray array=obj.getJSONArray("data");
			int count=array.length();
			for(int i=0;i<count;i++){
				productclassgroup bean=new productclassgroup();
				JSONObject objs=array.getJSONObject(i);
				bean.setClass_id(objs.getString("class_id"));
				bean.setClass_name(objs.getString("class_name"));
				JSONArray arrays=objs.getJSONArray("class_list");
				//生成一个集合，用来存储子类目录
				List<productclasschild> lists=new ArrayList<productclasschild>();
				for(int j=0;j<arrays.length();j++){
					
					productclasschild child=new productclasschild();
					JSONObject objss=arrays.getJSONObject(j);
					child.setClass_id(objss.getString("class_id"));
					child.setClass_name(objss.getString("class_name"));
					//生成子类对象
					lists.add(child);
				}
				//将集合属性设置
				bean.setList(lists);
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
