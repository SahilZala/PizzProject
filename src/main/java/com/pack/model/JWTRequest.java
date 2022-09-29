package com.pack.model;

public class JWTRequest {
	String userName;
	String password;

	public JWTRequest() {
		super();
	}
	public JWTRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "JWTRequest [userName=" + userName + ", password=" + password + "]";
	}
	
	
}
