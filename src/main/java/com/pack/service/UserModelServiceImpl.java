package com.pack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pack.model.UserModel;
import com.pack.repository.UserModelRepository;

@Service
public class UserModelServiceImpl implements UserModelService{

	@Autowired
	UserModelRepository userModelRepository;
	
	@Override
	public UserModel getUserData(String userName, String password) {
		
		Optional<UserModel> userModel = userModelRepository.findByUserNameAndPassword(userName, password);
		
		
		
		if(!userModel.isPresent())
		{
			throw new UsernameNotFoundException("no user data found");
		}
		
		return userModel.get();
	}

	@Override
	public UserModel getUserData(String userName) {
		Optional<UserModel> userModel = userModelRepository.findByUserName(userName);
		if(!userModel.isPresent()) {
			throw new UsernameNotFoundException("no user data found!");
		}
		return userModel.get();
	}

	@Override
	public UserModel updateUserData(UserModel userModel) {
		
		return userModelRepository.save(userModel);
	}
	
	
	
}
;