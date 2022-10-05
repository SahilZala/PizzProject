package com.pack.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(PathsClass.CUSTOMER_PATH)
public class CustomerPlaceOrder {
	
	@Autowired
	OrderModelService orderModelService;
	
	@Autowired
	UserModelService userModelService;
	
	
	@RequestMapping(PathsClass.PLACE_ORDER)
	public ResponseEntity<StatusModel> placeOrder(HttpServletRequest request,@Valid @RequestBody OrderModel orderModel) {
		
		try {
			UserModel userModel = userModelService.updateUserData(orderModel.getUser());
			orderModel.setUser(userModel);
			return new ResponseEntity<>(new StatusModel(
				LocalDateTime.now().toString(),
				HttpStatus.OK,
				"no error",
				PathsClass.ADMIN_PATH+PathsClass.UPDATE_ADMIN_ORDER_DATA_PATH,
				orderModelService.placeOrderModel(orderModel)),HttpStatus.OK);
			
		}
		catch(RuntimeException ex) {
			return new ResponseEntity<>(new StatusModel(LocalDateTime.now().toString(),HttpStatus.BAD_REQUEST,ex.getMessage(),PathsClass.ADMIN_PATH+PathsClass.UPDATE_ADMIN_ORDER_DATA_PATH,"no data"),HttpStatus.BAD_REQUEST);
		}
	}
}
