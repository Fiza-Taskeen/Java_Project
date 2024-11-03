package com.tap.model;

public class Orders {
	private int ordersId;
	private int userId;
	private int restaurantId;
	private float totalAmount;
	private String status;
	private String paymentOption;
	 private String restaurantName;
	
	
	public int getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
	

	
	
	public Orders(int ordersId, int userId, int restaurantId, float totalAmount, String status, String paymentOption,
			String restaurantName) {
		super();
		this.ordersId = ordersId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentOption = paymentOption;
		this.restaurantName = restaurantName;
		
	}
	
	
	public Orders(int userId, int restaurantId, float totalAmount, String status, String paymentOption,
			String restaurantName) {
		super();
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentOption = paymentOption;
		this.restaurantName = restaurantName;
	
	}
	
	
	public Orders() {
		super();
	}
	
	
	
	@Override
	public String toString() {
		return ordersId + "  "  + userId + "  " + restaurantId
				+ "  " + totalAmount + "  " + status + "  "  + paymentOption;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
