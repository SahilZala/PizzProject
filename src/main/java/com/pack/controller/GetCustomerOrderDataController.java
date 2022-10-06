package com.pack.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.model.OrderModel;
import com.pack.model.StatusModel;
import com.pack.model.UserModel;
import com.pack.service.OrderModelService;
import com.pack.service.UserModelService;
import com.pack.util.PathsClass;
import com.pack.util.UserCredentials;

@RestController
@CrossOrigin
@RequestMapping(PathsClass.CUSTOMER_PATH)
public class GetCustomerOrderDataController {
	
	@Autowired
	OrderModelService orderModelService;
	
	@Autowired
	UserModelService userModelService;
	
	@Autowired
	UserCredentials userCredentials;
	
	
	@GetMapping(PathsClass.GET_CUSTOMER_ORDER_DATA_PATH)
	public ResponseEntity<StatusModel> getCustomerOrderDataController(HttpServletRequest request){
		
		UserDetails ud = userCredentials.getUserDetails(request.getHeader("Authorization"));
		
		List<OrderModel> orderModelList = new ArrayList<>();
		try {
			
			orderModelList = orderModelService.getAllOrdersByUserId(
					userModelService.getUserData(ud.getUsername(),ud.getPassword())
			);
			
			return new ResponseEntity<>(
					new StatusModel(
							LocalDateTime.now().toString(),
							HttpStatus.OK,
							"no error",
							PathsClass.CUSTOMER_PATH+PathsClass.GET_CUSTOMER_ORDER_DATA_PATH,
							orderModelList),
					HttpStatus.OK);
		}
		catch(RuntimeException ex) {
			return new ResponseEntity<>(
					new StatusModel(LocalDateTime.now().toString(),
							HttpStatus.BAD_REQUEST,
							ex.getMessage(),
							PathsClass.CUSTOMER_PATH+PathsClass.GET_CUSTOMER_ORDER_DATA_PATH,
							"no data"),HttpStatus.BAD_REQUEST);
		}
	}
}
