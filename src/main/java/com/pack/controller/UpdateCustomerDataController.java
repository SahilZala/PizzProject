package com.pack.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.model.StatusModel;
import com.pack.model.UserModel;
import com.pack.service.UserModelService;
import com.pack.util.PathsClass;
import com.pack.util.UserCredentials;

@RestController
@RequestMapping(PathsClass.CUSTOMER_PATH)
public class UpdateCustomerDataController {
	
	@Autowired
	UserModelService userModelService;
	
	@PutMapping(PathsClass.UPDATE_CUSTOMER_DATA_PATH)
	@CrossOrigin(origins = "*")
	public ResponseEntity<StatusModel> updateCustomerDataController(@Valid @RequestBody UserModel userModel){
		
		try {
			UserDetails ud = UserCredentials.getUserDetails();
			userModel.setUserName(ud.getUsername());
			userModel.setPassword(ud.getPassword());
			userModel.setRoles(ud.getAuthorities()
					.stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(","))
					);
			return new ResponseEntity<>(new StatusModel(LocalDateTime.now().toString(),HttpStatus.OK,"",PathsClass.CUSTOMER_PATH+PathsClass.UPDATE_CUSTOMER_DATA_PATH,userModelService.updateUserData(userModel)),HttpStatus.OK);
		}
		catch(RuntimeException ex) {
			return new ResponseEntity<>(new StatusModel(LocalDateTime.now().toString(),HttpStatus.FORBIDDEN,ex.getMessage(),PathsClass.CUSTOMER_PATH+PathsClass.UPDATE_CUSTOMER_DATA_PATH,"no data"),HttpStatus.FORBIDDEN);
		}
	}
	
}
