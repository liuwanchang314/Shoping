package com.bean;

public class OrderGoodsBean {
	
	/**
	 *  "order_goods": {
                "order_goods_id": "2352",
                "goods_id": "4400",
                "goods_name": "\u4ec5\u4f9b\u6d4b\u8bd5\u8bf7\u52ff\u8d2d\u4e70 \u5929\u84dd\u8272,2.0m\uff086.6\u82f1\u5c3a\uff09\u5e8a",
                "goods_price": "300.00",
                "goods_num": "1",
                "goods_image": "http:\/\/www.91jf.com\/attachment\/goods\/428\/20151015015628876.jpg_60_60.jpg",
                "goods_pay_price": "300.00",
                "spec_id": "42714"
            },
	 * */
	
	private String order_goods_id;
	public String getOrder_goods_id() {
		return order_goods_id;
	}
	public void setOrder_goods_id(String order_goods_id) {
		this.order_goods_id = order_goods_id;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
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
	public String getGoods_pay_price() {
		return goods_pay_price;
	}
	public void setGoods_pay_price(String goods_pay_price) {
		this.goods_pay_price = goods_pay_price;
	}
	public String getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(String spec_id) {
		this.spec_id = spec_id;
	}
	private String goods_id;
	private String goods_name;
	private String goods_price;
	private String goods_num;
	private String goods_image;
	private String goods_pay_price;
	private String spec_id;
	

	

}
