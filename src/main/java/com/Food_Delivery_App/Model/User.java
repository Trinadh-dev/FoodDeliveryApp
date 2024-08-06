package com.Food_Delivery_App.Model;



//Pojo class for User Table 

public class User {
	private int user_id;
	private String username;
	private String password;
	private String email;
	private String address;
	private String phonenumber;

	public User() { }

	public User(int user_id, String username, String password, String email, String address, String phonenumber) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phonenumber = phonenumber;
	}

	public User(String username, String password, String email, String address, String phonenumber) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phonenumber = phonenumber;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address + ", phonenumber=" + phonenumber + "]";
	}

	
}
