package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderHistoryDAO;

import com.tap.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO{
	static Connection con;
	ArrayList<OrderHistory> orderHistoryList = new ArrayList<OrderHistory>();
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private OrderHistory orderHistory;
	private int res;
	private int x;
	
	private static final String INSERT_ORDERHISTORY = "insert into orderhistory(userId, orderId, total, status) values(?, ?, ?, ?)";
	private static final String UPDATE_ORDER_HISTORY = "update orderhistory set status = ? where OrderHistoryId = ?";
	private static final String GET_ORDER_HISTORY_BY_USER_ID = "select * from orderhistory where userId = ?";
	
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
	public int insertOrderHistory(OrderHistory oh) {
		int result = -1;
		try {
			pstmt = con.prepareStatement(INSERT_ORDERHISTORY);
			pstmt.setInt(1, oh.getUserId());
			pstmt.setInt(2, oh.getOrderId());
			pstmt.setFloat(3, oh.getTotal());
			pstmt.setString(4, oh.getStatus());
			
			result = pstmt.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	@Override
	public int updateOrderHistory(int ohid, String status) {
		try {
			pstmt = con.prepareStatement(UPDATE_ORDER_HISTORY);
			pstmt.setInt(2, ohid);
			pstmt.setString(1, status);
			x = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	@Override
	public OrderHistory getOrderHistoryByuserId(int userId) {
		try {
			pstmt = con.prepareStatement(GET_ORDER_HISTORY_BY_USER_ID);
			pstmt.setInt(1, userId);
			resultSet = pstmt.executeQuery();
			orderHistoryList = (ArrayList<OrderHistory>) extractOrderHistoryListFromResultSet(resultSet);
			orderHistory = orderHistoryList.get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orderHistory;
	}
	
	
	List<OrderHistory> extractOrderHistoryListFromResultSet(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				orderHistoryList.add(new OrderHistory(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), 
				                      resultSet.getFloat(4), resultSet.getString(5)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return orderHistoryList;
	}
}

