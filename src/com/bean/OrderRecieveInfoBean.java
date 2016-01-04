package com.bean;

public class OrderRecieveInfoBean {
	
	/**
	 * reciver_info": {
                "address": "\u56db\u5ddd \u5df4\u4e2d \u901a\u6c5f&nbsp;\u6d4b\u8bd5\u8857\u9053\u5730\u5740",
                "phone": "13356847878"
            },
	 * */
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String phone;

}
