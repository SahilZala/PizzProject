package com.pack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.model.StatusModel;
import com.pack.util.PathsClass;

@RestController
@RequestMapping(PathsClass.ADMIN_PATH)
public class AdminHome {
	
	@PostMapping("/home")
	@CrossOrigin(origins = "*")
	public ResponseEntity<StatusModel> adminHome(){
		return new ResponseEntity<>(new StatusModel("",HttpStatus.ACCEPTED,"","","admin"),HttpStatus.ACCEPTED);
	}
}
