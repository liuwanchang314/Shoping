package com.jsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import com.bean.TousuxiangqingBean;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-6 下午11:15:13 
 * 类说明 
 */
public class TousuxiangqingJsonParser {
	
	public static TousuxiangqingBean getbena(String str){
		
		TousuxiangqingBean bean = null;
		try {
			JSONObject obj=new JSONObject(str);
			JSONObject objs=obj.getJSONObject("data");
			bean = new TousuxiangqingBean();
			bean.setAccused_id(objs.getString("accused_id"));
			bean.setAccused_name(objs.getString("accused_id"));
			bean.setAccuser_id(objs.getString("accuser_id"));
			bean.setAccuser_name(objs.getString("accuser_name"));
			bean.setAppeal_datetime(objs.getString("appeal_datetime"));
			bean.setAppeal_message(objs.getString("appeal_message"));
			bean.setAppeal_pic1(objs.getString("appeal_pic1"));
			bean.setAppeal_pic2(objs.getString("appeal_pic2"));
			bean.setAppeal_pic3(objs.getString("appeal_pic3"));
			bean.setComplain_active(objs.getString("complain_active"));
			bean.setComplain_datetime(objs.getString("complain_datetime"));
			bean.setComplain_handle_datetime(objs.getString("complain_handle_datetime"));
			bean.setComplain_handle_member_id(objs.getString("complain_handle_member_id"));
			bean.setComplain_pic1(objs.getString("complain_pic1"));
			bean.setComplain_pic2(objs.getString("complain_pic2"));
			bean.setComplain_pic3(objs.getString("complain_pic3"));
			bean.setComplain_sn(objs.getString("complain_sn"));
			bean.setComplain_subject_content(objs.getString("complain_subject_content"));
			bean.setComplain_subject_id(objs.getString("complain_subject_id"));
			bean.setFinal_handle_datetime(objs.getString("complain_subject_id"));
			bean.setFinal_handle_member_id(objs.getString("final_handle_member_id"));
			bean.setFinal_handle_message(objs.getString("final_handle_message"));
			bean.setOrder_id(objs.getString("order_id"));
			return bean;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
		
	}

}
