package com.Food_Delivery_App.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Food_Delivery_App.DAOImplementation.UserDaoImplementation;
import com.Food_Delivery_App.Model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String inputUsername = req.getParameter("username");
        String inputPassword = req.getParameter("password");

        UserDaoImplementation userdao = new UserDaoImplementation();
        User user = userdao.fetchByUserName(inputUsername);

        if (user == null) {
            req.setAttribute("errorMessage", "Mail id is not registered");
        } else if (!inputPassword.equals(user.getPassword())) {
            req.setAttribute("errorMessage", "Invalid password");
        } else if (!inputUsername.equals(user.getUsername())) {
            // Extra check to ensure the usernames match case-sensitively
            req.setAttribute("errorMessage", "Invalid username");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("username", user.getUsername());
            resp.sendRedirect(req.getContextPath() + "/home");
            return; 
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
