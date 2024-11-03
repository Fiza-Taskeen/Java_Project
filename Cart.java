package com.tap.model;

import java.util.Map;
import java.util.HashMap;

public class Cart 
{
	//The cart "cart" is stored as a Map of item id's to CartItem objects
	private Map<Integer, CartItem> cart;
	
	public Cart() 
	{
		this.cart = new HashMap<>();

	}
	
	//Add an item to cart
	public void addItem(CartItem item) 
	{
		int menuId = item.getMenuId();
		if(cart.containsKey(menuId)) 
		{
			//If item already exists increase the quantity
			CartItem existingItem = cart.get(menuId);
			existingItem.setQuantity(existingItem.getQuantity()+ item.getQuantity());
		}
		else {
			//If item is new, add to cart
			cart.put(menuId, item);
		}
	}
	
	//Update the quantity of an item in the cart
	public void updateItem(int menuId, int quantity ) 
	{
		if(cart.containsKey(menuId)) {
			if(quantity <= 0) {
				cart.remove(menuId);
			}
			else {
				cart.get(menuId).setQuantity(quantity);
			}
		}
	}
	
	//Remove an item from the cart
	public void removeItem(int menuId) {
		cart.remove(menuId);
		
	}
	
	public Map<Integer, CartItem> getCart(){
		return cart;
	}
	
	public void clearCart() {
		cart.clear();
	}
}
