package com.tap.model;

public class Restaurant {
	private int restaurantId;
	private int ratings;
	private String restaurantName;
	private String cuisineType;
	private boolean isActive;
	
	
	
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getCuisineType() {
		return cuisineType;
	}
	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	public Restaurant(int restaurantId,  String restaurantName, String cuisineType,
			boolean isActive, float ratings) {
		super();
		this.restaurantId = restaurantId;
		this.ratings = (int) ratings;
		this.restaurantName = restaurantName;
		this.cuisineType = cuisineType;
		this.isActive = isActive;
	}
	

	
	
	
	public Restaurant() {
		super();
	}
	
	
	
	@Override
	public String toString() {
		return restaurantId + "  "   + restaurantName
				+ "  "  + cuisineType + "  " + isActive + "  " + ratings;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
