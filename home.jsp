<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "com.tap.model.User, java.util.List, com.tap.model.Restaurant, com.tap.daoimpl.MenuDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <style>
    	
    	body {
		    background-image: url('https://images.squarespace-cdn.com/content/v1/5f329f79c232cb4ecb69ba9a/1612605366161-JMYH2SDRM98M9ZEFY31W/food+background+for+restaurant+menu-3.jpg');
		    background-size: cover; /* Ensures the image covers the entire page */
		    background-position: center; /* Center the background image */
		    background-repeat: repeat; /* Prevents the image from repeating */
		}
		
		h2 {
			color: white;
		}
		
		/* Navbar Styling */
		.navbar {
		    display: flex;
		    justify-content: space-between;
		    align-items: center;
		    padding: 10px 20px;
		    background-color: black;
		    color: white;
		    position: relative;
		}

        .navbar .logo {
		    font-size: 1.5em;
		    font-weight: bold;
		    display: flex; /* Use flexbox to align image and text */
		    align-items: center; /* Center items vertically */
		}
		
		.navbar .logo img.logo-image {
		    width: 50px; /* Adjust width as necessary */
		    height: auto; /* Maintain aspect ratio */
		    margin-right: 10px; /* Space between image and text */
		}

        .navbar .nav-items {
            display: flex;
            align-items: center;
            gap: 20px;
        }
        
        /* Adjust the position of nav-items, Order History button, and Cart */
		.navbar .nav-items,
		.btn .orderHistory-btn {
		    margin-right: 50px; /* Move closer to the center */
		}
		
		.navbar .nav-items {
		    display: flex;
		    align-items: center;
		    gap: 15px; /* Controls spacing between items */
		}

        .navbar .nav-items img {
            width: 30px;
            height: 30px;
            border-radius: 50%; /* Makes profile picture circular */
            cursor: pointer;
        }

       .cart {
		    text-align: center;
		    font-size: 1.2em;
		    cursor: pointer;
		}
		
		.cart-icon {
		    width: 30px;
		    height: auto;
		    margin-bottom: -5px;
		}

		
		.navbar .nav-items .dropdown {
		    margin-right: 100px; /* Adjust this value to move left */
		}


        /* Welcome message styling */
        .navbar .welcome {
	    font-size: 1.8em;
	    font-weight: normal;
	    position: absolute;
	    left: 50%;
	    transform: translateX(-50%);
	    white-space: nowrap;
	}

        /* Dropdown Styles */
        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 2;
            margin-top: 10px;
            border-radius: 5px;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }

        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }
    	
        /* Card Container */
        .card-container {
            display: flex;
            flex-wrap: wrap;
            gap: 30px;
            justify-content: space-between;
            margin-top: 20px;
        }

        /* Card Style */
        .card {
           	
             background-image: url('https://png.pngtree.com/background/20210710/original/pngtree-restaurant-menu-background-material-picture-image_1052797.jpg');
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
            font-size: 1.5em;
            color: #333;
        }

        .card p {
            color: #666;
            font-size: 1em;
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
        
        /* Order History Button Styling */
		.orderHistory-btn {
		    background-color: #004aad;
		    color: white;
		    padding: 10px 15px;
		    border: none;
		    border-radius: 8px;
		    font-size: 1em;
		    cursor: pointer;
		    margin-left: 850px;
		}
		
		.orderHistory-btn:hover {
		    background-color: #003b8b;
		}
    </style>
     <script>
        // JavaScript for toggling dropdown on click
        document.addEventListener("DOMContentLoaded", function() {
            const profilePic = document.querySelector('.navbar .nav-items img');
            const dropdown = document.querySelector('.dropdown-content');

            profilePic.addEventListener('click', function(event) {
                event.stopPropagation();
                dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
            });

            // Close dropdown if clicking outside
            document.addEventListener('click', function() {
                dropdown.style.display = 'none';
            });

            dropdown.addEventListener('click', function(event) {
                event.stopPropagation(); // Prevent dropdown from closing when clicking inside
            });
        });
    </script>
</head>
<body>

<!-- Card Section -->


<!-- Navbar -->
<div class="navbar">
    <div class="logo"><img src="th.jpg" alt="Logo" class="logo-image">Tap Foods</div>
    <div class="welcome">
        <%
            User u = (User) session.getAttribute("user");
            if (u != null) {
        %>
            Welcome, <% out.println(u.getUserName()); %>!
        <%
            } else {
        %>
            <a href="Login.html" style="color:white;">Login</a>
        <%
            }
        %>
    </div>
    <div class="btn">
    	<button type="button" class="orderHistory-btn" onclick="window.location.href= 'orderHistoryServlet'">Order History</button>
    </div>
    <div class="nav-items">
       <div class="cart">
	    <img alt="Cart" src="https://icon-library.com/images/cart-icon-png-white/cart-icon-png-white-2.jpg" class="cart-icon">
	    <div>Cart</div>
	</div>

        <div class="dropdown">
            <img src="https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg" alt="Profile Picture"> <!-- Use actual profile picture path -->
            <div class="dropdown-content">
                <a href="editProfile.html">Edit Profile</a>
                <a href="logout">Logout</a>
            </div>
        </div>
    </div>
</div>

<%
    if (u != null) {
%>

    <%
        // Retrieve the restaurant list from the session
        List<Restaurant> resList = (List<Restaurant>) session.getAttribute("resList");

        if (resList != null && !resList.isEmpty()) {
    %>

        <h2><center>Here are some recommended restaurants:</center></h2>

        <div class="card-container">
            <%
                // Loop through the restaurant list and display each as a card
                for (Restaurant r : resList) {
                    if (r != null) {
            %>
                <div class="card">
                    <h3><% out.println(r.getRestaurantName()); %></h3>
                    <p><strong>Cuisine:</strong> <% out.println(r.getCuisineType()); %></p>
                    <p><strong>Rating:</strong> <% out.println(r.getRatings()); %> / 5</p>
            
                    <a href = "MenuServlet?restaurantId=<%= r.getRestaurantId() %>"><button>View Menu</button></a>
                </div>
            <%
                    }
                }
            %>
        </div>

    <%
        } else {
    %>
        <p>No restaurants available.</p>
    <%
        }
    %>
<%
    } 
%>

</body>
</html>
