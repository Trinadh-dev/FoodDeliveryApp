<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.Food_Delivery_App.Model.Restaurant"%>
<%@ page session="true"%>

<%
    // Check if restaurantList is in session, if not, redirect to the HomeServlet
    if (session.getAttribute("restaurantList") == null) {
        response.sendRedirect(request.getContextPath() + "/home");
        return; // Prevent further execution of this page
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Delivery App - Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style1.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div id="main">

        <!-- Nav Bar -->
        <nav>
            <div id="nav-left">
                <img src="${pageContext.request.contextPath}/img/Logo1-removebg-preview.png" alt="Logo">
                <h2>
                    <%
                        if (session.getAttribute("username") != null) {
                            out.print("Welcome, " + session.getAttribute("username"));
                        } else {
                            out.print("Welcome, Guest");
                        }
                    %>
                </h2>
            </div>
            <div id="nav-right">
                <a href="${pageContext.request.contextPath}/home" class="nav-link">Home</a>
                <!--  <a href="#" class="nav-link">Menu</a> -->
                <a href="#" class="nav-link">Contact Us</a>
                <a href="#" class="nav-link">View Cart</a>
                <%
                	if (session.getAttribute("username") != null) {
                %>
                <a href="${pageContext.request.contextPath}/logout" class="nav-link">Logout</a>
                <%
                    } else {
                %>
                <a href="${pageContext.request.contextPath}/login" class="nav-link">Sign In</a>
                <%
                    }
                %>
                <img src="${pageContext.request.contextPath}/img/background-image.jpeg alt="Profile Image" id="profile-image">
            </div>
        </nav>

        <!-- Carousel -->
        <div class="carousel">
            <img src="${pageContext.request.contextPath}/img/background-image.jpeg" alt="Carousel Image">
        </div>

        <!-- Restaurant List -->
        <div class="Restaurant_List">
            <%
                List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");
                if (restaurantList != null && !restaurantList.isEmpty()) {
                    for (Restaurant restaurant : restaurantList) {
            %>
            <div class="Restaurant_card">
                <img src="${pageContext.request.contextPath}/img/restaurant_placeholder.png" alt="Restaurant Image">
                <h1><%= restaurant.getRestaurantname() %></h1>
                <h2><%= restaurant.getAddress() %></h2>
                <h3>Rating: <%= restaurant.getRating() %></h3>
                <a href="${pageContext.request.contextPath}/menu?restaurantId=<%= restaurant.getRestaurant_id() %>"><button>View Menu</button></a>
            </div>
            <%
                    }
                } else {
            %>
            <p>No restaurants available.</p>
            <%
                }
            %>
        </div>

        <!-- Footer -->
        <footer>
            <p>&copy; 2024 Food Delivery App. All rights reserved.</p>
            <p><a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a></p>
            <p>Contact us at <a href="mailto:support@fooddeliveryapp.com">support@fooddeliveryapp.com</a></p>
        </footer>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
