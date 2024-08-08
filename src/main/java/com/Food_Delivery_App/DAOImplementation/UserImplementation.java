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
	
	
	
	private static final String INSERT_USER="Insert into `user`(`username`,`email`,`phonenumber`,`password`,`address`) values(?,?,?,?,?)";
	private static final String FETCH_ALL_USERS="select * from user";
	
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
			
			int status = pstatement.executeUpdate();
			if(status==1) {
				System.out.println("Success");
			}
			else {
				System.out.println("Failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	//To Fetech All Users this method is used 
	@Override
	public List<User> fetchAllUsers() {
		
		
		
		try {
			statement = con.createStatement();
			result = statement.executeQuery(FETCH_ALL_USERS);
			
			List<User> list=new ArrayList<User>();
			
			while(result.next()) {
				int user_id=result.getInt(1);
				String username=result.getString(2);
				String email=result.getString(3);
				String phonenumber=result.getString(4);
				String password=result.getString(5);
				String address=result.getString(6);
				
				list.add(new User(user_id, username, password, email, address, phonenumber));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public User fetchByMail(int mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePassword(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
