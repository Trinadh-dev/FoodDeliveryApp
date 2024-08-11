package com.Food_Delivery_App.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Food_Delivery_App.DAOImplementation.RestaurantDaoImplementation;
import com.Food_Delivery_App.Model.Restaurant;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        RestaurantDaoImplementation restaurantDao = new RestaurantDaoImplementation();
        List<Restaurant> restaurantList = restaurantDao.fetchAllRestaurant();
        
        // Use session to store the restaurant list
        HttpSession session = request.getSession();
        session.setAttribute("restaurantList", restaurantList);

        // Redirect to Home.jsp
        resp.sendRedirect("Home.jsp");
    }
}
