package com.tap.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.dao.MenuDAO;
import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/Cart")
public class CartServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart == null)
		{
			cart = new Cart();
			session.setAttribute("cart" , cart);
		}
		
		String action = req.getParameter("action");
		if ("add".equals(action))
		{
			addItemToCart(req, cart);
		}
		else if ("update".equals(action))
		{
			System.out.println("Update called ");
			updateCartItem(req, cart);
		}
		else if ("remove".equals(action))
		{
			removeItemFromCart(req, cart);
		}
		
		session.setAttribute("cart", cart);
		resp.sendRedirect("cart.jsp");
	}
	
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
			req.getRequestDispatcher("cart.jsp").forward(req, resp);
		}
	
	
	private void addItemToCart(HttpServletRequest req, Cart cart)
	{
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDAO menuDAO = new MenuDAOImpl();
		Menu menuItem = menuDAO.getMenuById(menuId);
		
		HttpSession session = req.getSession();
		session.setAttribute("restaurantId", menuItem.getRestaurantId());
		
		if (menuItem != null)
		{
			CartItem item = new CartItem(
					menuItem.getMenuId(),
					menuItem.getRestaurantId(),
					menuItem.getItemName(),
					quantity,
					menuItem.getPrice()
				);
			cart.addItem(item);
		}
	}
	
	public void updateCartItem(HttpServletRequest req, Cart cart)
	{
		System.out.println("In update Cart "+req.getParameter("menuId")+" "+req.getParameter("quantity"));
		int menuId = Integer.parseInt(req.getParameter("menuId"));
	    int quantity = Integer.parseInt(req.getParameter("quantity"));
	    
	    HttpSession session = req.getSession();
		cart.updateItem(menuId, quantity);
		session.setAttribute("cart", cart);
	}
	
	public void removeItemFromCart(HttpServletRequest req, Cart cart)
	{
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		
		HttpSession session = req.getSession();
		cart.removeItem(menuId);
		session.setAttribute("cart", cart);
	}
}
