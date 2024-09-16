package com.Food_Delivery_App.Controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Food_Delivery_App.DAOImplementation.MenuDaoImplementation;
import com.Food_Delivery_App.DAOImplementation.RestaurantDaoImplementation;
import com.Food_Delivery_App.Model.Menu;
import com.Food_Delivery_App.Model.Restaurant;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type"); // Use 'type' to differentiate between restaurant and menu images
        String idParam = request.getParameter("id"); // Use 'id' as the identifier (restaurantId or menuId)

        if (type == null || idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing type or id parameter");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id parameter");
            return;
        }

        byte[] image = null; // Image to be retrieved
        String contentType = "image/jpeg"; // Default content type

        if ("restaurant".equalsIgnoreCase(type)) {
            // Fetch restaurant image
            RestaurantDaoImplementation restaurantDao = new RestaurantDaoImplementation();
            Restaurant restaurant = restaurantDao.fetchByRestaurant_id(id);
            if (restaurant != null && restaurant.getImage() != null) {
                image = restaurant.getImage();
                contentType = "image/jpeg";
            }
        } else if ("menu".equalsIgnoreCase(type)) {
            // Fetch menu image
            MenuDaoImplementation menuDao = new MenuDaoImplementation();
            Menu menu = menuDao.fetchByMenuId(id);
            if (menu != null && menu.getImagepath() != null) {
                image = menu.getImagepath();
                contentType = "image/jpeg";
            }
        }

        if (image != null) {
            response.setContentType(contentType);
            response.setContentLength(image.length);

            try (OutputStream out = response.getOutputStream()) {
                out.write(image);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
        }
    }
}
