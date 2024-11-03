<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "com.tap.daoimpl.MenuDAOImpl, java.util.List, com.tap.model.Menu" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Restaurant Menu</title>
<style>

	body {
        background-image: url('https://png.pngtree.com/58pic/12/54/15/38i58PICa64SqZ6rxb0aI_PIC2018.jpg');
        background-size: cover;
        background-position: center;
        background-repeat: repeat;
    }
    
     /* Card Container */
        .card-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: space-between;
            margin-top: 20px;
        }
    
     /* Card Style */
        .card {
           	
             background-image: url('https://c8.alamy.com/compes/jfa266/menu-de-cafeteria-vertical-con-fondo-blanco-y-borde-de-cinta-retro-jfa266.jpg');
		    background-size: cover; /* Ensures the image covers the entire page */
		    background-position: center; /* Center the background image */
		    background-repeat: repeat; /* Prevents the image from repeating */
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            flex: 1 1 calc(33.33% - 20px); /* 3 cards per row with spacing */
            padding: 15px;
            text-align: center;
            transition: transform 0.3s ease;
            box-sizing: border-box;
        }

        /* Same Image for All Cards */
        .card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 5px;
            margin-bottom: 15px;
        }

        .card:hover {
            transform: translateY(-10px);
        }

        .card h3 {
            margin: 0;
            font-size: 2em;
            color: #333;
        }

        .card p {
            color: #333;
            font-size: 1.3em;
            margin: 10px 0;
        }

        .card button {
            background-color: #004aad;
            border: none;
            color: white;
            padding: 10px 15px;
            font-size: 1em;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .card button:hover {
            background-color: #003b8b;
        }

        /* Responsive design adjustments */
        @media screen and (max-width: 1024px) {
            .card {
                flex: 1 1 calc(50% - 20px); /* 2 cards per row on smaller screens */
            }
        }

        @media screen and (max-width: 768px) {
            .card {
                flex: 1 1 calc(50% - 20px); /* Adjust for smaller tablets */
            }
        }

        @media screen and (max-width: 480px) {
            .card {
                flex: 1 1 100%; /* 1 card per row on mobile */
            }
        }
</style>
</head>
<body>
    <div class="card-container">
        <%
            // Retrieve the menu list from the session
            List<Menu> mList = (List<Menu>) session.getAttribute("mList");
            if (mList != null) {
        %>
        
            <%
                // Loop through the menu list and display each item as a card
                for (Menu m : mList) {
                    if (m != null) {
            %>
                    <div class="card">
                        <h3><% out.println( m.getItemName()); %></h3>
                        <p><strong>Description:</strong> <%out.println(m.getDescription()); %></p>
                        <p><strong>Price:</strong> <%out.println( m.getPrice()); %></p>
                        <form action="cart?menuId=<%= m.getMenuId() %>"  method="post">
                        		Quantity: <input type="number" name="quantity" value="1" class="quantity-input" min = "1">
                        		<button type="submit" class="add-to-cart-btn">Add to cart</button>
                        		<input type="hidden" name="action" value="add">
                        </form>
                    </div>
            <%
                    }
                }
            %>

        <%
            } else {
        %>
            <p>No Menu available for this Restaurant.</p>
        <%
            }
        %>
    </div>
</body>
</html>
