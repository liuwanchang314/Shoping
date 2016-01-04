package com.jsonParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.OrderBean;
import com.bean.OrderGoodsBean;
import com.bean.OrderRecieveInfoBean;

public class OrderJsonParser {
	/**
	 *  {
    "api": "APISUCCESS",
    "data": [
        {
            "order_goods": {
                "order_goods_id": "2352",
                "goods_id": "4400",
                "goods_name": "\u4ec5\u4f9b\u6d4b\u8bd5\u8bf7\u52ff\u8d2d\u4e70 \u5929\u84dd\u8272,2.0m\uff086.6\u82f1\u5c3a\uff09\u5e8a",
                "goods_price": "300.00",
                "goods_num": "1",
                "goods_image": "http:\/\/www.91jf.com\/attachment\/goods\/428\/20151015015628876.jpg_60_60.jpg",
                "goods_pay_price": "300.00",
                "spec_id": "42714"
            },
            "order_id": "1474",
            "order_sn": "7000000000151401",
            "pay_sn": "830504115580795341",
            "store_name": "jiafangyan",
            "buyer_email": "apitestuser@91jf.com",
            "order_time": "2015-12-22 16:06:20",
            "pay_code": "predeposit",
            "pay_time": "2015-12-22 16:06:20",
            "end_time": "1970-01-01 07:00:00",
            "goods_price": "300.00",
            "order_price": "300.00",
            "pd_price": "300.00",
            "shipping_price": "0.00",
            "evaluation_status": "",
            "order_status": "20",
            "refund_status": "0",
            "lock_status": "0",
            "refund_price": "0.00",
            "delay_time": "1970-01-01 07:00:00",
            "shipping_code": "",
            "poundage_price": "0.00",
            "shipping_mode": "\u5546\u5bb6\u53d1\u8d27",
            "shipping_time": "1970-01-01 07:00:00",
            "express_id": "28",
            "express_name": null,
            "evaluation_time": "1970-01-01 07:00:00",
            "evalseller_status": "0",
            "evalseller_time": "1970-01-01 07:00:00",
            "order_message": "",
            "deliver_explain": "",
            "daddress_id": "0",
            "reciver_name": "\u6d4b\u8bd5",
            "reciver_info": {
                "address": "\u56db\u5ddd \u5df4\u4e2d \u901a\u6c5f&nbsp;\u6d4b\u8bd5\u8857\u9053\u5730\u5740",
                "phone": "13356847878"
            },
            "reciver_province_id": "001023",
            "invoice_info": [
            ]
        },
	 * */
	
	public static List<OrderBean> getlist(String tr){
		List<OrderBean> list=new ArrayList<OrderBean>();
		try {
			JSONObject obj=new JSONObject(tr);
			JSONArray array=obj.getJSONArray("data");
			for(int i=0;i<array.length();i++){
				JSONObject objs=array.getJSONObject(i);
				OrderBean bean=new OrderBean();
				JSONObject objss=objs.getJSONObject("order_goods");
				OrderGoodsBean beans=new OrderGoodsBean();
				beans.setGoods_id(objss.getString("goods_id"));
				beans.setGoods_image(objss.getString("goods_image"));
				beans.setGoods_name(objss.getString("goods_name"));
				beans.setGoods_num(objss.getString("goods_num"));
				beans.setGoods_pay_price(objss.getString("goods_pay_price"));
				beans.setGoods_price(objss.getString("goods_price"));
				beans.setOrder_goods_id(objss.getString("order_goods_id"));
				beans.setSpec_id(objss.getString("spec_id"));
				bean.setOrdergoods(beans);
				bean.setBuyer_email(objs.getString("buyer_email"));
				bean.setDaddress_id(objs.getString("daddress_id"));
				bean.setDelay_time(objs.getString("delay_time"));
				bean.setDeliver_explain(objs.getString("deliver_explain"));
				bean.setEnd_time(objs.getString("end_time"));
				bean.setEvaluation_status(objs.getString("evaluation_status"));
				bean.setEvaluation_time(objs.getString("evaluation_time"));
				bean.setExpress_id(objs.getString("express_id"));
				bean.setGoods_price(objs.getString("goods_price"));
				bean.setExpress_name(objs.getString("express_name"));
				bean.setLock_status(objs.getString("lock_status"));
				bean.setOrder_id(objs.getString("order_id"));
				bean.setOrder_message(objs.getString("order_message"));
				bean.setOrder_price(objs.getString("order_price"));
				bean.setOrder_sn(objs.getString("order_sn"));
				bean.setOrder_status(objs.getString("order_status"));
				bean.setOrder_time(objs.getString("order_time"));
				bean.setPay_sn(objs.getString("pay_sn"));
				bean.setPay_time(objs.getString("pay_time"));
				JSONObject objz=objs.getJSONObject("reciver_info");
				OrderRecieveInfoBean beansss=new OrderRecieveInfoBean();
				beansss.setAddress(objz.getString("address"));
				beansss.setPhone(objz.getString("phone"));
				bean.setRecieveinfobean(beansss);
				bean.setReciver_name(objs.getString("reciver_name"));
				bean.setReciver_province_id(objs.getString("reciver_province_id"));
				bean.setRefund_status(objs.getString("refund_status"));
				bean.setShipping_code(objs.getString("shipping_code"));
				bean.setShipping_price(objs.getString("shipping_price"));
				bean.setShipping_time(objs.getString("shipping_time"));
				bean.setShipping_mode(objs.getString("shipping_mode"));
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
