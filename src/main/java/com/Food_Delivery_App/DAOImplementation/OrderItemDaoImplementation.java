package com.Food_Delivery_App.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Food_Delivery_App.DAO.OrderItemDao;
import com.Food_Delivery_App.Model.OrderItem;
import com.Food_Delivery_App.Uitl.DBConnector;

public class OrderItemDaoImplementation implements OrderItemDao {
	
	Connection con;
	PreparedStatement pstatement;
	Statement statement;
	ResultSet result;
	int status;
	List<OrderItem> list=new ArrayList<OrderItem>();
	
	
	
	private static final String INSERT_ORDER_ITEM="Insert into `orderitem`(`order_id`,`menu_id`,`quantity`,`subtotal`) values(?,?,?,?)";
	private static final String FETCH_ALL_ORDER_ITEMS="select * from `orderitem`";
	private static final String FETECH_ORDER_ITEM_BY_ID="select * from `orderitem` where `orderitem_id`=?";
	
	
	
	public OrderItemDaoImplementation() {
		con=DBConnector.connect();
	}
	
	

	@Override
	public int insertOrderItem(OrderItem item) {
		try {
			
			pstatement=con.prepareStatement(INSERT_ORDER_ITEM);
			pstatement.setInt(1, item.getOrder_id());
			pstatement.setInt(2, item.getMenu_id());
			pstatement.setInt(3, item.getQuantity());
			pstatement.setFloat(4, item.getSubtotal());
			
			status=pstatement.executeUpdate();
			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public List<OrderItem> getAllOrderItems() {

		
		try {
			
			statement=con.createStatement();
			result=statement.executeQuery(FETCH_ALL_ORDER_ITEMS);
			list = getdetailsofOrderItem(result);
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public OrderItem getOrderItemById(int OrderItem_id) {

		try {
			
			pstatement=con.prepareStatement(FETECH_ORDER_ITEM_BY_ID);
			pstatement.setInt(1, OrderItem_id);
			
			result=pstatement.executeQuery();
			list = getdetailsofOrderItem(result);
			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list.get(0);
	}
	
public List<OrderItem> getdetailsofOrderItem(ResultSet result) {
		
		try {
			while(result.next()) {
				
				int orderitem_id=result.getInt(1);
				int order_id=result.getInt(2);
				int menu_id=result.getInt(3);
				int quantity=result.getInt(4);
				Float subtotal=result.getFloat(5);
				
				list.add(new OrderItem(orderitem_id, order_id, menu_id, quantity, subtotal));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;	
	}
}
