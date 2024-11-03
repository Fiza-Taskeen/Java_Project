<%@ page import="com.tap.model.CartItem" %>
<%@ page import="com.tap.model.Cart" %>
<%@ page import="java.util.Collection, java.util.Map, java.text.NumberFormat" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    // Retrieve the Cart object from the session
    Cart cart = (Cart) session.getAttribute("cart");
    
    // Check if the cart is null or empty
    if (cart == null || cart.getCart().isEmpty()) {
        out.println("<h3>Your cart is empty!</h3>");
    } 
    else {
        Collection<CartItem> cartItems = cart.getCart().values(); // Get the list of items from the Cart object
        double grandTotal = 0;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new java.util.Locale("en", "IN"));
%>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <style>
    	body {
		    background-image: url('https://t4.ftcdn.net/jpg/04/33/38/31/360_F_433383199_z1mLbi6GTKBmrtauE7sToBWPSfTdkUGa.jpg');
		    background-size: cover; /* Ensures the image covers the entire page */
		    background-position: center; /* Center the background image */
		    background-repeat: repeat; /* Prevents the image from repeating */
		}
		
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
            font-size: 25px;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        input[type=number] {
            width: 100px;
            text-align: left;
            font-size: 20px;
        }
        button {
            padding: 5px 10px;
        }
        .action-buttons {
            display: flex; /* Use flexbox for horizontal alignment */
            justify-content: center; /* Center buttons horizontally */
            gap: 10px; /* Space between buttons */
        }
        .update-btn {
            background-color: #004aad;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
        }
        .delete-btn {
            background-color: #e63946; /* Changed color for clarity */
            color: white;
            padding: 12px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
        }
        .update-btn:hover {
            background-color: #003b8b;
        }
        .delete-btn:hover {
            background-color: #d62839; /* Changed hover color for clarity */
        }
        .proceed-btn {
            background-color: #004aad;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
        }
        .proceed-btn:hover {
            background-color: #003b8b;
        }
        
    </style>
</head>
<body>

<h2>Your Shopping Cart</h2>

<table>
    <thead>
        <tr>
            <th>Item Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
    <%
        for (CartItem item : cartItems) {
            double subtotal = item.getPrice() * item.getQuantity();
            grandTotal += subtotal;
    %>
    <tr>
        <td><%= item.getItemName() %></td>
        <td><%= currencyFormat.format(item.getPrice()) %></td>
        <td>
            <!-- Move the input inside the update form -->
            <form action="Cart" method="post" style="display:inline;">
                <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                <input type="hidden" name="action" value="update">
                <input type="number" name="quantity" value="<%= item.getQuantity() %>" class="quantity-input" min="1" />
        </td>
        <td><%= currencyFormat.format(subtotal) %></td>
        <td class="action-buttons">
            <!-- Wrap both forms in a div to position buttons inline -->
            <div style="display: inline;">
                <!-- Update button -->
                <button type="submit" class="update-btn">Update</button>
            </form>

            <!-- Delete form directly beside it -->
            <form action="Cart?menuId=<%= item.getMenuId() %>" method="post" style="display:inline;">
                <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                <input type="hidden" name="action" value="remove">
                <button type="submit" class="delete-btn">Delete</button>
            </form>
            </div>
        </td>
    </tr>
    <%
        }
    %>
</tbody>


</table>

<h1>Grand Total: <%= currencyFormat.format(grandTotal) %></h3>

<%
    session.setAttribute("grandTotal", grandTotal);
%>


<form action="checkout.jsp" method="get">
    <center><button type="submit" class="proceed-btn">Proceed to Checkout</button></center>
</form>
</body>
</html>

<%
    }
%>
