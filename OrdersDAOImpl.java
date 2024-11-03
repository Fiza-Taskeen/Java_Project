package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.OrdersDAO;
import com.tap.model.Orders;

public class OrdersDAOImpl implements OrdersDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tap_foods";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Fiza@9353";

    private static final String INSERT_ORDERS = "INSERT INTO orders(ordersId, userId, restaurantId, totalAmount, status, paymentOption) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String GET_ORDERS_BYID = "SELECT * FROM orders WHERE ordersId = ?";
    private static final String UPDATE_ORDERS_BYID = "UPDATE orders SET status = ? WHERE ordersId = ?";
    private static final String GET_ORDERS_BY_MAXID = "SELECT MAX(ordersId) FROM orders";
    private static final String GET_ORDERS_BY_USERID = "SELECT * FROM orders WHERE userId = ?";
    private static final String GET_RESTAURANT_NAME_BY_ID = "SELECT restaurantName FROM restaurant WHERE restaurantId = ?";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

	private Connection con;

    @Override
    public int insertOrders(Orders order) {
        int result = -1;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(INSERT_ORDERS)) {

            pstmt.setInt(1, order.getOrdersId());
            pstmt.setInt(2, order.getUserId());
            pstmt.setInt(3, order.getRestaurantId());
            pstmt.setFloat(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getPaymentOption());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Orders getOrdersById(int id) {
        Orders order = null;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(GET_ORDERS_BYID)) {

            pstmt.setInt(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    order = new Orders(
                        resultSet.getInt("ordersId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getInt("totalAmount"),
                        resultSet.getString("status"),
                        resultSet.getString("paymentOption"),
                        resultSet.getString("restaurantName")
                    );
                    // Fetch and set restaurant name
                    String restaurantName = getRestaurantNameById(order.getRestaurantId(), con);
                    order.setRestaurantName(restaurantName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    private String getRestaurantNameById(int restaurantId, Connection con) {
        String restaurantName = null;
        try (PreparedStatement stmt = con.prepareStatement(GET_RESTAURANT_NAME_BY_ID)) {
            stmt.setInt(1, restaurantId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    restaurantName = rs.getString("restaurantName");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantName;
    }

    @Override
    public int updateOrders(int id, String status) {
        int result = -1;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(UPDATE_ORDERS_BYID)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, id);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getOrdersByMaxId() {
        int maxOrderId = -1;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(GET_ORDERS_BY_MAXID)) {

            if (resultSet.next()) {
                maxOrderId = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxOrderId;
    }

    @Override
    public List<Orders> getOrdersByUserId(int userId) {
        List<Orders> orderList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(GET_ORDERS_BY_USERID)) {

            pstmt.setInt(1, userId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Orders order = new Orders(
                        resultSet.getInt("ordersId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getInt("totalAmount"),
                        resultSet.getString("status"),
                        resultSet.getString("paymentOption"),
                        null // Set restaurantName as null; will be fetched later if needed
                    );
                    // Fetch and set restaurant name
                    String restaurantName = getRestaurantNameById(order.getRestaurantId(), con);
                    order.setRestaurantName(restaurantName);
                    
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

  //ordersDAOImpl

    List<Orders> extractOrdersListFromResultSet(ResultSet resultSet) {
    	    List<Orders> ordersList = new ArrayList<>();
    	    try {
    	        while (resultSet.next()) {
    	            Orders order = new Orders();
    	            order.setOrdersId(resultSet.getInt("ordersId"));
    	            order.setUserId(resultSet.getInt("userId"));
    	            order.setRestaurantId(resultSet.getInt("restaurantId"));
    	            order.setTotalAmount(resultSet.getInt("totalAmount"));
    	            order.setStatus(resultSet.getString("status"));
    	            order.setPaymentOption(resultSet.getString("paymentOption"));
    	            
    	            // Fetch restaurant name by restaurantId
    	            String restaurantName = getRestaurantNameById(order.getRestaurantId(), con);
    	            order.setRestaurantName(restaurantName); // Set the fetched restaurant name
    	            
    	            ordersList.add(order);
    	        }
    	    } 
    	    catch (Exception e) 
    	    {
    	        e.printStackTrace();
    	    }
    	    return ordersList;
    	}
}