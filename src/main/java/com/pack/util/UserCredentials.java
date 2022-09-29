package com.pack.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserCredentials {
	
	static final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	public static UserDetails getUserDetails() {
		return (UserDetails)auth.getPrincipal();
	}
	
	private UserCredentials() {
		
	}

}
