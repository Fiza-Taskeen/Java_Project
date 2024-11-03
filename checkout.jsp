<%@ page import="java.text.NumberFormat" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    // Retrieve the grandTotal passed from cart.jsp (assuming it's passed via session or a request attribute)
     Double grandTotalDouble = (Double) session.getAttribute("grandTotal");
    float grandTotal = 0.0f;
    if (grandTotalDouble != null) {
        grandTotal = grandTotalDouble.floatValue(); // Convert Double to Float
    }
        

    // Initialize the currency formatter
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new java.util.Locale("en", "IN"));
%>


 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	 <title>Payment Page</title>
    <style>
    	body {
		    background-image: url('https://wallpapercave.com/wp/wp7029406.jpg');
		    background-size: cover; /* Ensures the image covers the entire page */
		    background-position: center; /* Center the background image */
		    background-repeat: repeat; /* Prevents the image from repeating */
		}
		
        .container {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="submit"], textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .radio-group {
            margin-bottom: 10px;
        }
        .radio-group input {
            margin-right: 10px;
        }
        .total-amount {
            font-size: 1.5em;
            margin-bottom: 20px;
            color: #004aad;
            text-align: center;
        }
        .submit-btn {
            background-color: #004aad;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }
        .submit-btn:hover {
            background-color: #003b8b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Confirm Your Payment</h2>

        <!-- Display Total Amount -->
        <div class="total-amount">
            Total Amount: <%= currencyFormat.format(grandTotal) %>
        </div>

        <!-- Address Input -->
        <form action="CheckoutServlet" method="post">
    <div class="form-group">
        <label for="address">Shipping Address:</label>
        <textarea id="address" name="address" rows="4" placeholder="Enter your shipping address here" required></textarea>
    </div>

    <!-- Payment Mode Options -->
    <div class="form-group">
        <label for="paymentMode">Payment Mode:</label>
        <div class="radio-group">
            <input type="radio" id="creditCard" name="paymentMode" value="Credit Card" required>
            <label for="creditCard">Credit Card</label>
        </div>
        <div class="radio-group">
            <input type="radio" id="debitCard" name="paymentMode" value="Debit Card">
            <label for="debitCard">Debit Card</label>
        </div>
        <div class="radio-group">
            <input type="radio" id="upi" name="paymentMode" value="UPI">
            <label for="upi">UPI</label>
        </div>
        <div class="radio-group">
            <input type="radio" id="cashOnDelivery" name="paymentMode" value="Cash on Delivery">
            <label for="cashOnDelivery">Cash on Delivery</label>
        </div>
    </div>

    <!-- Confirm Payment Button -->
    <div class="form-group">
        <input type="submit" value="Confirm Order" class="submit-btn">
    </div>
</form>

<style>
    .form-group {
        margin-bottom: 15px; /* Add some space between form groups */
    }

    .radio-group {
        display: flex; /* Use flexbox for horizontal alignment */
        align-items: center; /* Center align the items */
        margin-bottom: 5px; /* Space between radio groups */
    }

    .radio-group input[type="radio"] {
        margin-right: 5px; /* Add space between the radio button and label */
    }
</style>

    </div>
</body>
</html>
