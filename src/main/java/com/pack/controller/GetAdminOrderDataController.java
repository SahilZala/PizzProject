package com.pack.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.model.OrderModel;
import com.pack.model.StatusModel;
import com.pack.service.OrderModelService;
import com.pack.util.PathsClass;

@RestController
@RequestMapping(PathsClass.ADMIN_PATH)
public class GetAdminOrderDataController {
	
	@Autowired
	OrderModelService orderModelService;
	
	@GetMapping(PathsClass.GET_ADMIN_ORDER_DATA_PATH)
	@CrossOrigin(origins = "*")
	public ResponseEntity<StatusModel> getAdminOrderDataController(){
		List<OrderModel> orderModelList = new ArrayList<>();
		try {
			orderModelList = orderModelService.getAllOrders();
			
			return new ResponseEntity<>(
					new StatusModel(
							LocalDateTime.now().toString(),
							HttpStatus.OK,
							"no error",
							PathsClass.CUSTOMER_PATH+PathsClass.GET_ADMIN_ORDER_DATA_PATH,
							orderModelList),
					HttpStatus.OK);
		}
		catch(RuntimeException ex) {
			return new ResponseEntity<>(
					new StatusModel(LocalDateTime.now().toString(),
							HttpStatus.BAD_REQUEST,
							ex.getMessage(),
							PathsClass.CUSTOMER_PATH+PathsClass.GET_ADMIN_ORDER_DATA_PATH,
							"no data"),HttpStatus.BAD_REQUEST);
		}
	}
}
