package com.bean;

import java.util.List;

/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "class_id": "720",
            "class_name": "��ɫ����",
            "class_list": []
        },
        {
            "class_id": "721",
            "class_name": "��԰���",
            "class_list": []
        },
        {
            "class_id": "727",
            "class_name": "�ഺ����",
            "class_list": [
                {
                    "class_id": "745",
                    "class_name": "�ɰ���ͨ"
                },
                {
                    "class_id": "744",
                    "class_name": "���˷���"
                }
            ]
        }
    ],
    "status": 1
}
 * */
public class productclassgroup {
	
	
	private String class_id;
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
	public List<productclasschild> getList() {
		return list;
	}
	public void setList(List<productclasschild> list) {
		this.list = list;
	}
	private String class_name;
	private List<productclasschild> list;
	
	

}
