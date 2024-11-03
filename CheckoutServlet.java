package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.dao.OrderHistoryDAO;
import com.tap.dao.OrderItemsDAO;
import com.tap.dao.OrdersDAO;
import com.tap.daoimpl.OrderHistoryDAOImpl;
import com.tap.daoimpl.OrderItemsDAOImpl;
import com.tap.daoimpl.OrdersDAOImpl;
import com.tap.model.OrderHistory;
import com.tap.model.OrderItems;
import com.tap.model.Orders;
import com.tap.model.User;

public class CheckoutServlet extends HttpServlet {

    private OrdersDAO ordersDao = new OrdersDAOImpl();
    private OrderItemsDAO orderItemDao = new OrderItemsDAOImpl();
    private OrderHistoryDAO orderHistoryDao = new OrderHistoryDAOImpl();

    private String status = "Delivered";
    private String paymentOption;
    private float total;
    private String restaurantName;
	private String itemName;
	private String cuisineType;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String address = request.getParameter("address");
            String paymentMode = request.getParameter("paymentMode");

            // Retrieve dynamic values from session or request (adjust based on your implementation)
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            int userId = user.getUserId();
            int restaurantId = (int) session.getAttribute("restaurantId"); // Assumed to be stored in session

            Double grandTotal = (Double) session.getAttribute("grandTotal");
            float totalAmount = 0.0f;
            if (grandTotal != null) {
                totalAmount = grandTotal.floatValue(); // Convert Double to float
            }

            System.out.println("userId " + userId + " restaurantId " + restaurantId);
            paymentOption = paymentMode;

            if (userId == 0 || restaurantId == 0 || grandTotal == 0) {
                System.out.println("Null");
                response.sendRedirect("failure.html");
                return; // Exit early since required data is missing
            }

            Orders order = new Orders(userId, restaurantId, totalAmount, status, paymentOption, restaurantName);
            int orderCreationResult = ordersDao.insertOrders(order);

            if (orderCreationResult > 0) {
                int orderId = ordersDao.getOrdersByMaxId();

                @SuppressWarnings("unchecked")
                List<OrderItems> cartItems = (List<OrderItems>) session.getAttribute("cartItems");
                if (cartItems != null && !cartItems.isEmpty()) {
                    for (OrderItems item : cartItems) {
                        item.setOrderId(orderId);
                        orderItemDao.insertOrderItems(item);
                    }
                } else {
                    System.out.println("Cart is empty or null");
                }

                OrderHistory orderHistory = new OrderHistory(userId, orderId, totalAmount, status);
                orderHistoryDao.insertOrderHistory(orderHistory);

                // **Fetch and set orderList in session for the JSP page**
                List<Orders> orderList =  ordersDao.getOrdersByUserId(userId); // Add this line
                session.setAttribute("orderList", orderList);                // And this line

                response.sendRedirect("success.html");
            } else {
                response.sendRedirect("failure.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
