package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.BillBean;

/**
 * "api": "APISUCCESS",
    "data": [
        {
            "inv_type": "1",
            "inv_title": "个人",
            "inv_content": "装修材料",
            "company_name": "",
            "inv_id": "48",
            "default": "0"
        },
        {
            "inv_type": "1",
            "inv_title": "单位",
            "inv_content": "装修材料",
            "company_name": "不知道",
            "inv_id": "50",
            "default": "0"
        },
        {
            "inv_type": "2",
            "inv_title": "",
            "inv_content": "",
            "company_name": "呵呵",
            "inv_id": "52",
            "default": "1"
        }
    ],
    "status": 1
}
 * */
public class BillJsonPaser {
	
	public static List<BillBean> getlist(String str){
		
		List<BillBean> list=new ArrayList<BillBean>();
		try {
			JSONObject obj=new JSONObject(str);
			JSONArray array=obj.getJSONArray("data");
			for(int i=0;i<array.length();i++){
				BillBean bean=new BillBean();
				JSONObject objs=array.getJSONObject(i);
				bean.setCompany_name(objs.getString("company_name"));
				bean.setDefaults(objs.getString("default"));
				bean.setInv_content(objs.getString("inv_content"));
				bean.setInv_id(objs.getString("inv_id"));
				bean.setInv_title(objs.getString("inv_title"));
				bean.setInv_type(objs.getString("inv_type"));
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
