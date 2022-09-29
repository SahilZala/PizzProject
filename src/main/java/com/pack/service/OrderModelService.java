package com.pack.service;

import java.util.List;

import com.pack.model.OrderModel;
import com.pack.model.UserModel;

public interface OrderModelService {
	List<OrderModel> getAllOrders();
	List<OrderModel> getAllOrdersByUserId(UserModel id);
	OrderModel updateOrderModel(OrderModel orderModel);
}
