package com.Food_Delivery_App.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Food_Delivery_App.DAO.RestaurantDao;
import com.Food_Delivery_App.Model.Restaurant;
import com.Food_Delivery_App.Uitl.DBConnector;

public class RestaurantDaoImplementation implements RestaurantDao {
    
    Connection con;
    PreparedStatement pstatement;
    Statement statement;
    ResultSet result;
    int status;
    List<Restaurant> list = new ArrayList<>();

    private static final String INSERT_RESTAURANT = "INSERT INTO `restaurant` (`restaurantname`, `cuisine_type`, `address`, `rating`, `isactive`) VALUES (?, ?, ?, ?, ?)";
    private static final String FETCH_ALL_RESTAURANTS = "SELECT * FROM `restaurant`";
    private static final String FETCH_BY_RESTAURANT_ID = "SELECT * FROM `restaurant` WHERE `restaurant_id` = ?";
    private static final String UPDATE_RESTAURANT = "UPDATE `restaurant` SET `restaurantname` = ?, `cuisine_type` = ?, `address` = ?, `rating` = ?, `isactive` = ? WHERE `restaurant_id` = ?";
    private static final String DELETE_RESTAURANT = "DELETE FROM `restaurant` WHERE `restaurant_id` = ?";

    public RestaurantDaoImplementation() {
        try {
            con = DBConnector.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertRestaurant(Restaurant restaurant) {
        try {
            pstatement = con.prepareStatement(INSERT_RESTAURANT);
            pstatement.setString(1, restaurant.getRestaurantname());
            pstatement.setString(2, restaurant.getCuisine_type());
            pstatement.setString(3, restaurant.getAddress());
            pstatement.setFloat(4, restaurant.getRating());
            pstatement.setBoolean(5, restaurant.isIsactive());

            status = pstatement.executeUpdate();
            if(status == 1) {
                System.out.println("Restaurant inserted successfully.");
            } else {
                System.out.println("Failed to insert restaurant.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<Restaurant> fetchAllRestaurant() {
        List<Restaurant> restaurantList = new ArrayList<>();
        try {
            statement = con.createStatement();
            result = statement.executeQuery(FETCH_ALL_RESTAURANTS);
            restaurantList = getRestaurantDetails(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantList;
    }

    @Override
    public Restaurant fetchByRestaurant_id(int restaurant_id) {
        try {
            pstatement = con.prepareStatement(FETCH_BY_RESTAURANT_ID);
            pstatement.setInt(1, restaurant_id);
            result = pstatement.executeQuery();
            list = getRestaurantDetails(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int updateRestaurant(Restaurant restaurant) {
        try {
            pstatement = con.prepareStatement(UPDATE_RESTAURANT);
            pstatement.setString(1, restaurant.getRestaurantname());
            pstatement.setString(2, restaurant.getCuisine_type());
            pstatement.setString(3, restaurant.getAddress());
            pstatement.setFloat(4, restaurant.getRating());
            pstatement.setBoolean(5, restaurant.isIsactive());
            pstatement.setInt(6, restaurant.getRestaurant_id());

            status = pstatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public int deleteRestaurant(int restaurant_id) {
        try {
            pstatement = con.prepareStatement(DELETE_RESTAURANT);
            pstatement.setInt(1, restaurant_id);
            status = pstatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    private List<Restaurant> getRestaurantDetails(ResultSet result) {
        try {
            while(result.next()) {
                int restaurant_id = result.getInt(1);
                String restaurantname = result.getString(2);
                String cuisine_type = result.getString(3);
                String address = result.getString(4);
                float rating = result.getFloat(5);
                boolean isactive = result.getBoolean(6);
                byte[] imagepath = result.getBytes(7); // Assuming the image is stored in the 7th column
                list.add(new Restaurant(restaurant_id, restaurantname, cuisine_type, address, rating, isactive, imagepath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
