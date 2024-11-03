package com.tap.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.dao.UserDAO;

import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;


@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("userEmail");
		String password = req.getParameter("userPassword");
		
		System.out.println(email+"  "+password);
		
		UserDAO udao = new UserDAOImpl();
		User user = udao.getUserByEmail(email);
		
		
		
		if(user != null) {
			System.out.println(user.getUserPassword());
			if(password.equals(user.getUserPassword())) {
				session = req.getSession();
				session.setAttribute("user", user);
				resp.sendRedirect("HomeServlet");
			}
			else {
				resp.sendRedirect("failure.html");
			}
		}
		else {
			resp.sendRedirect("Register.html");
		}
	}

}
