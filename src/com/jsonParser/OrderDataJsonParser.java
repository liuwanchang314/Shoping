package com.jsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import com.bean.OrderBean;
import com.bean.OrderGoodsBean;
import com.bean.OrderRecieveInfoBean;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-15 下午12:48:24 
 * 类说明  订单详情数据解析工具类
 * 
 * {
    "api": "APISUCCESS",
    "data": {
        "order_goods": {
            "order_goods_id": "3084",
            "goods_id": "12238",
            "goods_name": "田园四件套",
            "goods_price": "122.00",
            "goods_num": "1",
            "goods_image": "http://www.91jf.com/attachment/goods/428/20150813022205559.jpg_60_60.jpg",
            "goods_pay_price": "122.00",
            "spec_id": "98100"
        },
        "order_sn": "8000000000201201",
        "pay_sn": "690506036022925341",
        "store_name": "jiafangyan",
        "buyer_email": "apitestuser@91jf.com",
        "order_time": "2016-01-13 21:33:42",
        "pay_code": "online",
        "pay_time": "2016-01-14 21:50:36",
        "end_time": "1970-01-01 07:00:00",
        "goods_price": "122.00",
        "order_price": "122.00",
        "pd_price": "0.00",
        "shipping_price": "0.00",
        "evaluation_status": "",
        "order_status": "20",
        "refund_status": "0",
        "lock_status": "0",
        "refund_price": "0.00",
        "delay_time": "1970-01-01 07:00:00",
        "shipping_code": "",
        "poundage_price": "0.00",
        "shipping_mode": "商家发货",
        "shipping_time": "1970-01-01 07:00:00",
        "express_id": "3",
        "express_name": "CCES",
        "evaluation_time": "1970-01-01 07:00:00",
        "evalseller_status": "0",
        "evalseller_time": "1970-01-01 07:00:00",
        "order_message": "",
        "deliver_explain": "",
        "daddress_id": "0",
        "reciver_name": "",
        "reciver_info": {
            "address": "&nbsp;",
            "phone": ""
        },
        "reciver_province_id": "",
        "invoice_info": []
    },
    "status": 1
}
 */
public class OrderDataJsonParser {
	
	public  static OrderBean getbean(String str){
		OrderBean bean=new OrderBean();
		try {
			JSONObject obj=new JSONObject(str);
			JSONObject objs=obj.getJSONObject("data");
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
			return bean;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
		
	}

}
