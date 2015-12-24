
package com.bean;

import java.io.Serializable;

/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "store_name": "jiafangyan",
            "cart_id": "3935",
            "goods_id": "4400",
            "goods_name": "�������������� ����ɫ,1.8m��6Ӣ�ߣ���",
            "goods_price": "300.00",
            "goods_num": "1",
            "goods_image": "http://www.91jf.com/attachment/goods/428/20151015015628876.jpg_60_60.jpg",
            "spec_id": "42713"
        },

 * */
public class BuyCartBean implements Serializable{
	
	private String store_name;
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getCart_id() {
		return cart_id;
	}
	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
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
	public String getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(String spec_id) {
		this.spec_id = spec_id;
	}
	private String cart_id;
	private String goods_id;
	private String goods_name;
	private String goods_price;
	private String goods_num;
	private String goods_image;
	private String spec_id;
	private boolean isChoosed;		//��Ʒ�Ƿ��ڹ��ﳵ�б�ѡ��
	
	public boolean isChoosed() {
		return isChoosed;
	}
	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}
}
