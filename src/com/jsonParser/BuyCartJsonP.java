package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.BuyCartBean;

/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "store_name": "jiafangyan",
            "cart_id": "3935",
            "goods_id": "4400",
            "goods_name": "½ö¹©²âÊÔÇëÎð¹ºÂò ÌìÀ¶É«,1.8m£¨6Ó¢³ß£©´²",
            "goods_price": "300.00",
            "goods_num": "1",
            "goods_image": "http://www.91jf.com/attachment/goods/428/20151015015628876.jpg_60_60.jpg",
            "spec_id": "42713"
        },

 * */
public class BuyCartJsonP {
	
	public static List<BuyCartBean> getlist(String str){
		List<BuyCartBean> list=new ArrayList<BuyCartBean>();
		try {
			JSONObject obj=new JSONObject(str);
			JSONArray array=obj.getJSONArray("data");
			for(int i=0;i<array.length();i++){
				JSONObject objs=array.getJSONObject(i);
				BuyCartBean bean=new BuyCartBean();
				bean.setCart_id(objs.getString("cart_id"));
				bean.setGoods_id(objs.getString("goods_id"));
				bean.setGoods_image(objs.getString("goods_image"));
				bean.setGoods_name(objs.getString("goods_name"));
				bean.setGoods_num(objs.getString("goods_num"));
				bean.setGoods_price(objs.getString("goods_price"));
				bean.setSpec_id(objs.getString("spec_id"));
				bean.setStore_name(objs.getString("store_name"));
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
