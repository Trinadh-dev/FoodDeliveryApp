package com.Food_Delivery_App.Model;

public class OrderHistory {
	
	private int orderhistory_id;
	private int order_id;
	private int user_id;
	private String orderdate;
	private Float totalamount;
	private String status;
	
	public OrderHistory() {

	}

	public OrderHistory(int orderhistory_id, int order_id, int user_id, String orderdate, Float totalamount,
			String status) {
		super();
		this.orderhistory_id = orderhistory_id;
		this.order_id = order_id;
		this.user_id = user_id;
		this.orderdate = orderdate;
		this.totalamount = totalamount;
		this.status = status;
	}

	public OrderHistory(int order_id, int user_id, String orderdate, Float totalamount, String status) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.orderdate = orderdate;
		this.totalamount = totalamount;
		this.status = status;
	}

	public int getOrderhistory_id() {
		return orderhistory_id;
	}

	public void setOrderhistory_id(int orderhistory_id) {
		this.orderhistory_id = orderhistory_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public Float getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Float totalamount) {
		this.totalamount = totalamount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderHistory [orderhistory_id=" + orderhistory_id + ", order_id=" + order_id + ", user_id=" + user_id
				+ ", orderdate=" + orderdate + ", totalamount=" + totalamount + ", status=" + status + "]";
	}
	
	
	
	

}
