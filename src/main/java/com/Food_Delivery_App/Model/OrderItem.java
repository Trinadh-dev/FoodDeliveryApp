
package com.Food_Delivery_App.Model;

public class OrderItem {
	
	private int orderitem_id;
	private int order_id;
	private int menu_id;
	private int quantity;
	private Float subtotal;
	
	
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}


	public OrderItem(int orderitem_id, int order_id, int menu_id, int quantity, Float subtotal) {
		super();
		this.orderitem_id = orderitem_id;
		this.order_id = order_id;
		this.menu_id = menu_id;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}


	public OrderItem(int order_id, int menu_id, int quantity, Float subtotal) {
		super();
		this.order_id = order_id;
		this.menu_id = menu_id;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}


	public int getOrderitem_id() {
		return orderitem_id;
	}


	public void setOrderitem_id(int orderitem_id) {
		this.orderitem_id = orderitem_id;
	}


	public int getOrder_id() {
		return order_id;
	}


	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}


	public int getMenu_id() {
		return menu_id;
	}


	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Float getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}


	@Override
	public String toString() {
		return "OrderItem [orderitem_id=" + orderitem_id + ", order_id=" + order_id + ", menu_id=" + menu_id
				+ ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}
	
	
	
	

}
