<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Delivery App</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
    <div class="container" id="container">
        <div class="character-container">
            <img id="character" src="${pageContext.request.contextPath}/img/luffy.webp" alt="Character">
        </div>
        <div class="form-container sign-up-container">
            <form action="${pageContext.request.contextPath}/Register" method="post">
                <h2>Create Account</h2>
                <br>
                <input type="text" name="username" placeholder="User Name" required />
                <input type="email" name="email" placeholder="Enter your mail" required />
                <input type="text" name="phonenumber" placeholder="Phone Number" required />
                <input type="password" name="password" placeholder="Password" required />
                <input type="password" name="confirmPassword" placeholder="Confirm Password" required />
                <input type="text" name="address" placeholder="Address" required />
                <button type="submit">Sign Up</button>
            </form>
        </div>
        <div class="form-container sign-in-container">
            <form action="${pageContext.request.contextPath}/login" method="post">
                <!-- Error message box -->
                <div id="error-box" style="display: none;">
                    <!-- Error message will be injected here by JavaScript -->
                </div>
                <h1>Sign in</h1>
                <br>
                <input type="text" name="username" placeholder="User Name" required />
                <input type="password" name="password" placeholder="Password" required />
                <a href="#">Forgot your password?</a>
                <button type="submit">Sign In</button>
            </form>
        </div>
        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
                    <h1>Welcome Back!</h1>
                    <p>To keep connected with us please login with your personal info</p>
                    <button class="ghost" id="signIn">Sign In</button>
                </div>
                <div class="overlay-panel overlay-right">
                    <h1>Hello, Friend!</h1>
                    <p>Enter your personal details and start journey with us</p>
                    <button class="ghost" id="signUp">Sign Up</button>
                </div>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/JS/script.js"></script>
    <script>
        window.onload = function() {
            var errorBox = document.getElementById('error-box');
            var errorMessage = "<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>"; // Get error message from server
            
            if (errorMessage.trim() !== "") {
                errorBox.style.display = 'block';
                errorBox.innerText = errorMessage;
            } else {
                errorBox.style.display = 'none';
            }
        };
    </script>
</body>
</html>
