<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.Food_Delivery_App.DAOImplementation.Cart"%>
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
				<a href="#" class="nav-link">Contact Us</a> <a href="#"
					class="nav-link">View Cart</a>
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

		<!-- Cart Title -->
		<div style="margin-top: 60px; display:flex; justify-content:center;">
			<h1>Your Cart</h1>
		</div>

		<!-- Cart Items List -->
		<div class="cart-List">
			<%
        float total = 0;
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem item = entry.getValue();
            total += item.getSubtotal();
    %>
			<div class="cart">
				<div class="sub-cart image-container">
				 <img src="${pageContext.request.contextPath}/image?type=menu&id=<%= item.getItem_id() %>" alt="<%= item.getName() %>" class="cart-item-image" >
					
				</div>
				<div class="sub-cart item-details">
					<h1><%= item.getName() %></h1>
					<h5>
						Price ₹
						<%= item.getPrice() %></h5>
				</div>
				<div class="sub-cart quantity-container">
					<button class="quantity-button"
						onclick="changeQuantity(event, '<%= item.getItem_id() %>', 'decrease')">-</button>
					<input type="number" id="quantity_<%= item.getItem_id() %>"
						name="quantity" value="<%= item.getQuantity() %>" min="1" readonly>
					<button class="quantity-button"
						onclick="changeQuantity(event, '<%= item.getItem_id() %>', 'increase')">+</button>
				</div>
				<div class="sub-cart subtotal">
					<h3>Sub Total</h3>
					<h3>
						₹
						<%= item.getSubtotal() %></h3>
				</div>
				<div class="sub-cart actions">
					<form action="cart" method="post">
						<input type="hidden" name="item_id"
							value="<%= item.getItem_id() %>"> <input type="hidden"
							name="action" value="remove"> <input type="submit"
							value="Remove" class="action-button remove-button">
					</form>
				</div>
			</div>
			<%
        }
    %>
			<div class="cart-total">
				<h2>
					Total: ₹
					<%= total %></h2>
				<a href="${pageContext.request.contextPath}/CheckOut.jsp"
					class="btn btn-success">Proceed to Checkout</a>
			</div>
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

	<script>
		function changeQuantity(event, itemId, action) {
			event.preventDefault();
			const quantityInput = document.getElementById('quantity_' + itemId);
			let currentQuantity = parseInt(quantityInput.value);
			if (action === 'increase') {
				currentQuantity++;
			} else if (action === 'decrease' && currentQuantity > 1) {
				currentQuantity--;
			}
			quantityInput.value = currentQuantity;

			// Optionally, submit a form to update the cart server-side
			const form = document.createElement('form');
			form.method = 'post';
			form.action = 'cart';
			const itemIdInput = document.createElement('input');
			itemIdInput.type = 'hidden';
			itemIdInput.name = 'item_id';
			itemIdInput.value = itemId;
			const quantityInputField = document.createElement('input');
			quantityInputField.type = 'hidden';
			quantityInputField.name = 'quantity';
			quantityInputField.value = currentQuantity;
			const actionInput = document.createElement('input');
			actionInput.type = 'hidden';
			actionInput.name = 'action';
			actionInput.value = 'update';
			form.appendChild(itemIdInput);
			form.appendChild(quantityInputField);
			form.appendChild(actionInput);
			document.body.appendChild(form);
			form.submit();
		}
	</script>
</body>
</html>
