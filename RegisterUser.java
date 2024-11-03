package com.tap.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.dao.UserDAO;
import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/insertUser")
public class RegisterUser extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("userName");
		String email = req.getParameter("userEmail");
		String password = req.getParameter("userPassword");
		String address = req.getParameter("userAddress");
		
		User u = new User(username, email, password, address);
		
		UserDAO udaoi = new UserDAOImpl();
		
		int x = udaoi.insertUser(u);
		
		if(x==0) {
			resp.sendRedirect("failure.html");
		}
		else {
			resp.sendRedirect("success.html");
		}
	}
}
