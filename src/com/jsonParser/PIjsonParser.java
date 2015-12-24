package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.PIdimesionAndColor;
import com.bean.PIspec;
import com.bean.ProductInfoBean;

/**
 * 
 *  {
    "api": "APISUCCESS",
    "data": {
        "store_id": "428",
        "store_name": "jiafangyan",
        "goods_name": "½ö¹©²âÊÔÇëÎð¹ºÂò",
        "class_id": "105",
        "class_name": "´²Æ·Ì×¼þ&#160;>&#160;ËÄ¼þÌ×&#160;>&#160;Ìá»¨ËÄ¼þÌ×",
        "goods_price": "10.00",
        "goods_price_interval": "10.00 - 12.00",
        "goods_status": "1",
        "goods_spec": [
            {
                "id": 11,
                "value": [
                    {
                        "id": 47,
                        "value": "ÄÛ·Û"
                    },
                    {
                        "id": 51,
                        "value": "Ç³»ÒÉ«"
                    },
                    {
                        "id": 68,
                        "value": "¾ÆºìÉ«"
                    }
                ]
            },
            {
                "id": 10,
                "value": [
                    {
                        "id": 40,
                        "value": "1.5m£¨5Ó¢³ß£©´²"
                    },
                    {
                        "id": 41,
                        "value": "1.8m£¨6Ó¢³ß£©´²"
                    }
                ]
            }
        ],
        "goods_content": "<img src=\"http://www.91jf.com/attachment/editor/image/20150626/20150626093522_74506.jpg\" alt=\"\" />",
        "goods_image": "/attachment/goods/428/20151015015802157.jpg_250_250.jpg",
        "goods_image_thumb": "/attachment/goods/428/20151015015802157.jpg_60_60.jpg,/attachment/goods/428/20150713090506802.jpg_60_60.jpg",
        "sale_num": "134",
        "collect_num": "0"
    },
    "status": 1
 * */
public class PIjsonParser {
	
	public static ProductInfoBean getbean(String str){
		
		ProductInfoBean bean=new ProductInfoBean();
		try {
			JSONObject obj=new JSONObject(str);
			JSONObject objs=obj.getJSONObject("data");
			bean.setStore_id(objs.getString("store_id"));
			bean.setStore_name(objs.getString("store_name"));
			bean.setClass_id(objs.getString("class_id"));
			bean.setClass_name(objs.getString("class_name"));
			bean.setCollect_num(objs.getString("collect_num"));
			bean.setGoods_content(objs.getString("goods_content"));
			bean.setGoods_image(objs.getString("goods_image"));
			bean.setGoods_image_thumb(objs.getString("goods_image_thumb"));
			bean.setGoods_name(objs.getString("goods_name"));
			bean.setGoods_price(objs.getString("goods_price"));
			bean.setGoods_price_interval(objs.getString("goods_price_interval"));
			bean.setGoods_status(objs.getString("goods_status"));
			bean.setSale_num(objs.getString("sale_num"));
			JSONArray array=objs.getJSONArray("goods_spec");
			List<PIspec> listss=new ArrayList<PIspec>();
			int count=array.length();
			for(int i=0;i<count;i++){
				JSONObject objsp=array.getJSONObject(i);
				PIspec ps=new PIspec();
				ps.setId(objsp.getString("id"));
				JSONArray arrays=objsp.getJSONArray("value");
				List<PIdimesionAndColor> lists=new ArrayList<PIdimesionAndColor>();
				for(int j=0;j<arrays.length();j++){
					JSONObject objdc=arrays.getJSONObject(j);
					PIdimesionAndColor pidc=new PIdimesionAndColor();
					pidc.setId(objdc.getString("id"));
					pidc.setValue(objdc.getString("value"));
					lists.add(pidc);
				}
				ps.setList(lists);
				listss.add(ps);
			}
			bean.setList(listss);
			return bean;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bean;
		
	}

}
