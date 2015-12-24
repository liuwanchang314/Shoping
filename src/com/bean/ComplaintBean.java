package com.bean;
/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "0": "32",
            "1": "1474",
            "2": "7341",
            "3": "apitestuser",
            "4": "428",
            "5": "jiafangyan",
            "6": "商家不同意退款 ",
            "7": "1",
            "8": "12123发",
            "9": "nabin/upload/7341/complain_pic11450771629.jpg",
            "10": "nabin/upload/7341/complain_pic21450771629.jpg",
            "11": "nabin/upload/7341/complain_pic31450771629.jpg",
            "12": "1450771629",
            "13": "0",
            "14": "0",
            "15": "",
            "16": "0",
            "17": "",
            "18": "",
            "19": "",
            "20": "",
            "21": "0",
            "22": "0",
            "23": "10",
            "24": "1",
            "25": "220504115629967341",
            "complain_id": "32",
            "order_id": "1474",
            "accuser_id": "7341",
            "accuser_name": "apitestuser",
            "accused_id": "428",
            "accused_name": "jiafangyan",
            "complain_subject_content": "商家不同意退款 ",
            "complain_subject_id": "1",
            "complain_content": "12123发",
            "complain_pic1": "nabin/upload/7341/complain_pic11450771629.jpg",
            "complain_pic2": "nabin/upload/7341/complain_pic21450771629.jpg",
            "complain_pic3": "nabin/upload/7341/complain_pic31450771629.jpg",
            "complain_datetime": "1450771629",
            "complain_handle_datetime": "0",
            "complain_handle_member_id": "0",
            "appeal_message": "",
            "appeal_datetime": "0",
            "appeal_pic1": "",
            "appeal_pic2": "",
            "appeal_pic3": "",
            "final_handle_message": "",
            "final_handle_datetime": "0",
            "final_handle_member_id": "0",
            "complain_state": "10",
            "complain_active": "1",
            "complain_sn": "220504115629967341"
        }
    ],
    "status": 1
}
 * */
public class ComplaintBean {

	private String complain_id;
	public String getComplain_id() {
		return complain_id;
	}
	public void setComplain_id(String complain_id) {
		this.complain_id = complain_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getAccuser_id() {
		return accuser_id;
	}
	public void setAccuser_id(String accuser_id) {
		this.accuser_id = accuser_id;
	}
	public String getAccuser_name() {
		return accuser_name;
	}
	public void setAccuser_name(String accuser_name) {
		this.accuser_name = accuser_name;
	}
	public String getAccused_id() {
		return accused_id;
	}
	public void setAccused_id(String accused_id) {
		this.accused_id = accused_id;
	}
	public String getAccused_name() {
		return accused_name;
	}
	public void setAccused_name(String accused_name) {
		this.accused_name = accused_name;
	}
	public String getComplain_subject_content() {
		return complain_subject_content;
	}
	public void setComplain_subject_content(String complain_subject_content) {
		this.complain_subject_content = complain_subject_content;
	}
	public String getComplain_subject_id() {
		return complain_subject_id;
	}
	public void setComplain_subject_id(String complain_subject_id) {
		this.complain_subject_id = complain_subject_id;
	}
	public String getComplain_content() {
		return complain_content;
	}
	public void setComplain_content(String complain_content) {
		this.complain_content = complain_content;
	}
	public String getComplain_pic1() {
		return complain_pic1;
	}
	public void setComplain_pic1(String complain_pic1) {
		this.complain_pic1 = complain_pic1;
	}
	public String getComplain_pic2() {
		return complain_pic2;
	}
	public void setComplain_pic2(String complain_pic2) {
		this.complain_pic2 = complain_pic2;
	}
	public String getComplain_pic3() {
		return complain_pic3;
	}
	public void setComplain_pic3(String complain_pic3) {
		this.complain_pic3 = complain_pic3;
	}
	public String getComplain_datetime() {
		return complain_datetime;
	}
	public void setComplain_datetime(String complain_datetime) {
		this.complain_datetime = complain_datetime;
	}
	public String getComplain_handle_datetime() {
		return complain_handle_datetime;
	}
	public void setComplain_handle_datetime(String complain_handle_datetime) {
		this.complain_handle_datetime = complain_handle_datetime;
	}
	public String getComplain_handle_member_id() {
		return complain_handle_member_id;
	}
	public void setComplain_handle_member_id(String complain_handle_member_id) {
		this.complain_handle_member_id = complain_handle_member_id;
	}
	public String getAppeal_message() {
		return appeal_message;
	}
	public void setAppeal_message(String appeal_message) {
		this.appeal_message = appeal_message;
	}
	public String getAppeal_datetime() {
		return appeal_datetime;
	}
	public void setAppeal_datetime(String appeal_datetime) {
		this.appeal_datetime = appeal_datetime;
	}
	public String getAppeal_pic1() {
		return appeal_pic1;
	}
	public void setAppeal_pic1(String appeal_pic1) {
		this.appeal_pic1 = appeal_pic1;
	}
	public String getAppeal_pic2() {
		return appeal_pic2;
	}
	public void setAppeal_pic2(String appeal_pic2) {
		this.appeal_pic2 = appeal_pic2;
	}
	public String getAppeal_pic3() {
		return appeal_pic3;
	}
	public void setAppeal_pic3(String appeal_pic3) {
		this.appeal_pic3 = appeal_pic3;
	}
	public String getFinal_handle_message() {
		return final_handle_message;
	}
	public void setFinal_handle_message(String final_handle_message) {
		this.final_handle_message = final_handle_message;
	}
	public String getFinal_handle_datetime() {
		return final_handle_datetime;
	}
	public void setFinal_handle_datetime(String final_handle_datetime) {
		this.final_handle_datetime = final_handle_datetime;
	}
	public String getFinal_handle_member_id() {
		return final_handle_member_id;
	}
	public void setFinal_handle_member_id(String final_handle_member_id) {
		this.final_handle_member_id = final_handle_member_id;
	}
	public String getComplain_state() {
		return complain_state;
	}
	public void setComplain_state(String complain_state) {
		this.complain_state = complain_state;
	}
	public String getComplain_active() {
		return complain_active;
	}
	public void setComplain_active(String complain_active) {
		this.complain_active = complain_active;
	}
	public String getComplain_sn() {
		return complain_sn;
	}
	public void setComplain_sn(String complain_sn) {
		this.complain_sn = complain_sn;
	}
	private String order_id;
	private String accuser_id;
	private String accuser_name;
	private String accused_id;
	private String accused_name;
	private String complain_subject_content;
	private String complain_subject_id;
	private String complain_content;
	private String complain_pic1;
	private String complain_pic2;
	private String complain_pic3;
	private String complain_datetime;
	private String complain_handle_datetime;
	private String complain_handle_member_id;
	private String appeal_message;
	private String appeal_datetime;
	private String appeal_pic1;
	private String appeal_pic2;
	private String appeal_pic3;
	private String final_handle_message;
	private String final_handle_datetime;
	private String final_handle_member_id;
	private String complain_state;
	private String complain_active;
	private String complain_sn;
	
	
	
}
