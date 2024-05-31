package com.alkewallet.servlets;

import com.alkewallet.dao.UserDAO;
import com.alkewallet.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userDAO.login(username, password);

        if (user != null) {
            // Si el usuario existe, se crea una sesión y se establece el usuario como atributo de sesión
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            // Redirige al usuario a la página de balance
            response.sendRedirect("balance");
        } else {
            // Si el usuario no es encontrado, se redirige al usuario de vuelta a la página de inicio de sesión con un mensaje de error
            response.sendRedirect("index.jsp?error=UserNotFound");
        }
    }
}
