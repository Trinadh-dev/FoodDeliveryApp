package com.Food_Delivery_App.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Food_Delivery_App.DAOImplementation.MenuDaoImplementation;
import com.Food_Delivery_App.DAOImplementation.RestaurantDaoImplementation;
import com.Food_Delivery_App.Model.Menu;
import com.Food_Delivery_App.Model.Restaurant;


@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		MenuDaoImplementation menudao = new MenuDaoImplementation();
		List<Menu> menulist = menudao.fetchByRestaurantId(restaurantId);
		RestaurantDaoImplementation Restaurant = new RestaurantDaoImplementation();
		Restaurant  restaurant = Restaurant.fetchByRestaurant_id(restaurantId);
		HttpSession session = req.getSession();
		session.setAttribute("menuList", menulist);
		session.setAttribute("RestaurantName", restaurant.getRestaurantname());

		// Forward to Home.jsp
		req.getRequestDispatcher("MenuPage.jsp").forward(req, resp);


	}
}
