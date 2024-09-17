<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.Food_Delivery_App.DAOImplementation.Cart"%>
<%@ page import="com.Food_Delivery_App.Model.CartItem"%>
<%@ page session="true"%>

<%
// Retrieve the cart from the session
   Cart cart = (Cart) session.getAttribute("cart");

    // If the cart is null or empty, redirect to the home page
    if (cart == null || cart.getItems().isEmpty()) {
        response.sendRedirect(request.getContextPath() + "/home");
        return; // Prevent further execution of this page
    }

    // Calculate the total cost
    float totalCost = 0;
    for (CartItem item : cart.getItems().values()) {
        totalCost += item.getSubtotal();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - Food Delivery App</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/checkoutcss.css">
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
                <a href="${pageContext.request.contextPath}/cart" class="nav-link">View Cart</a>
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

        <!-- Checkout Title -->
        <div style="margin-top: 60px;">
            <h1>Checkout</h1>
        </div>

        <!-- Cart Summary -->
        <div class="cart-summary">
            <h2>Your Order</h2>
            <ul class="list-group">
                <%
                    for (CartItem item : cart.getItems().values()) {
                %>
                <li class="list-group-item">
                    <div class="d-flex justify-content-between">
                        <span><%= item.getName() %> (x<%= item.getQuantity() %>)</span>
                        <span>₹ <%= item.getSubtotal() %></span>
                    </div>
                </li>
                <%
                    }
                %>
                <li class="list-group-item d-flex justify-content-between">
                    <strong>Total</strong>
                    <strong>₹ <%= totalCost %></strong>
                </li>
            </ul>
        </div>

        <!-- Payment Form -->
        <div class="payment-form">
            <h2>Payment Details</h2>
            <form action="${pageContext.request.contextPath}/confirmOrder" method="post">
                <!-- Payment Method Selection -->
                <div class="mb-3">
                    <label for="paymentMethod" class="form-label">Select Payment Method</label>
                    <select class="form-select" id="paymentMethod" name="paymentMethod" required>
                        <option value="cod">Cash on Delivery (COD)</option>
                        <option value="creditCard">Credit/Debit Card</option>
                        <option value="upi">UPI</option>
                        <option value="netBanking">Net Banking</option>
                    </select>
                </div>

                <!-- Credit/Debit Card Details -->
                <div id="cardDetails" style="display: none;">
                    <div class="mb-3">
                        <label for="cardholderName" class="form-label">Cardholder Name</label>
                        <input type="text" class="form-control" id="cardholderName" name="cardholderName">
                    </div>
                    <div class="mb-3">
                        <label for="cardNumber" class="form-label">Card Number</label>
                        <input type="text" class="form-control" id="cardNumber" name="cardNumber">
                    </div>
                    <div class="mb-3">
                        <label for="expiryDate" class="form-label">Expiry Date</label>
                        <input type="month" class="form-control" id="expiryDate" name="expiryDate">
                    </div>
                    <div class="mb-3">
                        <label for="cvv" class="form-label">CVV</label>
                        <input type="text" class="form-control" id="cvv" name="cvv">
                    </div>
                </div>

                <!-- UPI Details -->
                <div id="upiDetails" style="display: none;">
                    <div class="mb-3">
                        <label for="upiId" class="form-label">UPI ID</label>
                        <input type="text" class="form-control" id="upiId" name="upiId">
                    </div>
                </div>

                <!-- Net Banking Details -->
                <div id="netBankingDetails" style="display: none;">
                    <div class="mb-3">
                        <label for="bankName" class="form-label">Bank Name</label>
                        <input type="text" class="form-control" id="bankName" name="bankName">
                    </div>
                    <div class="mb-3">
                        <label for="accountNumber" class="form-label">Account Number</label>
                        <input type="text" class="form-control" id="accountNumber" name="accountNumber">
                    </div>
                </div>

                <!-- Shipping Address -->
                <div class="mb-3">
                    <label for="shippingAddress" class="form-label">Shipping Address</label>
                    <textarea class="form-control" id="shippingAddress" name="shippingAddress" rows="3" required></textarea>
                </div>
	
                <button type="submit" class="btn btn-success">Place Order</button>
            </form>
        </div>

        <!-- Footer -->
        <footer>
            <p>&copy; 2024 Food Delivery App. All rights reserved.</p>
            <p><a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a></p>
            <p>Contact us at <a href="mailto:support@fooddeliveryapp.com">support@fooddeliveryapp.com</a></p>
        </footer>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.getElementById('paymentMethod').addEventListener('change', function() {
            var cardDetails = document.getElementById('cardDetails');
            var upiDetails = document.getElementById('upiDetails');
            var netBankingDetails = document.getElementById('netBankingDetails');
            
            // Hide all payment method details sections
            cardDetails.style.display = 'none';
            upiDetails.style.display = 'none';
            netBankingDetails.style.display = 'none';

            // Show the selected payment method's details section
            if (this.value === 'creditCard') {
                cardDetails.style.display = 'block';
            } else if (this.value === 'upi') {
                upiDetails.style.display = 'block';
            } else if (this.value === 'netBanking') {
                netBankingDetails.style.display = 'block';
            }
        });
    </script>
</body>
</html>
