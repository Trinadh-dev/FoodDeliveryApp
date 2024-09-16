<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style1.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Order Confirmation</h1>
        <p>Your order has been successfully placed!</p>
        <p>Thank you for ordering with us.</p>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Continue Shopping</a>
    </div>
</body>
</html>
