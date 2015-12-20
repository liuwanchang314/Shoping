package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.ShangjiazengpingBean;

/**
 *  {
    "api": "APISUCCESS",
    "data": [
        {
        
            "goods_name": "\u7eb8\u5305\u88c5",
            "goods_image": "http:\/\/www.91jf.com\/attachment\/goods\/428\/20151015015741174.jpg_60_60.jpg",
            "goods_price": "16.00",
            "goods_id": "18604",
            "goods_weight": "0.100"
        }
    ],
    "status": 1
}
 * */
public class ShangjiazenpinJsonparser {

	public static List<ShangjiazengpingBean> getlist(String str){
		List<ShangjiazengpingBean> list=new ArrayList<ShangjiazengpingBean>();
		try {
			
			
			JSONObject obj=new JSONObject(str);
			JSONArray array=obj.getJSONArray("data");
			for(int i=0;i<array.length();i++){
				ShangjiazengpingBean bean=new ShangjiazengpingBean();
				JSONObject objs=array.getJSONObject(i);
				bean.setGoods_id(objs.getString("goods_id"));
				bean.setGoods_image(objs.getString("goods_image"));
				bean.setGoods_name(objs.getString("goods_name"));
				bean.setGoods_price(objs.getString("goods_price"));
				bean.setGoods_weight(objs.getString("goods_weight"));
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
