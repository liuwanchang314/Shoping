package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;

import com.bean.SubjectBean;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-10 下午3:24:53 
 * 类说明 
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "subject_id": "1",
            "subject_content": "商家不同意退款 ",
            "subject_desc": "买家申请退款被拒绝。",
            "subject_state": "1"
        },
        {
            "subject_id": "2",
            "subject_content": "未收到货 ",
            "subject_desc": "交易成功，未收到货，钱已经付给商家，可进行维权。",
            "subject_state": "1"
        },
        {
            "subject_id": "3",
            "subject_content": "售后保障服务 ",
            "subject_desc": "交易完成后30天内，在使用商品过程中，发现商品有质量问题或无法正常使用，可进行维权。",
            "subject_state": "1"
        }
    ],
    "status": 1
}
 */
public class SubjectJsonparser {
	public static List<SubjectBean> getlist(String str){
		List<SubjectBean> list=new ArrayList<SubjectBean>();
		try {
			
			JSONObject obj=new JSONObject(str);
			JSONArray array=obj.getJSONArray("data");
			for(int i=0;i<array.length();i++){
				JSONObject objs=array.getJSONObject(i);
				SubjectBean bean=new SubjectBean();
				bean.setSubject_content(objs.getString("subject_content"));
				bean.setSubject_desc(objs.getString("subject_desc"));
				bean.setSubject_id(objs.getString("subject_id"));
				bean.setSubject_state(objs.getString("subject_state"));
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
