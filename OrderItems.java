package com.tap.model;

public class OrderItems {
	private int orderItemsId;
	private int orderId;
	private int menuId;
	private int quantity;
	private float subTotal;
	
	
	public int getOrderItemsId() {
		return orderItemsId;
	}
	public void setOrderItemsId(int orderItemsId) {
		this.orderItemsId = orderItemsId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	
	
	public OrderItems(int orderItemsId, int orderId, int menuId, int quantity, float subTotal) {
		super();
		this.orderItemsId = orderItemsId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}
	
	
	public OrderItems(int orderId, int menuId, int quantity, float subTotal) {
		super();
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}
	
	
	public OrderItems() {
		super();
	}
	
	
	@Override
	public String toString() {
		return orderItemsId + "  " + orderId + "  "  + menuId
				+ "  " + quantity + "  " + subTotal ;
	}
	
	
	
}
