package com.Food_Delivery_App.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Food_Delivery_App.DAO.MenuDao;
import com.Food_Delivery_App.Model.Menu;
import com.Food_Delivery_App.Uitl.DBConnector;

public class MenuDaoImplementation implements MenuDao {

    Connection con;
    PreparedStatement pstatement;
    Statement statement;
    ResultSet result;
    int status;
    List<Menu> list = new ArrayList<>();

    private static final String INSERT_MENU = "INSERT INTO `menu`(`restaurant_id`, `menuname`, `price`, `description`, `isavailable`, `imagepath`) VALUES(?,?,?,?,?,?)";
    private static final String FETCH_ALL_MENUS = "SELECT * FROM `menu`";
    private static final String FETCH_BY_RESTAURANT_ID = "SELECT * FROM `menu` WHERE `restaurant_id`=?";
    private static final String FETCH_BY_Menu_ID = "SELECT * FROM `menu` WHERE `menu_id`=?";
    private static final String UPDATE_MENU = "UPDATE `menu` SET `restaurant_id`=?, `menuname`=?, `price`=?, `description`=?, `isavailable`=?, `imagepath`=? WHERE `menu_id`=?";
    private static final String DELETE_MENU = "DELETE FROM `menu` WHERE `menu_id`=?";

    public MenuDaoImplementation() {
        try {
            con = DBConnector.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertMenu(Menu menu) {
        try {
            pstatement = con.prepareStatement(INSERT_MENU);
            pstatement.setInt(1, menu.getRestaurant_id());
            pstatement.setString(2, menu.getMenuname());
            pstatement.setFloat(3, menu.getPrice());
            pstatement.setString(4, menu.getDescription());
            pstatement.setBoolean(5, menu.isIsavailable());
            pstatement.setBytes(6, menu.getImagepath()); // Adding image

            status = pstatement.executeUpdate();
            if (status == 1) {
                System.out.println("Menu inserted successfully");
            } else {
                System.out.println("Menu insertion failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Menu> fetchAllMenus() {
        try {
            statement = con.createStatement();
            result = statement.executeQuery(FETCH_ALL_MENUS);
            list = getMenusFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Menu fetchByMenuId(int menu_id) {
        Menu menu = null;
        try {
            pstatement = con.prepareStatement(FETCH_BY_Menu_ID);
            pstatement.setInt(1, menu_id);
            result = pstatement.executeQuery();
            list = getMenusFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Menu> fetchByRestaurantId(int restaurant_id) {
        try {
            pstatement = con.prepareStatement(FETCH_BY_RESTAURANT_ID);
            pstatement.setInt(1, restaurant_id);
            result = pstatement.executeQuery();
            list = getMenusFromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int updateMenu(Menu menu) {
        try {
            pstatement = con.prepareStatement(UPDATE_MENU);
            pstatement.setInt(1, menu.getRestaurant_id());
            pstatement.setString(2, menu.getMenuname());
            pstatement.setFloat(3, menu.getPrice());
            pstatement.setString(4, menu.getDescription());
            pstatement.setBoolean(5, menu.isIsavailable());
            pstatement.setBytes(6, menu.getImagepath()); // Adding image to update
            pstatement.setInt(7, menu.getMenu_id());

            status = pstatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteMenu(int menuId) {
        try {
            pstatement = con.prepareStatement(DELETE_MENU);
            pstatement.setInt(1, menuId);
            status = pstatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private List<Menu> getMenusFromResultSet(ResultSet resultSet) throws SQLException {
        List<Menu> menus = new ArrayList<>();
        while (resultSet.next()) {
            menus.add(getMenuFromResultSet(resultSet));
        }
        return menus;
    }

    private Menu getMenuFromResultSet(ResultSet resultSet) throws SQLException {
        int menu_id = resultSet.getInt("menu_id");
        int restaurant_id = resultSet.getInt("restaurant_id");
        String menuname = resultSet.getString("menuname");
        float price = resultSet.getFloat("price");
        String description = resultSet.getString("description");
        boolean isavailable = resultSet.getBoolean("isavailable");
        byte[] imagepath = resultSet.getBytes("imagepath"); // Fetch image data

        return new Menu(menu_id, restaurant_id, menuname, price, description, isavailable, imagepath);
    }
}
