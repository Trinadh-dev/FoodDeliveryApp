package com.Food_Delivery_App.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Food_Delivery_App.DAOImplementation.UserDaoImplementation;
import com.Food_Delivery_App.Model.User;

@WebServlet("/Register")
public class UserRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phonenumber = request.getParameter("phonenumber");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String address = request.getParameter("address");

        if (password.equals(confirmPassword)) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPhonenumber(phonenumber);
            user.setPassword(password);
            user.setAddress(address);

            UserDaoImplementation userDao = new UserDaoImplementation();
            int status = userDao.insertUser(user);

            if (status == 1) {
                response.sendRedirect("index.jsp?status=success");
            } else {
                response.sendRedirect("index.jsp?status=error");
            }
        } else {
            response.sendRedirect("index.jsp?status=invalidPassword");
        }
    }
}
