package com.Food_Delivery_App.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Food_Delivery_App.DAOImplementation.MenuDaoImplementation;
import com.Food_Delivery_App.Model.Cart;
import com.Food_Delivery_App.Model.CartItem;
import com.Food_Delivery_App.Model.Menu;


@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addItemToCart(request, cart);
        } else if ("update".equals(action)) {
            updateCart(request, cart);
        } else if ("remove".equals(action)) {
            removeItemFromCart(request, cart);
        }

        session.setAttribute("cart", cart);

        if (request.getHeader("X-Requested-With") != null) {
            // Handle AJAX request
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}");
        } else {
            response.sendRedirect("Cart.jsp");
        }
    }

    public void addItemToCart(HttpServletRequest request, Cart cart) {
        int item_id = Integer.parseInt(request.getParameter("item_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        MenuDaoImplementation menu = new MenuDaoImplementation();
        Menu menuItem = menu.fetchByMenuId(item_id);

        if (menuItem != null) {
            CartItem item = new CartItem(
                    menuItem.getMenu_id(),
                    menuItem.getRestaurant_id(),
                    menuItem.getMenuname(),
                    menuItem.getPrice(),
                    quantity,
                    quantity * menuItem.getPrice()
            );

            cart.Add(item);
        }
    }

    private void updateCart(HttpServletRequest request, Cart cart) {
        int item_id = Integer.parseInt(request.getParameter("item_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        cart.updateItem(item_id, quantity);
    }

    public void removeItemFromCart(HttpServletRequest request, Cart cart) {
        int item_id = Integer.parseInt(request.getParameter("item_id"));
        cart.removeItem(item_id);
    }
}
