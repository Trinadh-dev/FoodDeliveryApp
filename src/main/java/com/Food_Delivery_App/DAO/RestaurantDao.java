package com.Food_Delivery_App.DAO;

import java.util.List;

import com.Food_Delivery_App.Model.Restaurant;
import com.Food_Delivery_App.Model.User;

public interface RestaurantDao {
	
	int insertRestaurant(Restaurant restaurant);
	List<Restaurant> fetchAllRestaurant();
	Restaurant fetchByRestaurant_id(int restaurant_id);//find user by email
	int updateRestaurant(Restaurant restaurant);
	int deleteRestaurant(int restaurant_id);
	
}
