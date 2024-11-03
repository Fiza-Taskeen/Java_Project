package com.tap.dao;

import java.util.List;

import com.tap.model.Orders;

public interface OrdersDAO {
	int insertOrders(Orders orders);
	Orders getOrdersById(int id);
	int updateOrders(int id, String status);
	int getOrdersByMaxId();
	List<Orders> getOrdersByUserId(int userId);
}
