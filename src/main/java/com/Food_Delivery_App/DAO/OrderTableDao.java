package com.Food_Delivery_App.DAO;

import java.util.List;

import com.Food_Delivery_App.Model.OrderTable;

public interface OrderTableDao {
	 int insertOrder(OrderTable order);
	    List<OrderTable> fetchAllOrders();
	    OrderTable fetchOrderById(int orderId);
	    int deleteOrder(int orderId);
}
