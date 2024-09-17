package com.Food_Delivery_App.Model;

import java.util.Arrays;

public class CartItem {
	
	private int item_id;
	private int restaurant_id;
	private String name;
	private float price;
	private int quantity;
	private float subtotal;
	private byte[] imagePath;
	
	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	

	public CartItem(int item_id, int restaurant_id, String name, float price, int quantity, float subtotal,
			byte[] imagePath) {
		super();
		this.item_id = item_id;
		this.restaurant_id = restaurant_id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.imagePath = imagePath;
	}



	public byte[] getImagePath() {
		return imagePath;
	}

	public void setImagePath(byte[] imagePath) {
		this.imagePath = imagePath;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "CartItem [item_id=" + item_id + ", restaurant_id=" + restaurant_id + ", name=" + name + ", price="
				+ price + ", quantity=" + quantity + ", subtotal=" + subtotal + ", imagePath="
				+ Arrays.toString(imagePath) + "]";
	}

	
	
	
	
	
	

}
