package com.tap.model;

public class OrderHistory {
	private int orderHistoryId;
	private int userId;
	private int orderId;
	private float total;
	private String status;
	
	
	public int getOrderHistoryId() {
		return orderHistoryId;
	}
	public void setOrderHistoryId(int orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public OrderHistory(int orderHistoryId, int userId, int orderId, float total, String status) {
		super();
		this.orderHistoryId = orderHistoryId;
		this.userId = userId;
		this.orderId = orderId;
		this.total = total;
		this.status = status;
	}
	
	
	public OrderHistory(int userId, int orderId, float total, String status) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.total = total;
		this.status = status;
	}
	
	
	public OrderHistory() {
		super();
	}
	
	
	@Override
	public String toString() {
		return orderHistoryId + "  " + userId + "  " + orderId
				+ "  " + total + "  " + status ;
	}
	
	
	
}
