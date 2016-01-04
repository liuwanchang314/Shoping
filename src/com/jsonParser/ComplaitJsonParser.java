package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.ComplaintBean;

/**
 *  "api": "APISUCCESS",
    "data": [
        {
            "0": "32",
            "1": "1474",
            "2": "7341",
            "3": "apitestuser",
            "4": "428",
            "5": "jiafangyan",
            "6": "商家不同意退款 ",
            "7": "1",
            "8": "12123发",
            "9": "nabin/upload/7341/complain_pic11450771629.jpg",
            "10": "nabin/upload/7341/complain_pic21450771629.jpg",
            "11": "nabin/upload/7341/complain_pic31450771629.jpg",
            "12": "1450771629",
            "13": "0",
            "14": "0",
            "15": "",
            "16": "0",
            "17": "",
            "18": "",
            "19": "",
            "20": "",
            "21": "0",
            "22": "0",
            "23": "10",
            "24": "1",
            "25": "220504115629967341",
            "complain_id": "32",
            "order_id": "1474",
            "accuser_id": "7341",
            "accuser_name": "apitestuser",
            "accused_id": "428",
            "accused_name": "jiafangyan",
            "complain_subject_content": "商家不同意退款 ",
            "complain_subject_id": "1",
            "complain_content": "12123发",
            "complain_pic1": "nabin/upload/7341/complain_pic11450771629.jpg",
            "complain_pic2": "nabin/upload/7341/complain_pic21450771629.jpg",
            "complain_pic3": "nabin/upload/7341/complain_pic31450771629.jpg",
            "complain_datetime": "1450771629",
            "complain_handle_datetime": "0",
            "complain_handle_member_id": "0",
            "appeal_message": "",
            "appeal_datetime": "0",
            "appeal_pic1": "",
            "appeal_pic2": "",
            "appeal_pic3": "",
            "final_handle_message": "",
            "final_handle_datetime": "0",
            "final_handle_member_id": "0",
            "complain_state": "10",
            "complain_active": "1",
            "complain_sn": "220504115629967341"
        }
    ],
    "status": 1
}
 * */
public class ComplaitJsonParser {
	

	public static List<ComplaintBean> getlist(String str){
		List<ComplaintBean> list=new ArrayList<ComplaintBean>();
		try {
			
			JSONObject obj=new JSONObject(str);
			JSONArray array=obj.getJSONArray("data");
			for(int i=0;i<array.length();i++){
				JSONObject objs=array.getJSONObject(i);
				ComplaintBean bean=new ComplaintBean();
				bean.setAccused_id(objs.getString("accused_id"));
				bean.setAccused_name(objs.getString("accused_name"));
				bean.setAccuser_id(objs.getString("accused_name"));
				bean.setAccuser_name(objs.getString("accuser_name"));
				bean.setAppeal_datetime(objs.getString("appeal_datetime"));
				bean.setAppeal_message(objs.getString("appeal_message"));
				bean.setAppeal_pic1(objs.getString("appeal_pic1"));
				bean.setAppeal_pic2(objs.getString("appeal_pic2"));
				bean.setAppeal_pic3(objs.getString("appeal_pic3"));
				bean.setComplain_active(objs.getString("complain_active"));
				bean.setComplain_content(objs.getString("complain_content"));
				bean.setComplain_datetime(objs.getString("complain_datetime"));
				bean.setComplain_handle_datetime(objs.getString("complain_handle_datetime"));
				bean.setComplain_handle_member_id(objs.getString("complain_handle_member_id"));
				bean.setComplain_id(objs.getString("complain_id"));
				bean.setComplain_pic1(objs.getString("complain_pic1"));
				bean.setComplain_pic3(objs.getString("complain_pic3"));
				bean.setComplain_sn(objs.getString("complain_sn"));
				bean.setComplain_state(objs.getString("complain_state"));
				bean.setComplain_subject_content(objs.getString("complain_subject_content"));
				bean.setComplain_subject_id(objs.getString("complain_subject_id"));
				bean.setFinal_handle_datetime(objs.getString("final_handle_datetime"));
				bean.setFinal_handle_member_id(objs.getString("final_handle_datetime"));
				bean.setFinal_handle_message(objs.getString("final_handle_message"));
				bean.setOrder_id(objs.getString("order_id"));
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
