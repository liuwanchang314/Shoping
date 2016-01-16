package com.Model;

public class UserInfo {

	private static UserInfo  instance=null;
	private UserInfo(){
		
	}
	public static UserInfo getInstance(){
		if(instance == null){
			instance = new UserInfo();
		}
		
		return instance;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	private String Name="user";
	private String PassWord=new String();

}
