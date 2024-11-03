package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemsDAO;

import com.tap.model.OrderItems;



public class OrderItemsDAOImpl implements OrderItemsDAO{
	static Connection con;
	ArrayList<OrderItems> itemList = new ArrayList<OrderItems>();
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private OrderItems orderItems;
	private int res;
	
	private static final String INSERT_ORDERITEMS = "insert into orderitems(orderId, menuId, quantity, subTotal) values(?, ?, ?, ?)";
	private static final String GET_BY_ORDERID = "select * from orderitems where orderId = ?";
	
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
	public int insertOrderItems(OrderItems orderItems) {
		int x = -1;
		try {
			pstmt = con.prepareStatement(INSERT_ORDERITEMS);
			pstmt.setInt(1, orderItems.getOrderId());
			pstmt.setInt(2, orderItems.getMenuId());
			pstmt.setInt(3, orderItems.getQuantity());
			pstmt.setFloat(4, orderItems.getSubTotal());
			
			x = pstmt.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return x;
		
	}
	
	@Override
	public OrderItems getOrderItemsByOrderId(int orderId) {
		try {
			pstmt = con.prepareStatement(GET_BY_ORDERID);
			pstmt.setInt(1, orderId);
			resultSet = pstmt.executeQuery();
			itemList = (ArrayList<OrderItems>) extractOrderItemsListFromResultSet(resultSet);
			orderItems = itemList.get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orderItems;
	}
	
	List<OrderItems> extractOrderItemsListFromResultSet(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				itemList.add(new OrderItems(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), 
				                      resultSet.getInt(4), resultSet.getFloat(5)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}
}

