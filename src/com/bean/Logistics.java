package com.bean;

public class Logistics {
	private String maddress;
	private String mtime;
	
	public Logistics(String maddress, String mtime) {
		this.maddress = maddress;
		this.mtime = mtime;
	}

	public String getMaddress() {
		return maddress;
	}

	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
}
