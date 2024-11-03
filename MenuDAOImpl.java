package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;


public class MenuDAOImpl implements MenuDAO {
	static Connection con;
	ArrayList<Menu> mList = new ArrayList<Menu>();
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private Menu menu;
	private int x;
	
	private static final String INSERT_MENU = "insert into menu(menuId, restaurantId, itemName, description, price, isAvailable) values(?, ?, ?, ?, ?, ?)";
	private static final String GET_BY_MENUID = "select * from menu where menuId = ?";
	private static final String DELETE_BY_MENUID = "delete from menu where menuId = ?";
	private static final String UPDATE_AVAIL_STATUS = "update menu set isAvailable = ? where menuId = ?";
	private static final String GET_BY_RESTID = "select * from menu where restaurantId = ?";
	
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
	public int insertMenu(Menu m) {
		int result = -1;
		try {
			pstmt = con.prepareStatement(INSERT_MENU);
			pstmt.setInt(1, m.getMenuId());
			pstmt.setInt(2, m.getRestaurantId());
			pstmt.setString(3, m.getItemName());
			pstmt.setString(4, m.getDescription());
			pstmt.setFloat(5, m.getPrice());
			pstmt.setBoolean(6, true);
			
			result = pstmt.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	@Override
	public Menu getMenuById(int id) {
		try {
			pstmt = con.prepareStatement(GET_BY_MENUID);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			mList = (ArrayList<Menu>) extractMenuListFromResultSet(resultSet);
			menu = mList.get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return menu;
	}
	
	
	@Override
	public int deleteByMenuId(int id) {
		try {
			pstmt = con.prepareStatement(DELETE_BY_MENUID);
			pstmt.setInt(1, id);
			x = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	
	@Override
	public int updateAvailStatus(int id, boolean isAvailable) {
		try {
			pstmt = con.prepareStatement(UPDATE_AVAIL_STATUS);
			pstmt.setInt(2, id);
			pstmt.setBoolean(1, isAvailable);
			x = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	@Override
	public ArrayList<Menu> getMenuByRestId(int id) {
		try {
			pstmt = con.prepareStatement(GET_BY_RESTID);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			mList = (ArrayList<Menu>)extractMenuListFromResultSet(resultSet);
			menu = mList.get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return mList;
	}
	
	
	List<Menu> extractMenuListFromResultSet(ResultSet resultSet) {
		List<Menu> mList = new ArrayList<>();
		try {
			while(resultSet.next()) {
				mList.add(new Menu(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), 
				                      resultSet.getString(4), resultSet.getFloat(5), resultSet.getBoolean(6)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mList;
	}
}
