package com.pack.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.exception.NoTopingFoundException;
import com.pack.model.StatusModel;
import com.pack.service.TopingModelService;
import com.pack.util.PathsClass;

@RestController
public class GetTopingDataController {
	
	@Autowired
	TopingModelService topingModelService;
	
	@GetMapping(PathsClass.GET_TOPING_DATA_PATH)
	@CrossOrigin(origins = "*")
	public ResponseEntity<StatusModel> getTopingDataController(){
		try{
			return new ResponseEntity<>(new StatusModel(LocalDateTime.now().toString(),HttpStatus.OK,
					"no error",PathsClass.GET_TOPING_DATA_PATH,topingModelService.getAllTopings()),HttpStatus.OK);
		}
		catch(NoTopingFoundException ex) {
			return new ResponseEntity<>(
					new StatusModel(
							LocalDateTime.now().toString(),HttpStatus.FORBIDDEN,
							ex.getMessage(),PathsClass.GET_TOPING_DATA_PATH,"no data found"),HttpStatus.FORBIDDEN);
		}
	}
}
