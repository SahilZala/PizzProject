package com.pack.controller;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pack.model.StatusModel;
import com.pack.model.UserModel;
import com.pack.service.UserModelService;
import com.pack.util.PathsClass;
import com.pack.util.UserCredentials;


@RestController
@CrossOrigin
public class GetUserDataController {
	
	static Logger logger = Logger.getLogger(GetUserDataController.class.getName());  
	
	@Autowired
	UserModelService userModelService;
	
	@Autowired
	UserCredentials userCredentials;
	
	@GetMapping(PathsClass.GET_USER_DATA_PATH)
	public ResponseEntity<StatusModel> getUserData(HttpServletRequest request,HttpServletResponse response){
		
		logger.info("in getuserData controller.");
		
		UserDetails ud = userCredentials.getUserDetails(request.getHeader("Authorization"));
		
		UserModel userModel = null;
		try {
			userModel = userModelService.getUserData(ud.getUsername(), ud.getPassword());
			
		}
		catch(UsernameNotFoundException ex) {
			logger.warning(ex.getMessage());
			return new ResponseEntity<>(
					new StatusModel(
							LocalDateTime.now().toString(),HttpStatus.FORBIDDEN,
							ex.getMessage(),PathsClass.GET_USER_DATA_PATH,"no data found"),HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(
				new StatusModel(
						LocalDateTime.now().toString(),HttpStatus.OK,
						"no error",PathsClass.GET_USER_DATA_PATH,userModel),HttpStatus.OK);
	}
}
