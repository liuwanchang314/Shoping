package com.bean; 

import java.util.List;

public class PIspec {

	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<PIdimesionAndColor> getList() {
		return list;
	}
	public void setList(List<PIdimesionAndColor> list) {
		this.list = list;
	}
	private List<PIdimesionAndColor> list;
}
