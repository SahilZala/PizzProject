package com.pack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pack.model.UserModel;
import com.pack.repository.UserModelRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserModelRepository userModelRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Optional<UserModel> userModel = userModelRepository.findByUserName(username);
		
		if(!userModel.isPresent())
			throw new UsernameNotFoundException("user not found exception");
	
		return userModel.map(UserDetailsImpl::new).get();
	}
	
	
	
}
