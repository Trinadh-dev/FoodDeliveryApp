<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.Food_Delivery_App.Model.Menu"%>
<%@ page session="true"%>

<%
    // Check if menuList is in session, if not, redirect to the HomeServlet
    List<Menu> menuList = (List<Menu>) session.getAttribute("menuList");

    if (menuList == null) {
        response.sendRedirect(request.getContextPath() + "/home");
        return; // Prevent further execution of this page
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Menu - Food Delivery App</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/style1.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div id="main">
		<!-- Nav Bar -->
		<nav>
			<div id="nav-left" class="d-flex align-items-center">
				<img
					src="${pageContext.request.contextPath}/img/Logo1-removebg-preview.png"
					alt="Logo">
				<h2>
					<%
                        if (session.getAttribute("username") != null) {
                            out.print("Welcome, " + session.getAttribute("username"));
                        } else {
                            out.print("Welcome");
                        }
                    %>
				</h2>
			</div>
			<div id="nav-right">
				<a href="${pageContext.request.contextPath}/home" class="nav-link">Home</a>
				<a href="#" class="nav-link">Contact Us</a> <a
					href="${pageContext.request.contextPath}/cart" class="nav-link">View
					Cart</a>
				<%
                    if (session.getAttribute("username") != null) {
                %>
				<a href="${pageContext.request.contextPath}/logout" class="nav-link">Logout</a>
				<%
                    } else {
                %>
				<a href="${pageContext.request.contextPath}/login" class="nav-link">Sign
					In</a>
				<%
                    }
                %>
				<img
					src="${pageContext.request.contextPath}/img/profile-placeholder.png"
					alt="Profile Image" id="profile-image">
			</div>
		</nav>

		<!-- Restaurant Title -->
		<div class="name">
			<h1><%= session.getAttribute("RestaurantName") %></h1>
		</div>

		<!-- Menu List -->
		<div class="Menu_List">
			<%
                if (menuList != null && !menuList.isEmpty()) {
                    for (Menu menuItem : menuList) {
            %>
			<div class="Menu_card">
				<div class="Menu_card-content">
					<h1><%= menuItem.getMenuname() %></h1>
					<h3>
						₹<%= menuItem.getPrice() %></h3>
					<h2><%= menuItem.getDescription() %></h2>
				</div>
				<div class="Menu_card-image">
					<img
						src="${pageContext.request.contextPath}/image?type=menu&id=<%= menuItem.getMenu_id() %>"
						alt="Menu Image">
					<form method="post"
						action="${pageContext.request.contextPath}/cart">
						<input type="hidden" name="item_id"
							value="<%= menuItem.getMenu_id() %>"> <input
							type="hidden" name="action" value="add">
						<button type="button" class="add-to-cart">Add to Cart</button>
						<div class="quantity-controls d-none">
							<button type="button" class="decrease">-</button>
							<input type="number" name="quantity" value="1" min="1">
							<button type="button" class="increase">+</button>
						</div>
						<input type="submit" class="confirm-add-to-cart d-none"
							value="Confirm">
					</form>
				</div>
			</div>
			<%
                    }
                } else {
            %>
			<p>No menu items available.</p>
			<%
                }
            %>
		</div>

		<!-- Footer -->
		<footer>
			<p>&copy; 2024 Food Delivery App. All rights reserved.</p>
			<p>
				<a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a>
			</p>
			<p>
				Contact us at <a href="mailto:support@fooddeliveryapp.com">support@fooddeliveryapp.com</a>
			</p>
		</footer>
	</div>
	<script src="${pageContext.request.contextPath}/JS/MenuScript.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
