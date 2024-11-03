package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;


public class RestaurantDAOImpl implements RestaurantDAO{
	static Connection con;
	ArrayList<Restaurant> resList = new ArrayList<Restaurant>();
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private Restaurant restaurant;
	private int x;
	
	private static final String GET_ALL_RESTAURANTS = "select * from restaurant";
	private static final String INSERT_RESTAURANT = "insert into restaurant(restaurantId, restaurantName, cuisineType, isActive, ratings) values(?, ?, ?, ?, ?)";
	private static final String GET_RESTAURANT_BYID = "select * from restaurant where restaurantId = ?";
	private static final String DELETE_RESTAURANT_BYID = "delete from restaurant where restaurantId = ?";
	private static final String UPDATE_RESTAURANT_BYID = "update restaurant set cuisineType = ? where restaurantId = ?";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tap_foods", "root", "Fiza@9353");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int insertRestaurant(Restaurant res) {
		int result = -1;
		try {
			pstmt = con.prepareStatement(INSERT_RESTAURANT);
			pstmt.setInt(1, res.getRestaurantId());
			pstmt.setString(2, res.getRestaurantName());
			pstmt.setString(3, res.getCuisineType());
			pstmt.setBoolean(4, true);
			pstmt.setFloat(5, res.getRatings());
			
			result = pstmt.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Override
	public List<Restaurant> getAllRestaurants() {
		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(GET_ALL_RESTAURANTS);
			
			resList = (ArrayList<Restaurant>) extractRestaurantListFromResultSet(resultSet);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return resList;
	}
	
	
	@Override
	public Restaurant getRestaurantById(int id) {
		try {
			pstmt = con.prepareStatement(GET_RESTAURANT_BYID);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			resList = (ArrayList<Restaurant>) extractRestaurantListFromResultSet(resultSet);
			restaurant = resList.get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return restaurant;
	}
	
	@Override
	public int deleteRestaurantById(int id) {
		try {
			pstmt = con.prepareStatement(DELETE_RESTAURANT_BYID);
			pstmt.setInt(1, id);
			x = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	
	@Override
	public int updateRestaurantById(int id, String cuisineType) {
		try {
			pstmt = con.prepareStatement(UPDATE_RESTAURANT_BYID);
			pstmt.setInt(2, id);
			pstmt.setString(1, cuisineType);
			x = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	
	
	List<Restaurant> extractRestaurantListFromResultSet(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				resList.add(new Restaurant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), 
				                      resultSet.getBoolean(4), resultSet.getInt(5)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return resList;
	}
}
