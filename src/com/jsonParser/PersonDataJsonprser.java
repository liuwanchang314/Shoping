package com.jsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import com.bean.PersonDataBean;

/*
 *  "api": "APISUCCESS",
    "data": {
        "cname": "apitestuser",
        "sex": "ÄÐ",
        "tel": "",
        "address": "",
        "qq": "",
        "wechat": "",
        "email": "apitestuser@91jf.com",
        "head_img": "",
        "wanwan": null,
        "wx_openid": ""
    },
    "status": 1
 * 
 * **/
public class PersonDataJsonprser {
	
	public static PersonDataBean getbean(String str){
		PersonDataBean bean=new PersonDataBean();
		try {
			
			JSONObject obj=new JSONObject(str);
			JSONObject objs=obj.getJSONObject("data");
			bean.setAddress(objs.getString("address"));
			bean.setCname(objs.getString("cname"));
			bean.setEmail(objs.getString("email"));
			bean.setHead_img(objs.getString("head_img"));
			bean.setQq(objs.getString(objs.getString("name")));
			bean.setSex(objs.getString("sex"));
			bean.setTel(objs.getString("tel"));
			bean.setWanwan(objs.getString("wanwan"));
			bean.setWechat(objs.getString("wechat"));
			bean.setWx_openid(objs.getString("wx_openid"));
			return bean;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;

		
	}
	
	
	

}
