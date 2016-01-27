package com.bean;

import java.io.Serializable;

/**
 * {
    "api": "APISUCCESS",
    "data": {
        "cname": "apitestuser",
        "sex": "��",
        "tel": "",
        "address": "",
        "qq": "",
        "wechat": "",
        "email": "apitestuser@91jf.com",
        "head_img": "",
        "wanwan": null,
        "wx_openid": ""
    },
    "status": 1
}
 * */
public class PersonDataBean implements Serializable{

	private String cname;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHead_img() {
		return head_img;
	}
	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}
	public String getWanwan() {
		return wanwan;
	}
	public void setWanwan(String wanwan) {
		this.wanwan = wanwan;
	}
	public String getWx_openid() {
		return wx_openid;
	}
	public void setWx_openid(String wx_openid) {
		this.wx_openid = wx_openid;
	}
	private String sex;
	private String tel;
	private String address;
	private String qq;
	private String wechat;
	private String email;
	private String head_img;
	private String wanwan;
	private String wx_openid;
	
}
