package com.Food_Delivery_App.DAO;

import java.util.List;

import com.Food_Delivery_App.Model.Menu;

public interface MenuDao {
    int insertMenu(Menu menu);
    List<Menu> fetchAllMenus();
    Menu fetchByMenuId(int Menu_id);
    List<Menu> fetchByRestaurantId(int restaurant_id);
    int updateMenu(Menu menu);
    int deleteMenu(int menuId);
}
