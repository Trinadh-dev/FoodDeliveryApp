package com.Food_Delivery_App.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Food_Delivery_App.DAO.OrderHistoryDao;
import com.Food_Delivery_App.Model.OrderHistory;
import com.Food_Delivery_App.Uitl.DBConnector;

public class OrderHistoryDaoImplementation implements OrderHistoryDao {
    
    private static final String INSERT_ORDER_HISTORY = "INSERT INTO orderhistory (order_id, user_id, orderdate, totalamount, status) VALUES (?, ?, ?, ?, ?)";
    private static final String FETCH_ALL_ORDER_HISTORY = "SELECT * FROM orderhistory";
    private static final String FETCH_BY_ORDER_HISTORY_ID = "SELECT * FROM orderhistory WHERE orderhistory_id = ?";

    Connection con;
    PreparedStatement pstatement;
    ResultSet result;
    int status;
    
    public OrderHistoryDaoImplementation() {
        try {
            con = DBConnector.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertOrderHistory(OrderHistory history) {
        try {
            pstatement = con.prepareStatement(INSERT_ORDER_HISTORY);
            pstatement.setInt(1, history.getOrder_id());
            pstatement.setInt(2, history.getUser_id());
            pstatement.setString(3, history.getOrderdate());
            pstatement.setFloat(4, history.getTotalamount());
            pstatement.setString(5, history.getStatus());

            status = pstatement.executeUpdate();
            if (status == 1) {
                System.out.println("Order history added successfully.");
            } else {
                System.out.println("Failed to add order history.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<OrderHistory> getAllOrderHistory() {
        List<OrderHistory> list = new ArrayList<>();

        try {
            pstatement = con.prepareStatement(FETCH_ALL_ORDER_HISTORY);
            result = pstatement.executeQuery();

            while (result.next()) {
                int orderhistory_id = result.getInt("orderhistory_id");
                int order_id = result.getInt("order_id");
                int user_id = result.getInt("user_id");
                String orderdate = result.getString("orderdate");
                Float totalamount = result.getFloat("totalamount");
                String status = result.getString("status");

                OrderHistory history = new OrderHistory(orderhistory_id, order_id, user_id, orderdate, totalamount, status);
                list.add(history);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public OrderHistory getByOrderHistoryid(int orderhistory_id) {
        OrderHistory history = null;

        try {
            pstatement = con.prepareStatement(FETCH_BY_ORDER_HISTORY_ID);
            pstatement.setInt(1, orderhistory_id);
            result = pstatement.executeQuery();

            if (result.next()) {
                int order_id = result.getInt("order_id");
                int user_id = result.getInt("user_id");
                String orderdate = result.getString("orderdate");
                Float totalamount = result.getFloat("totalamount");
                String status = result.getString("status");

                history = new OrderHistory(orderhistory_id, order_id, user_id, orderdate, totalamount, status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return history;
    }
}
