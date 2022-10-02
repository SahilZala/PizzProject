package com.pack.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import com.pack.config.JWTAuthenticationFilter;

@Service
public class UserCredentials {
	
	Logger log = Logger.getLogger(JWTAuthenticationFilter.class.getName());
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	static final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	public static UserDetails getUserDetails() {
		return (UserDetails)auth.getPrincipal();
	}
	
	public UserDetails getUserDetails(String token) {
		String requestTokenHeader = token;
		
		String userName = null;
		String jwtToken = null;
		UserDetails userDetails = null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
		{
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = jwtUtil.extractUsername(jwtToken);
				
				userDetails = userDetailsService.loadUserByUsername(userName);
				
				if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					
					userNamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource());
					SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthenticationToken);
						
				}
								
			}
			catch(RuntimeException ex) {
				log.log(Level.SEVERE,ex.getMessage());
				
			}
			
		}
		return userDetails;
		
	}
	
	private UserCredentials() {
		
	}

}
