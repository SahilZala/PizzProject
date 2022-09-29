package com.pack.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class UserModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	@Size(min = 2, message="Size of user name should be grater then 2")
	private String userName;
	@NotEmpty
	private String myName;
	@Email
	private String emailId;
	@NotNull
	@NotEmpty
	@Size(min = 4,message="Size of password should be greater then 4")
	private String password;
	private String address;
	@NotNull
	@NotEmpty
	@Size(min = 10,max = 10,message="Invalid mobile number")
	private String mobileNo;
	@NotEmpty
	private String roles;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public UserModel() {
		super();
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", userName=" + userName + ", emailId=" + emailId + ", password=" + password
				+ ", address=" + address + ", mobileNo=" + mobileNo + ", roles=" + roles + "]";
	}
	public UserModel(int id, String userName, String myName, String emailId, String password, String address,
			String mobileNo, String roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.myName = myName;
		this.emailId = emailId;
		this.password = password;
		this.address = address;
		this.mobileNo = mobileNo;
		this.roles = roles;
	}
	public UserModel(int id, String myName, String emailId, String address, String mobileNo) {
		super();
		this.id = id;
		this.myName = myName;
		this.emailId = emailId;
		this.address = address;
		this.mobileNo = mobileNo;
	}
	
	
	
	
	
	
	
	
}
