package com.pack.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.model.StatusModel;
import com.pack.service.OrderModelService;
import com.pack.util.PathsClass;

@RestController
@CrossOrigin
@RequestMapping(PathsClass.ADMIN_PATH)
public class GetOrderDataCount {
	
	@Autowired
	OrderModelService orderModelService;
	
	@RequestMapping(PathsClass.GET_ORDER_DATA_COUNT)
	public ResponseEntity<StatusModel> getTotalOrderCount(){
		try {
			return new ResponseEntity<>(new StatusModel(
					LocalDateTime.now().toString(),
					HttpStatus.OK,
					"no error",
					PathsClass.ADMIN_PATH+PathsClass.GET_ORDER_DATA_COUNT,
					orderModelService.getOrderDataCount()),HttpStatus.OK);
		}
		catch(RuntimeException ex) {
			return new ResponseEntity<>(new StatusModel(LocalDateTime.now().toString(),HttpStatus.BAD_REQUEST,ex.getMessage(),PathsClass.ADMIN_PATH+PathsClass.GET_ORDER_DATA_COUNT,"no data"),HttpStatus.BAD_REQUEST);
		}
	}
}

