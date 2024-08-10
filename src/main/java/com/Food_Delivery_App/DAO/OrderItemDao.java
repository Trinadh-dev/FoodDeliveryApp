package com.Food_Delivery_App.DAO;

import java.util.List;

import com.Food_Delivery_App.Model.OrderItem;

public interface OrderItemDao {
	
	int insertOrderItem(OrderItem item);
	List<OrderItem> getAllOrderItems();
	OrderItem getOrderItemById(int OrderItem_id);
	
}
