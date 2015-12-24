package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.TakeGoodsAddressBean;

/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "receive_name": "����",
            "area_info": "�Ĵ� ���� ͨ�����Խֵ���ַ",
            "mob_phone": "13356847878",
            "default": "0",
            "address_id": "761",
            "tel_phone": ""
        },
        {
            "receive_name": "�ڶ���ַ",
            "area_info": "�㶫 ��Զ ��ǲ�֪������Ľֵ�",
            "mob_phone": "13812455421",
            "default": "1",
            "address_id": "763",
            "tel_phone": ""
        }
    ],
    "status": 1
}
 * */
public class TakeGoodsAddressJsonpaser {
	
	public static List<TakeGoodsAddressBean> getlist(String str){
		List<TakeGoodsAddressBean> list=new ArrayList<TakeGoodsAddressBean>();
		try {
			
			JSONObject obj=new JSONObject(str);
			JSONArray array=obj.getJSONArray("data");
			for(int i=0;i<array.length();i++){
				TakeGoodsAddressBean bean=new TakeGoodsAddressBean();
				JSONObject objs=array.getJSONObject(i);
				bean.setAddress_id(objs.getString("address_id"));
				bean.setArea_info(objs.getString("area_info"));
				bean.setDefaults(objs.getString("default"));
				bean.setMob_phone(objs.getString("mob_phone"));
				bean.setReceive_name(objs.getString("receive_name"));
				bean.setTel_phone(objs.getString("tel_phone"));
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
