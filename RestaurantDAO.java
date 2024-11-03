package com.tap.dao;

import java.util.List;

import com.tap.model.Restaurant;


public interface RestaurantDAO {
	int insertRestaurant(Restaurant restaurant);
	List<Restaurant> getAllRestaurants();
	Restaurant getRestaurantById(int id);
	int deleteRestaurantById(int id);
	int updateRestaurantById(int id, String cuisineType);
}
