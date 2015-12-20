package com.wheelview;

import java.util.List;

/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "name": "±±¾©",
            "id": "001001"
        },
        {
            "name": "Ìì½ò",
            "id": "001002"
        },
 * */
public class CityBean {
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String id;

	private List<DirectBean> list;
	public List<DirectBean> getList() {
		return list;
	}
	public void setList(List<DirectBean> list) {
		this.list = list;
	}
}
