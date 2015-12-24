package com.bean;

public class BillBean {
	
	/**
	 * {
    "api": "APISUCCESS",
    "data": [
        {
            "inv_type": "1",
            "inv_title": "个人",
            "inv_content": "装修材料",
            "company_name": "",
            "inv_id": "48",
            "default": "0"
        },
        {
            "inv_type": "1",
            "inv_title": "单位",
            "inv_content": "装修材料",
            "company_name": "不知道",
            "inv_id": "50",
            "default": "0"
        },
        {
            "inv_type": "2",
            "inv_title": "",
            "inv_content": "",
            "company_name": "呵呵",
            "inv_id": "52",
            "default": "1"
        }
    ],
    "status": 1
}
	 * */
	private String inv_type;
	public String getInv_type() {
		return inv_type;
	}
	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
	}
	public String getInv_title() {
		return inv_title;
	}
	public void setInv_title(String inv_title) {
		this.inv_title = inv_title;
	}
	public String getInv_content() {
		return inv_content;
	}
	public void setInv_content(String inv_content) {
		this.inv_content = inv_content;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getInv_id() {
		return inv_id;
	}
	public void setInv_id(String inv_id) {
		this.inv_id = inv_id;
	}
	public String getDefaults() {
		return defaults;
	}
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}
	private String inv_title;
	private String inv_content;
	private String company_name;
	private String inv_id;
	private String defaults;

}
