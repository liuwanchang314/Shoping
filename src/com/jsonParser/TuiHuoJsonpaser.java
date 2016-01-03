package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.TuikuanSHBean;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-3 下午9:30:19 
 * 类说明 
 *  "api": "APISUCCESS",
    "data": [
        {
            "refund_id": "191",
            "refund_sn": "857528151229125717",
            "order_sn": "7000000000170301",
            "store_name": "jiafangyan",
            "goods_id": "0",
            "order_goods_id": "0",
            "goods_name": "全部退款",
            "goods_num": "1",
            "goods_image": "http://www.91jf.com/attachment/goods/428/20151015015802157.jpg_60_60.jpg",
            "refund_price": "10.00",
            "refund_type": "1",
            "seller_status": "1",
            "refund_status": "1",
            "return_type": "1",
            "order_lock": "2",
            "goods_status": "1",
            "refund_time": "2015-12-29 12:57:17",
            "seller_time": "1970-01-01 07:00:00",
            "admin_time": "1970-01-01 07:00:00",
            "buyer_message": "取消订单全部退款",
            "seller_message": "",
            "admin_message": "",
            "express_id": "0",
            "shipping_code": null,
            "shipping_time": "1970-01-01 07:00:00",
            "delay_time": "1970-01-01 07:00:00",
            "receive_time": "1970-01-01 07:00:00",
            "receive_message": "",
            "sale_status": "1",
            "spec_id": "0"
        }
    ],
    "status": 1
}
 */
public class TuiHuoJsonpaser {
	
	public static List<TuikuanSHBean> getlist(String str){
		List<TuikuanSHBean> list=new ArrayList<TuikuanSHBean>();
		try {
			
			JSONObject obj=new JSONObject(str);
			JSONArray array=obj.getJSONArray("data");
			for(int i=0;i<array.length();i++){
				JSONObject objs=array.getJSONObject(i);
				TuikuanSHBean bean=new TuikuanSHBean();
				bean.setAdmin_time(objs.getString("admin_time"));
				bean.setBuyer_message(objs.getString("buyer_message"));
				bean.setDelay_time(objs.getString("delay_time"));
				bean.setExpress_id(objs.getString("express_id"));
				bean.setGoods_id(objs.getString("goods_id"));
				bean.setGoods_image(objs.getString("goods_image"));
				bean.setGoods_name(objs.getString("goods_name"));
				bean.setGoods_num(objs.getString("goods_num"));
				bean.setGoods_status(objs.getString("goods_status"));
				bean.setOrder_goods_id(objs.getString("order_goods_id"));
				bean.setOrder_lock(objs.getString("order_lock"));
				bean.setOrder_sn(objs.getString("order_sn"));
				bean.setReceive_message(objs.getString("receive_message"));
				bean.setReceive_time(objs.getString("receive_time"));
				bean.setRefund_id(objs.getString("refund_id"));
				bean.setRefund_price(objs.getString("refund_price"));
				bean.setRefund_sn(objs.getString("refund_sn"));
				bean.setRefund_type(objs.getString("refund_type"));
				bean.setReturn_type(objs.getString("return_type"));
				bean.setSale_status(objs.getString("sale_status"));
				bean.setSeller_message(objs.getString("seller_message"));
				bean.setSeller_status(objs.getString("seller_status"));
				bean.setSeller_time(objs.getString("seller_time"));
				bean.setShipping_code(objs.getString("shipping_code"));
				bean.setShipping_time(objs.getString("shipping_time"));
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
