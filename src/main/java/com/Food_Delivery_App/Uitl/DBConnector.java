package com.Food_Delivery_App.Uitl;

import java.sql.Connection;
import java.sql.DriverManager;


final public class DBConnector {


	public DBConnector() {

	} 
	
	//this code is a model of simpletoDesign pattern 
	
//	static DBConnector connection= new DBConnector();	
//	public static DBConnector getConnection(){
//
//		return connection;
//	}

	public static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/fooddeliveryapp","root","root");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}




}
