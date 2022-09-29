package com.pack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pack.model.UserModel;

public interface UserModelRepository extends JpaRepository<UserModel, Integer>{
	
	public Optional<UserModel> findByUserName(String userName);
	
	@Query("select u from UserModel u where u.userName = ?1 and u.password = ?2")
	public Optional<UserModel> findByUserNameAndPassword(String userName,String password);
	
}
