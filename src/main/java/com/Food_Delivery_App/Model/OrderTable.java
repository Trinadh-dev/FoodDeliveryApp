package com.Food_Delivery_App.Model;

public class OrderTable {
	
	private int order_id;
	private int restaurant_id;
	private int user_id;
	private String orderdate;
	private Float totalamount;
	private String status;
	private String paymentmode;
	
	public OrderTable() {
		// TODO Auto-generated constructor stub
	}

	public OrderTable(int order_id, int restaurant_id, int user_id, String orderdate, Float totalamount, String status,
			String paymentmode) {
		super();
		this.order_id = order_id;
		this.restaurant_id = restaurant_id;
		this.user_id = user_id;
		this.orderdate = orderdate;
		this.totalamount = totalamount;
		this.status = status;
		this.paymentmode = paymentmode;
	}

	public OrderTable(int restaurant_id, int user_id, String orderdate, Float totalamount, String status,
			String paymentmode) {
		super();
		this.restaurant_id = restaurant_id;
		this.user_id = user_id;
		this.orderdate = orderdate;
		this.totalamount = totalamount;
		this.status = status;
		this.paymentmode = paymentmode;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
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

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	@Override
	public String toString() {
		return "OrderTable [order_id=" + order_id + ", restaurant_id=" + restaurant_id + ", user_id=" + user_id
				+ ", orderdate=" + orderdate + ", totalamount=" + totalamount + ", status=" + status + ", paymentmode="
				+ paymentmode + "]";
	}
	
	

}
