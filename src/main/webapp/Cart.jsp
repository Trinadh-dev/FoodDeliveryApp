<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.Food_Delivery_App.Model.Cart"%>
<%@ page import="com.Food_Delivery_App.Model.CartItem"%>
<%@ page session="true"%>

<%
    // Retrieve the cart from the session
    Cart cart = (Cart) session.getAttribute("cart");

    if (cart == null || cart.getItems().isEmpty() ) {
        response.sendRedirect(request.getContextPath() + "/home"); // Redirect to menu if cart is empty
        return; // Prevent further execution of this page
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart - Food Delivery App</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style1.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div id="main">

        <!-- Nav Bar -->
        <nav>
            <div id="nav-left" class="d-flex align-items-center">
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
                <img src="${pageContext.request.contextPath}/img/profile-placeholder.png" alt="Profile Image" id="profile-image">
            </div>
        </nav>

        <!-- Cart Title -->
        <div style="margin-top: 60px;">
            <h1>Your Cart</h1>
            
        </div>

        <!-- Cart Items List -->
        <div class="Cart_List">
            <%
                float total = 0;
                for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                    CartItem item = entry.getValue();
                    total += item.getSubtotal();
            %>
            <div class="Cart_card">
                <img src="${pageContext.request.contextPath}/img/food-placeholder.png" alt="Food Image">
                <h1><%= item.getName() %></h1>
                <h2>₹ <%= item.getPrice() %></h2>
                <h3>Quantity: <%= item.getQuantity() %></h3>
                <h3>Subtotal: ₹ <%= item.getSubtotal() %></h3>
                <form action="cart" method="post">
                    <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1">
                    <input type="submit" value="Update">
                    <input type="hidden" name="item_id" value="<%= item.getItem_id() %>">
                    <input type="hidden" name="action" value="update">
                </form>
                <form action="cart" method="post">
                    <input type="hidden" name="item_id" value="<%= item.getItem_id() %>">
                    <input type="submit" value="Remove">
                    <input type="hidden" name="action" value="remove">
                </form>
            </div>
            <%
                }
            %>
            <div class="Cart_total">
                <h2>Total: ₹ <%= total %></h2>
                <a href="${pageContext.request.contextPath}/CheckOut.jsp" class="btn btn-success">Proceed to Checkout</a>
            </div>
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