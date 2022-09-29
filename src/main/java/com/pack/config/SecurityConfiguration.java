package com.pack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

import com.pack.util.PathsClass;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		
		
		http
		.cors()
		.and()
		.csrf()
		.disable()
//		.headers()
//		.frameOptions()
//		.disable()
		//.and()
		.authorizeRequests()
		.antMatchers(PathsClass.LOGIN_PATH)
		.permitAll()
//		.antMatchers(PathsClass.CUSTOMER_PATH+PathsClass.GET_USER_DATA_PATH)
//		.permitAll()
		.antMatchers("/")
		.hasAnyRole("USER","ADMIN")
		.antMatchers(PathsClass.GET_TOPING_DATA_PATH)
		.hasAnyRole("USER","ADMIN")
		.antMatchers(PathsClass.CUSTOMER_PATH+"/**")
		.hasRole("USER")
		.antMatchers(PathsClass.ADMIN_PATH+"/**")
		.hasRole("ADMIN")
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Bean
	AuthenticationManager authenticationMangerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
