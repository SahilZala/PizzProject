package com.pack.controller;

import java.time.LocalDateTime;
import java.util.logging.Logger;

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
@RequestMapping(PathsClass.CUSTOMER_PATH)
public class GetUserDataController {
	
	static Logger logger = Logger.getLogger(GetUserDataController.class.getName());  
	
	@Autowired
	UserModelService userModelService;
	
	@GetMapping(PathsClass.GET_USER_DATA_PATH)
	public ResponseEntity<StatusModel> getUserData(){
		logger.info("in getuserData controller.");
		UserDetails ud = UserCredentials.getUserDetails();
		UserModel userModel = null;
		try {
			userModel = userModelService.getUserData(ud.getUsername(), ud.getPassword());
			
		}
		catch(UsernameNotFoundException ex) {
			logger.warning(ex.getMessage());
			return new ResponseEntity<>(
					new StatusModel(
							LocalDateTime.now().toString(),HttpStatus.FORBIDDEN,
							ex.getMessage(),PathsClass.CUSTOMER_PATH+PathsClass.GET_USER_DATA_PATH,"no data found"),HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(
				new StatusModel(
						LocalDateTime.now().toString(),HttpStatus.OK,
						"no error",PathsClass.CUSTOMER_PATH+PathsClass.GET_USER_DATA_PATH,userModel),HttpStatus.OK);
	}
}
