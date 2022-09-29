package com.pack.service;

import com.pack.model.UserModel;

public interface UserModelService {
	public UserModel getUserData(String userName,String password);
	public UserModel getUserData(String userName);
	public UserModel updateUserData(UserModel userModel);
}
