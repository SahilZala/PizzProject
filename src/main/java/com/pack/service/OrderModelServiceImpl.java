package com.pack.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.exception.OrderNotFoundException;
import com.pack.model.OrderModel;
import com.pack.model.UserModel;
import com.pack.repository.OrderModelRepository;

@Service
public class OrderModelServiceImpl implements OrderModelService {

	@Autowired
	OrderModelRepository orderModelRepository;
	
	@Override
	public List<OrderModel> getAllOrders() {
		List<OrderModel> data = orderModelRepository.findAll();
		
		if(data.isEmpty())
			throw new OrderNotFoundException("order not found exception!");
		
		return data;
	}

	@Override
	public List<OrderModel> getAllOrdersByUserId(UserModel user) {
		List<OrderModel> data = orderModelRepository.findByUser(user);
		
		if(data.isEmpty())
			throw new OrderNotFoundException("order not found exception!");
		
		return data;
	}

	@Override
	public OrderModel updateOrderModel(OrderModel orderModel) {
		return orderModelRepository.save(orderModel);
	}

	
}
