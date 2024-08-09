package com.Food_Delivery_App.DAO;

import java.util.List;

import com.Food_Delivery_App.Model.User;


public interface UserDao {
	int insertUser(User user);
	List<User> fetchAllUsers();
	User fetchByMail(String email);//find user by email
	int updateDetails(User user);
	int delete(int userId);
}
