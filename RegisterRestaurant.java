package com.tap.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.dao.RestaurantDAO;
import com.tap.daoimpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

/**
 * Servlet implementation class RegisterRestaurant
 */
@WebServlet("/insertRestaurant")
public class RegisterRestaurant extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int restaurantid = Integer.parseInt(req.getParameter("restaurantId"));
		String restaurantname = req.getParameter("restaurantName");
		String cuisinetype = req.getParameter("cuisineType");
		boolean isactive = Boolean.parseBoolean(req.getParameter("isActive"));
		int ratings = Integer.parseInt(req.getParameter("ratings"));
		
		Restaurant r = new Restaurant(restaurantid, restaurantname, cuisinetype, isactive, ratings);
		
		RestaurantDAO rdaoi = new RestaurantDAOImpl();
		
		int x = rdaoi.insertRestaurant(r);
		
		if(x==0) {
			resp.sendRedirect("failure.html");
		}
		else {
			resp.sendRedirect("success.html");
		}
	}
	
}

