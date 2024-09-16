package com.Food_Delivery_App.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Food_Delivery_App.Model.Cart;
import com.Food_Delivery_App.Model.CartItem;
import com.Food_Delivery_App.Model.User;
import com.Food_Delivery_App.Uitl.DBConnector;

@WebServlet("/confirmOrder")
public class ConformOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check if user is logged in
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // User is not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            // Redirect to home page if cart is empty or null
            response.sendRedirect("home");
            return;
        }

        String paymentMode = request.getParameter("paymentMode");
        float totalAmount = cart.getTotalAmount();

        Connection connection = null;
        PreparedStatement orderStmt = null;
        PreparedStatement orderItemStmt = null;
        PreparedStatement orderHistoryStmt = null;

        try {
            connection = DBConnector.connect();
            connection.setAutoCommit(false); // Disable auto-commit for transaction management

            // Insert into ordertable
            String orderSql = "INSERT INTO ordertable (restaurant_id, user_id, totalamount, status, paymentmode) VALUES (?, ?, ?, ?, ?)";
            orderStmt = connection.prepareStatement(orderSql, PreparedStatement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, cart.getItems().values().iterator().next().getRestaurant_id()); // Assuming all items in cart are from the same restaurant
            orderStmt.setInt(2, user.getUser_id());
            orderStmt.setFloat(3, totalAmount);
            orderStmt.setString(4, "Confirmed");
            orderStmt.setString(5, paymentMode);
            orderStmt.executeUpdate();

            // Get generated order_id
            int orderId = 0;
            var rs = orderStmt.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // Insert into orderitem for each item in the cart
            String orderItemSql = "INSERT INTO orderitem (order_id, menu_id, quantity, subtotal) VALUES (?, ?, ?, ?)";
            orderItemStmt = connection.prepareStatement(orderItemSql);
            for (CartItem item : cart.getItems().values()) {
                orderItemStmt.setInt(1, orderId);
                orderItemStmt.setInt(2, item.getItem_id());
                orderItemStmt.setInt(3, item.getQuantity());
                orderItemStmt.setFloat(4, item.getSubtotal());
                orderItemStmt.addBatch(); // Batch processing for efficiency
            }
            orderItemStmt.executeBatch();

            // Insert into orderhistory
            String orderHistorySql = "INSERT INTO orderhistory (order_id, user_id, orderdate, totalamount, status) VALUES (?, ?, ?, ?, ?)";
            orderHistoryStmt = connection.prepareStatement(orderHistorySql);
            orderHistoryStmt.setInt(1, orderId);
            orderHistoryStmt.setInt(2, user.getUser_id());
            orderHistoryStmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            orderHistoryStmt.setFloat(4, totalAmount);
            orderHistoryStmt.setString(5, "Confirmed");
            orderHistoryStmt.executeUpdate();

            connection.commit(); // Commit transaction

            // Clear cart after order confirmation
            cart.clear();
            session.setAttribute("cart", cart);

            // Redirect to order success page
            response.sendRedirect("OrderConformation.jsp");

        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback transaction in case of error
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            response.sendRedirect("orderFailure.jsp"); // Redirect to order failure page

        } finally {
            try {
                if (orderStmt != null) orderStmt.close();
                if (orderItemStmt != null) orderItemStmt.close();
                if (orderHistoryStmt != null) orderHistoryStmt.close();
                if (connection != null) connection.setAutoCommit(true); // Re-enable auto-commit
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
