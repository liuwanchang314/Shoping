package com.bean;
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-10 下午3:20:20 
 * 类说明 
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "subject_id": "1",
            "subject_content": "商家不同意退款 ",
            "subject_desc": "买家申请退款被拒绝。",
            "subject_state": "1"
        },
        {
            "subject_id": "2",
            "subject_content": "未收到货 ",
            "subject_desc": "交易成功，未收到货，钱已经付给商家，可进行维权。",
            "subject_state": "1"
        },
        {
            "subject_id": "3",
            "subject_content": "售后保障服务 ",
            "subject_desc": "交易完成后30天内，在使用商品过程中，发现商品有质量问题或无法正常使用，可进行维权。",
            "subject_state": "1"
        }
    ],
    "status": 1
}
 */
public class SubjectBean {
	
	private String subject_id;
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	public String getSubject_content() {
		return subject_content;
	}
	public void setSubject_content(String subject_content) {
		this.subject_content = subject_content;
	}
	public String getSubject_desc() {
		return subject_desc;
	}
	public void setSubject_desc(String subject_desc) {
		this.subject_desc = subject_desc;
	}
	public String getSubject_state() {
		return subject_state;
	}
	public void setSubject_state(String subject_state) {
		this.subject_state = subject_state;
	}
	private String subject_content;
	private String subject_desc;
	private String subject_state;

}
