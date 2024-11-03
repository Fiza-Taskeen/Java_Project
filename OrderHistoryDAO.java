package com.tap.dao;


import com.tap.model.OrderHistory;

public interface OrderHistoryDAO {
	int insertOrderHistory(OrderHistory oh);
	int updateOrderHistory(int ohid, String status);
	OrderHistory getOrderHistoryByuserId(int userId);
}
