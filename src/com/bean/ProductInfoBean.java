package com.bean;

import java.util.List;

import com.bean.PIspec;

/**
 * {
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
}
}
 * */
public class ProductInfoBean {
	
	private String store_id;
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_price_interval() {
		return goods_price_interval;
	}
	public void setGoods_price_interval(String goods_price_interval) {
		this.goods_price_interval = goods_price_interval;
	}
	public String getGoods_status() {
		return goods_status;
	}
	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}
	public String getGoods_content() {
		return goods_content;
	}
	public void setGoods_content(String goods_content) {
		this.goods_content = goods_content;
	}
	public String getGoods_image() {
		return goods_image;
	}
	public void setGoods_image(String goods_image) {
		this.goods_image = goods_image;
	}
	public String getGoods_image_thumb() {
		return goods_image_thumb;
	}
	public void setGoods_image_thumb(String goods_image_thumb) {
		this.goods_image_thumb = goods_image_thumb;
	}
	public String getSale_num() {
		return sale_num;
	}
	public void setSale_num(String sale_num) {
		this.sale_num = sale_num;
	}
	public String getCollect_num() {
		return collect_num;
	}
	public void setCollect_num(String collect_num) {
		this.collect_num = collect_num;
	}
	private String store_name;
	private String goods_name;
	private String class_id;
	private String class_name;
	private String goods_price;
	private String goods_price_interval;
	private String goods_status;
	private List<PIspec> list;
	public List<PIspec> getList() {
		return list;
	}
	public void setList(List<PIspec> list) {
		this.list = list;
	}
	private String goods_content;
	private String goods_image;
	private String goods_image_thumb;
	private String sale_num;
	private String collect_num;

}
