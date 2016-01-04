package com.bean;

import android.R.string;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-3 下午8:59:25 
 * 类说明  退款与售后的实体类
 * {
    "api": "APISUCCESS",
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
 * 
 */
public class TuikuanSHBean {
	
	private String refund_id;
	public String getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
	public String getRefund_sn() {
		return refund_sn;
	}
	public void setRefund_sn(String refund_sn) {
		this.refund_sn = refund_sn;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getOrder_goods_id() {
		return order_goods_id;
	}
	public void setOrder_goods_id(String order_goods_id) {
		this.order_goods_id = order_goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
	}
	public String getGoods_image() {
		return goods_image;
	}
	public void setGoods_image(String goods_image) {
		this.goods_image = goods_image;
	}
	public String getRefund_price() {
		return refund_price;
	}
	public void setRefund_price(String refund_price) {
		this.refund_price = refund_price;
	}
	public String getRefund_type() {
		return refund_type;
	}
	public void setRefund_type(String refund_type) {
		this.refund_type = refund_type;
	}
	public String getSeller_status() {
		return seller_status;
	}
	public void setSeller_status(String seller_status) {
		this.seller_status = seller_status;
	}
	public String getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
	public String getReturn_type() {
		return return_type;
	}
	public void setReturn_type(String return_type) {
		this.return_type = return_type;
	}
	public String getOrder_lock() {
		return order_lock;
	}
	public void setOrder_lock(String order_lock) {
		this.order_lock = order_lock;
	}
	public String getGoods_status() {
		return goods_status;
	}
	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}
	public String getRefund_time() {
		return refund_time;
	}
	public void setRefund_time(String refund_time) {
		this.refund_time = refund_time;
	}
	public String getSeller_time() {
		return seller_time;
	}
	public void setSeller_time(String seller_time) {
		this.seller_time = seller_time;
	}
	public String getAdmin_time() {
		return admin_time;
	}
	public void setAdmin_time(String admin_time) {
		this.admin_time = admin_time;
	}
	public String getBuyer_message() {
		return buyer_message;
	}
	public void setBuyer_message(String buyer_message) {
		this.buyer_message = buyer_message;
	}
	public String getSeller_message() {
		return seller_message;
	}
	public void setSeller_message(String seller_message) {
		this.seller_message = seller_message;
	}
	public String getExpress_id() {
		return express_id;
	}
	public void setExpress_id(String express_id) {
		this.express_id = express_id;
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
	public String getDelay_time() {
		return delay_time;
	}
	public void setDelay_time(String delay_time) {
		this.delay_time = delay_time;
	}
	public String getReceive_time() {
		return receive_time;
	}
	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}
	public String getReceive_message() {
		return receive_message;
	}
	public void setReceive_message(String receive_message) {
		this.receive_message = receive_message;
	}
	public String getSale_status() {
		return sale_status;
	}
	public void setSale_status(String sale_status) {
		this.sale_status = sale_status;
	}
	public String getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(String spec_id) {
		this.spec_id = spec_id;
	}
	private String refund_sn;
	private String order_sn;
	private String store_name;
	private String goods_id;
	private String order_goods_id;
	private String goods_name;
	private String goods_num;
	private String goods_image;
	private String refund_price;
	private String refund_type;
	private String seller_status;
	private String refund_status;
	private String return_type;
	private String order_lock;
	private String goods_status;
	private String refund_time;
	private String seller_time;
	private String admin_time;
	private String buyer_message;
	private String seller_message;
	private String express_id;
	private String shipping_code;
	private String shipping_time;
	private String delay_time;
	private String receive_time;
	private String receive_message;
	private String sale_status;
	private String spec_id;
}
