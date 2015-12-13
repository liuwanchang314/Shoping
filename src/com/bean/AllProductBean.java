package com.bean;

public class AllProductBean {
	
	/**
	 *  "goods_id": "4400",
            "goods_name": null,
            "goods_image": "/attachment/goods/428/20151015015628876.jpg_250_250.jpg",
            "goods_price": "0.01"
	 * */
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	private String name;
	private String image;
	private String price;

}
