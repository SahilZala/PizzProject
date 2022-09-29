package com.pack.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.model.UserModel;

@RestController
public class IndexController {
	
	@GetMapping("/")
	@CrossOrigin(origins = "*")
	public ResponseEntity<UserModel> index() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails ud = (UserDetails)auth.getPrincipal();	
		System.out.println(ud.getUsername()+" "+ud.getPassword()+" "+ud.getAuthorities());
		
		return new ResponseEntity<>(new UserModel(),HttpStatus.OK);
	}
}
