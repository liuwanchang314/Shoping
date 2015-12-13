package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.AllProductBean;

/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "goods_id": "4400",
            "goods_name": null,
            "goods_image": "/attachment/goods/428/20151015015628876.jpg_250_250.jpg",
            "goods_price": "0.01"
        },
        {
            "goods_id": "27019",
            "goods_name": null,
            "goods_image": "/attachment/goods/428/20151015015802157.jpg_250_250.jpg",
            "goods_price": "10.00"
        },
        {
            "goods_id": "8459",
            "goods_name": null,
            "goods_image": "/attachment/goods/428/20150813020848288.jpg_250_250.jpg",
            "goods_price": "10.00"
        },
        {
            "goods_id": "21129",
            "goods_name": null,
            "goods_image": "/attachment/goods/428/20150924113134773.jpg_250_250.jpg",
            "goods_price": "12.00"
        },
        {
            "goods_id": "21130",
            "goods_name": null,
            "goods_image": "/attachment/goods/428/20151015015908781.jpg_250_250.jpg",
            "goods_price": "120.00"
        },
        {
            "goods_id": "29318",
            "goods_name": null,
            "goods_image": "/attachment/banner/201510/20151024021124798.jpg",
            "goods_price": "699.00"
        },
        {
            "goods_id": "18604",
            "goods_name": null,
            "goods_image": "/attachment/goods/428/20151015015741174.jpg_250_250.jpg",
            "goods_price": "16.00"
        },
        {
            "goods_id": "27020",
            "goods_name": null,
            "goods_image": "/attachment/goods/428/20150813022154705.jpg_250_250.jpg",
            "goods_price": "0.01"
        },
        {
            "goods_id": "12238",
            "goods_name": null,
            "goods_image": "/attachment/goods/428/20150813022205559.jpg_250_250.jpg",
            "goods_price": "20.00"
        }
    ],
    "status": 1
}
 * */
public class AllProductDataJson {
	
	public static List<AllProductBean> GetProductData(String jsonstring){
		List<AllProductBean> list=new ArrayList<AllProductBean>();
		try {
			JSONObject obj=new JSONObject(jsonstring);
			JSONArray array=obj.getJSONArray("data");
			int count=array.length();
			for(int i=0;i<count;i++){
				//生成实体类对象
				AllProductBean bean=new AllProductBean();
				JSONObject objs=array.getJSONObject(i);
				bean.setId(objs.getString("goods_id"));
				bean.setImage(objs.getString("goods_image"));
				bean.setName(objs.getString("goods_name"));
				bean.setPrice(objs.getString("goods_price"));
				list.add(bean);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}

}
