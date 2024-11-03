

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Orders" %>
<%@ page import="java.util.List" %>

<%
    List<Orders> orderList = (List<Orders>) session.getAttribute("orderList");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #000;
            color: #ffffff;
        }
        .order-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            margin: 20px;
        }
        .order-card {
            background-color: #1a1a1a;
            padding: 20px;
            border: 1px solid #333;
            border-radius: 8px;
            width: 300px;
            color: #fff;
        }
    </style>
</head>
<body>
    <h1>Order History</h1>
    <div class="order-container">
        <% if (orderList != null && !orderList.isEmpty()) { %>
            <% for (Orders order : orderList) { %>
            <div class="order-card">
                <div>Order ID: <%= order.getOrdersId() %></div>
                <div>Total Amount: <%= order.getTotalAmount() %></div>
                <div>Status: <%= order.getStatus() %></div>
                <div>Restaurant: <%= order.getRestaurantName() %></div>
                <div>Payment Option: <%= order.getPaymentOption() %></div>
            </div>
            <% } %>
        <% } else { %>
            <h2>No orders found</h2>
        <% } %>
    </div>
</body>
</html>