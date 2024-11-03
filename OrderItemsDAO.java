package com.tap.dao;

import com.tap.model.OrderItems;

public interface OrderItemsDAO 
{
	int insertOrderItems(OrderItems orderItems);
	OrderItems getOrderItemsByOrderId(int orderId);

}
