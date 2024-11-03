package com.tap.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.dao.MenuDAO;
import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.Menu;

/**
 * Servlet implementation class RegisterMenu
 */
@WebServlet("/insertMenu")
public class RegisterMenu extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int menuid = Integer.parseInt(req.getParameter("menuId"));
		int restaurantid = Integer.parseInt(req.getParameter("restaurantId"));
		String itemname = req.getParameter("itemName");
		String descrption = req.getParameter("description");
		float price = Float.parseFloat(req.getParameter("price"));
		boolean isavailable = Boolean.parseBoolean(req.getParameter("isAvailable"));
		
		Menu m = new Menu(menuid, restaurantid, itemname, descrption, price, isavailable);
		
		MenuDAO mdaoi = new MenuDAOImpl();
		
		int x = mdaoi.insertMenu(m);
		
		if(x==0) {
			resp.sendRedirect("failure.html");
		}
		else {
			resp.sendRedirect("success.html");
		}
	}

}
