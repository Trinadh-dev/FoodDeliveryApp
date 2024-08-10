package com.Food_Delivery_App.DAO;

import java.util.List;

import com.Food_Delivery_App.Model.OrderHistory;

public interface OrderHistoryDao {
	
	int insertOrderHistory(OrderHistory history);
	List<OrderHistory> getAllOrderHistory();
	OrderHistory getByOrderHistoryid(int OrderHistory_id);

}
