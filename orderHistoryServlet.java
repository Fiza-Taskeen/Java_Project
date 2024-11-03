//orderHistoryServlet

package com.tap.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.dao.OrdersDAO;
import com.tap.daoimpl.OrdersDAOImpl;
import com.tap.model.Orders;
import com.tap.model.User;

@WebServlet("/orderHistoryServlet")
public class orderHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OrdersDAO ordersDao = new OrdersDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Retrieve the user from the session
        User user = (User) session.getAttribute("user");
        
        if (user != null) {
            int userId = user.getUserId();
            
            // Fetch order history for the logged-in user using userId
            List<Orders> orderList = ordersDao.getOrdersByUserId(userId);
            
            // Store order list in session for JSP page to access
            session.setAttribute("orderList", orderList);
            
            // Redirect to order history page to display the orders
            response.sendRedirect("orderHistory.jsp");
        } else {
            // If no user is logged in, redirect to login page
            response.sendRedirect("Login.html");
        }
    }
}