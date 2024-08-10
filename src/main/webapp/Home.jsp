<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <li><a href="home.jsp">Home</a></li>
            <li><a href="#">Menu</a></li>
            <li><input type="text" placeholder="Search..."></li>
            <li><a href="#">Add to Cart</a></li>
            <li><a href="${pageContext.request.contextPath}/login">Sign In</a></li>
            <c:if test="${sessionScope.username != null}">
                <li><a href="#">Welcome ${sessionScope.username}</a></li>
                <li><a href="#">Contact Us</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </c:if>
        </ul>
    </nav>

    <!-- Carousel -->
    <div class="carousel">
        <img src="${pageContext.request.contextPath}/img/food1.jpg" alt="Food Image 1">
        <img src="${pageContext.request.contextPath}/img/food2.jpg" alt="Food Image 2">
        <img src="${pageContext.request.contextPath}/img/food3.jpg" alt="Food Image 3">
    </div>

    <!-- Restaurant Cards -->
    <div class="restaurant-cards">
        <c:forEach var="restaurant" items="${restaurantList}">
            <div class="card">
                <img src="${restaurant.image}" alt="${restaurant.name}">
                <div class="card-content">
                    <h3>${restaurant.name}</h3>
                    <p>${restaurant.description}</p>
                    <p>${restaurant.location}</p>
                </div>
            </div>
        </c:forEach>
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
