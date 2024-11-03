package com.tap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.model.Restaurant;
import com.tap.dao.RestaurantDAO;
import com.tap.daoimpl.RestaurantDAOImpl;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private HttpSession session;
	
	
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			session = req.getSession(false);
			if(session != null) {
				RestaurantDAO rdao = new RestaurantDAOImpl();
				List<Restaurant> resList = rdao.getAllRestaurants();
				
				session.setAttribute("resList", resList);
				resp.sendRedirect("home.jsp");
			}
			else {
				resp.sendRedirect("Login.html");
			}
		}

}
