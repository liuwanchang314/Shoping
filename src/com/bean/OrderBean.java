package com.bean;
/**
 * {
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
public class OrderBean {
	
	private OrderGoodsBean ordergoods;
	public OrderGoodsBean getOrdergoods() {
		return ordergoods;
	}
	public void setOrdergoods(OrderGoodsBean ordergoods) {
		this.ordergoods = ordergoods;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getPay_sn() {
		return pay_sn;
	}
	public void setPay_sn(String pay_sn) {
		this.pay_sn = pay_sn;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getOrder_price() {
		return order_price;
	}
	public void setOrder_price(String order_price) {
		this.order_price = order_price;
	}
	public String getShipping_price() {
		return shipping_price;
	}
	public void setShipping_price(String shipping_price) {
		this.shipping_price = shipping_price;
	}
	public String getEvaluation_status() {
		return evaluation_status;
	}
	public void setEvaluation_status(String evaluation_status) {
		this.evaluation_status = evaluation_status;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
	public String getLock_status() {
		return lock_status;
	}
	public void setLock_status(String lock_status) {
		this.lock_status = lock_status;
	}
	public String getDelay_time() {
		return delay_time;
	}
	public void setDelay_time(String delay_time) {
		this.delay_time = delay_time;
	}
	public String getShipping_code() {
		return shipping_code;
	}
	public void setShipping_code(String shipping_code) {
		this.shipping_code = shipping_code;
	}
	public String getShipping_time() {
		return shipping_time;
	}
	public void setShipping_time(String shipping_time) {
		this.shipping_time = shipping_time;
	}
	public String getExpress_id() {
		return express_id;
	}
	public void setExpress_id(String express_id) {
		this.express_id = express_id;
	}
	public String getExpress_name() {
		return express_name;
	}
	public void setExpress_name(String express_name) {
		this.express_name = express_name;
	}
	public String getEvaluation_time() {
		return evaluation_time;
	}
	public void setEvaluation_time(String evaluation_time) {
		this.evaluation_time = evaluation_time;
	}
	public String getOrder_message() {
		return order_message;
	}
	public void setOrder_message(String order_message) {
		this.order_message = order_message;
	}
	public String getDeliver_explain() {
		return deliver_explain;
	}
	public void setDeliver_explain(String deliver_explain) {
		this.deliver_explain = deliver_explain;
	}
	public String getDaddress_id() {
		return daddress_id;
	}
	public void setDaddress_id(String daddress_id) {
		this.daddress_id = daddress_id;
	}
	public String getReciver_name() {
		return reciver_name;
	}
	public void setReciver_name(String reciver_name) {
		this.reciver_name = reciver_name;
	}
	public OrderRecieveInfoBean getRecieveinfobean() {
		return recieveinfobean;
	}
	public void setRecieveinfobean(OrderRecieveInfoBean recieveinfobean) {
		this.recieveinfobean = recieveinfobean;
	}
	public String getReciver_province_id() {
		return reciver_province_id;
	}
	public void setReciver_province_id(String reciver_province_id) {
		this.reciver_province_id = reciver_province_id;
	}
	private String order_id;
	private String order_sn;
	private String pay_sn;
	private String store_name;
	private String buyer_email;
	private String order_time;
	private String end_time;
	private String goods_price;
	private String order_price;
	private String shipping_price;
	private String evaluation_status;
	private String order_status;
	private String refund_status;
	private String lock_status;
	private String delay_time;
	private String shipping_code;
	private String shipping_time;
	private String express_id;
	private String express_name;
	private String evaluation_time;
	private String order_message;
	private String deliver_explain;
	private String daddress_id;
	private String reciver_name;
	private OrderRecieveInfoBean recieveinfobean;
	private String reciver_province_id;
	
	private String shipping_mode;
	public String getShipping_mode() {
		return shipping_mode;
	}
	public void setShipping_mode(String shipping_mode) {
		this.shipping_mode = shipping_mode;
	}
	
	
	

}
