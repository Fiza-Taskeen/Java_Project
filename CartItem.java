package com.tap.model;

public class CartItem 
{
	private int menuId;
	private int restaurantId;
	private String itemName;
	private int quantity;
	private float price;
	
	//Setters And Getters
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	//Constructor
	public CartItem(int menuId, int restaurantId, String itemName, int quantity, float price) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return menuId + "  "+ restaurantId + "  " + itemName
				+ "   " + quantity + "   " + price ;
	}
}

