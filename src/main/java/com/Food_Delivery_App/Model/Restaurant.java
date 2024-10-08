package com.Food_Delivery_App.Model;

public class Restaurant {
	
	
	private int restaurant_id;


	private String restaurantname;
	private String cuisine_type;
	private String address;
	private Float rating;
	private boolean isactive;
	private byte[] imagepath;
	
	public Restaurant() {
		// TODO Auto-generated constructor stub
	}
	
	public Restaurant(int restaurant_id, String restaurantname, String cuisine_type, String address, Float rating,
			boolean isactive, byte[] imagepath) {
		super();
		this.restaurant_id = restaurant_id;
		this.restaurantname = restaurantname;
		this.cuisine_type = cuisine_type;
		this.address = address;
		this.rating = rating;
		this.isactive = isactive;
		this.imagepath = imagepath;
	}

	public Restaurant(String restaurantname, String cuisine_type, String address, Float rating, boolean isactive,
			byte[] imagepath) {
		super();
		this.restaurantname = restaurantname;
		this.cuisine_type = cuisine_type;
		this.address = address;
		this.rating = rating;
		this.isactive = isactive;
		this.imagepath = imagepath;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getRestaurantname() {
		return restaurantname;
	}

	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}

	public String getCuisine_type() {
		return cuisine_type;
	}

	public void setCuisine_type(String cuisine_type) {
		this.cuisine_type = cuisine_type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public byte[] getImage() {
        return imagepath;
    }

    public void setImage(byte[] imagepath) {
        this.imagepath = imagepath;
    }

	@Override
	public String toString() {
		return "Restaurant [restaurant_id=" + restaurant_id + ", restaurantname=" + restaurantname + ", cuisine_type="
				+ cuisine_type + ", address=" + address + ", rating=" + rating + ", isactive=" + isactive
				+ ", imagepath=" + imagepath + "]";
	}

	

	
}
