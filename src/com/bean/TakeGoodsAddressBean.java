package com.bean;
/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "receive_name": "测试",
            "area_info": "四川 巴中 通江测试街道地址",
            "mob_phone": "13356847878",
            "default": "0",
            "address_id": "761",
            "tel_phone": ""
        },
        {
            "receive_name": "第二地址",
            "area_info": "广东 清远 清城不知道哪里的街道",
            "mob_phone": "13812455421",
            "default": "1",
            "address_id": "763",
            "tel_phone": ""
        }
    ],
    "status": 1
}
 * 
 * */
public class TakeGoodsAddressBean {
	
	
	private String receive_name;
	public String getReceive_name() {
		return receive_name;
	}
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}
	public String getArea_info() {
		return area_info;
	}
	public void setArea_info(String area_info) {
		this.area_info = area_info;
	}
	public String getMob_phone() {
		return mob_phone;
	}
	public void setMob_phone(String mob_phone) {
		this.mob_phone = mob_phone;
	}
	public String getDefaults() {
		return defaults;
	}
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}
	public String getAddress_id() {
		return address_id;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	public String getTel_phone() {
		return tel_phone;
	}
	public void setTel_phone(String tel_phone) {
		this.tel_phone = tel_phone;
	}
	private String area_info;
	private String mob_phone;
	private String defaults;
	private String address_id;
	private String tel_phone;

}
