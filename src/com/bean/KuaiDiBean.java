package com.bean;

/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "transport_id": "3",
            "express_title": "��ͨ���",
            "express_id": null,
            "express_note": ""
        },
        {
            "transport_id": "4",
            "express_title": "TNT���",
            "express_id": null,
            "express_note": ""
        }
    ],
    "status": 1
}
 * */
public class KuaiDiBean {

	private String transport_id;
	private String express_title;
	private String express_id;
	private String express_note;
	private boolean ischecked=false;
	public boolean isIschecked() {
		return ischecked;
	}
	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}
	public String getTransport_id() {
		return transport_id;
	}
	public void setTransport_id(String transport_id) {
		this.transport_id = transport_id;
	}
	public String getExpress_title() {
		return express_title;
	}
	public void setExpress_title(String express_title) {
		this.express_title = express_title;
	}
	public String getExpress_id() {
		return express_id;
	}
	public void setExpress_id(String express_id) {
		this.express_id = express_id;
	}
	public String getExpress_note() {
		return express_note;
	}
	public void setExpress_note(String express_note) {
		this.express_note = express_note;
	}
	
	
}
