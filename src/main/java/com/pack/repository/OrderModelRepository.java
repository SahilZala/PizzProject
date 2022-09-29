package com.pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.model.OrderModel;
import com.pack.model.UserModel;

public interface OrderModelRepository extends JpaRepository<OrderModel, Integer> {

	public List<OrderModel> findByUser(UserModel user);

}
