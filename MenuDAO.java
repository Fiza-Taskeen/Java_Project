package com.tap.dao;



import java.util.ArrayList;

import com.tap.model.Menu;

public interface MenuDAO {
	
	int insertMenu(Menu menu);
	Menu getMenuById(int id);
	int deleteByMenuId(int id);
	int updateAvailStatus(int id, boolean isAvailable);
	ArrayList<Menu> getMenuByRestId(int resId);
}
