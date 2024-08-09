package com.Food_Delivery_App.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Food_Delivery_App.DAO.UserDao;
import com.Food_Delivery_App.Model.User;
import com.Food_Delivery_App.Uitl.DBConnector;

public class UserImplementation implements UserDao {
	
	Connection con;
	PreparedStatement pstatement;
	Statement statement;
	ResultSet result;
	int status;
	List<User> list=new ArrayList<User>();
	
	
	
	private static final String INSERT_USER="Insert into `user`(`username`,`email`,`phonenumber`,`password`,`address`) values(?,?,?,?,?)";
	private static final String FETCH_ALL_USERS="select * from `user`";
	private static final String FETECH_BY_MAIL="select * from `user` where `email`=?";
	private static final String UPDATE_DETAILS="update `user` set `username`=?,`email`=?,`phonenumber`=?,`password`=?,`address`=? where user_id=?";
	private static final String DELETE_USER="delete from `user` where `user_id`=?";
	
	
	public UserImplementation() {
		try {
			con=DBConnector.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//To insert the user into the DataBase This method is used 
	@Override
	public int insertUser(User user) {
		
		try {
			
			pstatement=con.prepareStatement(INSERT_USER);
			
			pstatement.setString(1, user.getUsername());
			pstatement.setString(2, user.getEmail());
			pstatement.setString(3, user.getPhonenumber());
			pstatement.setString(4, user.getPassword());
			pstatement.setString(5, user.getAddress());
			
			status = pstatement.executeUpdate();
			if(status==1) {
				System.out.println("Success");
			}
			else {
				System.out.println("Failure");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

	
	//To Fetech All Users this method is used 
	@Override
	public List<User> fetchAllUsers() {
		
		
		
		try {
			
			statement = con.createStatement();
			result = statement.executeQuery(FETCH_ALL_USERS);
			list=getdetailsofuser(result);
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public User fetchByMail(String email) {

		try {
			
			pstatement=con.prepareStatement(FETECH_BY_MAIL);
			pstatement.setString(1, email);
			result=pstatement.executeQuery();
			list=getdetailsofuser(result);
				
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list.get(0);
	}
	

	@Override
	public int updateDetails(User user) {
		
		try {
			
			pstatement=con.prepareStatement(UPDATE_DETAILS);
			pstatement.setString(1, user.getUsername());
			pstatement.setString(2, user.getEmail());
			pstatement.setString(3, user.getPhonenumber());
			pstatement.setString(4, user.getPassword());
			pstatement.setString(5, user.getAddress());
			pstatement.setInt(6, user.getUser_id());
			
			status=pstatement.executeUpdate();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public int delete(int userId) {
		
		try {
			
			pstatement=con.prepareStatement(DELETE_USER);
			pstatement.setInt(1, userId);
			status=pstatement.executeUpdate();
			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		

		return status;
		
	}
	
	
	
	public List<User> getdetailsofuser(ResultSet result) {
		
		try {
			while(result.next()) {
				int user_id=result.getInt(1);
				String username=result.getString(2);
				String email=result.getString(3);
				String phonenumber=result.getString(4);
				String password=result.getString(5);
				String address=result.getString(6);
				
				list.add(new User(user_id, username, password, email, address, phonenumber));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
