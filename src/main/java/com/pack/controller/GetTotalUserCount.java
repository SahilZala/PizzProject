package com.pack.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.model.StatusModel;
import com.pack.service.UserModelService;
import com.pack.util.PathsClass;

@RestController
@CrossOrigin
@RequestMapping(PathsClass.ADMIN_PATH)
public class GetTotalUserCount {
	
	@Autowired
	UserModelService userModelService;
	
	@RequestMapping(PathsClass.GET_USER_DATA_COUNT)
	public ResponseEntity<StatusModel> getTotalUserCount(){
		try {
			return new ResponseEntity<>(new StatusModel(
					LocalDateTime.now().toString(),
					HttpStatus.OK,
					"no error",
					PathsClass.ADMIN_PATH+PathsClass.GET_USER_DATA_COUNT,
					userModelService.getUserDataCount()),HttpStatus.OK);
		}
		catch(RuntimeException ex) {
			return new ResponseEntity<>(new StatusModel(LocalDateTime.now().toString(),HttpStatus.BAD_REQUEST,ex.getMessage(),PathsClass.ADMIN_PATH+PathsClass.GET_USER_DATA_COUNT,"no data"),HttpStatus.BAD_REQUEST);
		}
	}
}
