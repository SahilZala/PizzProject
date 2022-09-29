package com.pack.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.model.OrderModel;
import com.pack.model.StatusModel;
import com.pack.service.OrderModelService;
import com.pack.service.UserModelService;
import com.pack.util.PathsClass;

@RestController
@RequestMapping(PathsClass.ADMIN_PATH)
public class UpdateOrderDataController {
	
	@Autowired
	OrderModelService orderModelService;
	
	@Autowired
	UserModelService userModelService;
	
	@PutMapping(PathsClass.UPDATE_ADMIN_ORDER_DATA_PATH)
	@CrossOrigin(origins = "*")
	ResponseEntity<StatusModel> updateOrderDataController(@Valid @RequestBody OrderModel orderModel){
		System.out.println(orderModel);
		try {
			return new ResponseEntity<>(new StatusModel(
				LocalDateTime.now().toString(),
				HttpStatus.OK,
				"no error",
				PathsClass.ADMIN_PATH+PathsClass.UPDATE_ADMIN_ORDER_DATA_PATH,
				orderModelService.updateOrderModel(orderModel)),HttpStatus.OK);
		
		}
		catch(RuntimeException ex) {
			return new ResponseEntity<>(new StatusModel(LocalDateTime.now().toString(),HttpStatus.BAD_REQUEST,ex.getMessage(),PathsClass.ADMIN_PATH+PathsClass.UPDATE_ADMIN_ORDER_DATA_PATH,"no data"),HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
