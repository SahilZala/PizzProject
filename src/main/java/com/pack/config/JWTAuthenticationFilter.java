package com.pack.config;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pack.util.JWTUtil;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    static Logger log = Logger.getLogger(JWTAuthenticationFilter.class.getName());
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JWTUtil jwtUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		log.info("in filter");
		String requestTokenHeader = request.getHeader("Authorization");
		
		String userName = null;
		String jwtToken = null;
		
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
		{
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = jwtUtil.extractUsername(jwtToken);
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
				
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
		
		
		response.setHeader(userName, jwtToken);
		filterChain.doFilter(request, response);
		
	}

}
