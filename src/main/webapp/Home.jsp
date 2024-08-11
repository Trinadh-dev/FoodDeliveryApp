<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.Food_Delivery_App.Model.Restaurant"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Food Delivery App - Home</title>
<link rel="stylesheet" href="CSS/style1.css">
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar">
		<div class="logo">
			<img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo">
		</div>
		<ul class="nav-links">
			<!-- Use the correct link to call the HomeServlet -->
			<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
			<li><a href="#">Menu</a></li>
			<li><input type="text" placeholder="Search..."></li>
			<li><a href="#">Add to Cart</a></li>
			<%
                if (session.getAttribute("username") != null) {
            %>
			<li><a href="#">Welcome <%= session.getAttribute("username") %></a></li>
			<li><a href="#">Contact Us</a></li>
			<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
			<%
                } else {
            %>
			<li><a href="${pageContext.request.contextPath}/login">Sign
					In</a></li>
			<%
                }
            %>
		</ul>
	</nav>

	<!-- Carousel -->
	<div class="carousel">
		<img src="${pageContext.request.contextPath}/img/food1.jpg"
			alt="Food Image 1"> <img
			src="${pageContext.request.contextPath}/img/food2.jpg"
			alt="Food Image 2"> <img
			src="${pageContext.request.contextPath}/img/food3.jpg"
			alt="Food Image 3">
	</div>

	<!-- Restaurant Cards -->
	<div class="restaurant-cards">
		<%
    // Redirect to the HomeServlet if this JSP is accessed directly
    	if (session.getAttribute("restaurantList") == null) {
        	response.sendRedirect("home");
        	return;
    	}
		%>
		<% response.sendRedirect("/home"); %>
		<%
            List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");
            if (restaurantList != null && !restaurantList.isEmpty()) {
                for (Restaurant restaurant : restaurantList) {
        %>
		<div class="card">
			<div class="card-content">
				<h3><%= restaurant.getRestaurantname() %></h3>
				<p><%= restaurant.getCuisine_type() %></p>
				<p><%= restaurant.getAddress() %></p>
				<p>
					Rating:
					<%= restaurant.getRating() %></p>
			</div>
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

	<!-- Contact Us -->
	<footer class="contact-us">
		<h2>Contact Us</h2>
		<p>Email: support@fooddeliveryapp.com</p>
		<p>Phone: +123 456 7890</p>
	</footer>

	<script src="JS/script.js"></script>
</body>
</html>
