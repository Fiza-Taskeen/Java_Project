package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.tap.dao.UserDAO;

import com.tap.model.User;

public class UserDAOImpl implements UserDAO {
	
	static Connection con;
	ArrayList<User> userList = new ArrayList<User>();
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	
	private int res;
	private User user;
	private static final String GET_ALL_USERS = "select * from user";
	private static final String INSERT_USER = "insert into user(userName, userEmail, userPassword, userAddress) values(?, ?, ?, ?)";
	private static final String GET_USER_BYEMAIL = "select * from user where userEmail = ?";
	private static final String DELETE_USER_BYID = "delete from user where userId = ?";
	private static final String UPDATE_USER_BYID = "update user set userAddress = ? where userId = ?";
	
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
	public int insertUser(User user) {
		int x = -1;
		try {
			pstmt = con.prepareStatement(INSERT_USER);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserPassword());
			pstmt.setString(4, user.getUserAddress());
			
			x = pstmt.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return x;
		
	}

	@Override
	public List<User> getAllUsers() {
		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(GET_ALL_USERS);
			
			userList = (ArrayList<User>) extractUserListFromResultSet(resultSet);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User getUserByEmail(String email) {
		try {
			user = null;
			pstmt = con.prepareStatement(GET_USER_BYEMAIL);
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				user = new User(resultSet.getInt("userId"), resultSet.getString("userName"), resultSet.getString("userEmail"), 
						resultSet.getString("userPassword"), resultSet.getString("userAddress"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int deleteUserById(int id) {
		try {
			pstmt = con.prepareStatement(DELETE_USER_BYID);
			pstmt.setInt(1, id);
			res = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateUserById(int id, String address) {
		try {
			pstmt = con.prepareStatement(UPDATE_USER_BYID);
			pstmt.setInt(2, id);
			pstmt.setString(1, address);
			res = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	List<User> extractUserListFromResultSet(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				userList.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), 
				                      resultSet.getString(4), resultSet.getString(5)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

}

