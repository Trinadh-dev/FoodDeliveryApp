package com.Food_Delivery_App.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.Food_Delivery_App.DAO.OrderTableDao;
import com.Food_Delivery_App.Model.OrderTable;
import com.Food_Delivery_App.Uitl.DBConnector;

public class OrderTableDaoImplementation implements OrderTableDao {

    Connection con;
    PreparedStatement pstatement;
    Statement statement;
    ResultSet result;
    int status;
    List<OrderTable> list = new ArrayList<>();

    private static final String INSERT_ORDER = "INSERT INTO `ordertable`(`restaurant_id`, `user_id`, `totalamount`, `status`, `paymentmode`) VALUES (?,?,?,?,?)";
    private static final String FETCH_ALL_ORDERS = "SELECT * FROM `ordertable`";
    private static final String FETCH_ORDER_BY_ID = "SELECT * FROM `ordertable` WHERE `order_id`=?";
//    private static final String UPDATE_ORDER = "UPDATE `ordertable` SET `restaurant_id`=?, `user_id`=?, `totalamount`=?, `status`=?, `paymentmode`=? WHERE `order_id`=?";
    private static final String DELETE_ORDER = "DELETE FROM `ordertable` WHERE `order_id`=?";

    public OrderTableDaoImplementation() {
        try {
            con = DBConnector.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertOrder(OrderTable order) {
        try {
            pstatement = con.prepareStatement(INSERT_ORDER);
            pstatement.setInt(1, order.getRestaurant_id());
            pstatement.setInt(2, order.getUser_id());
            pstatement.setFloat(3, order.getTotalamount());
            pstatement.setString(4, order.getStatus());
            pstatement.setString(5, order.getPaymentmode());

            status = pstatement.executeUpdate();
            if(status == 1) {
                System.out.println("Order inserted successfully.");
            } else {
                System.out.println("Failed to insert order.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<OrderTable> fetchAllOrders() {
        try {
            statement = con.createStatement();
            result = statement.executeQuery(FETCH_ALL_ORDERS);
            list = getOrderDetails(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public OrderTable fetchOrderById(int orderId) {
        OrderTable order = null;
        try {
            pstatement = con.prepareStatement(FETCH_ORDER_BY_ID);
            pstatement.setInt(1, orderId);
            result = pstatement.executeQuery();
            if (result.next()) {
                order = new OrderTable(result.getInt(1), result.getInt(2), result.getInt(3), result.getFloat(4),
                        result.getString(5), result.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

//    @Override
//    public int updateOrder(OrderTable order) {
//        try {
//            pstatement = con.prepareStatement(UPDATE_ORDER);
//            pstatement.setInt(1, order.getRestaurant_id());
//            pstatement.setInt(2, order.getUser_id());
//            pstatement.setFloat(3, order.getTotalamount());
//            pstatement.setString(4, order.getStatus());
//            pstatement.setString(5, order.getPaymentmode());
//            pstatement.setInt(6, order.getOrder_id());
//
//            status = pstatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return status;
//    }

    @Override
    public int deleteOrder(int orderId) {
        try {
            pstatement = con.prepareStatement(DELETE_ORDER);
            pstatement.setInt(1, orderId);
            status = pstatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private List<OrderTable> getOrderDetails(ResultSet result) throws SQLException {
        while (result.next()) {
            list.add(new OrderTable(result.getInt(1), result.getInt(2), result.getInt(3), result.getFloat(4),
                    result.getString(5), result.getString(6)));
        }
        return list;
    }
}
