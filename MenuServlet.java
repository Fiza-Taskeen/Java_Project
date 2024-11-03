package com.tap.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.dao.MenuDAO;
import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.Menu;


/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private HttpSession session;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession(false);
		if(session != null) {
			int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
			
			MenuDAO mdao = new MenuDAOImpl();
			List<Menu> mList =  (List<Menu>) mdao.getMenuByRestId(restaurantId);
	        
			req.getSession().setAttribute("mList", mList);
			req.getSession().setAttribute("restaurantId", restaurantId);
			
			
			
			resp.sendRedirect("Menu.jsp");
		
		}
			
		}
	}

