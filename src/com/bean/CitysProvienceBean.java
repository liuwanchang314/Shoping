package com.bean;

/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "name": "����",
            "id": "001001"
        },
        {
            "name": "���",
            "id": "001002"
        },
 * */
public class CitysProvienceBean {
	
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

}
