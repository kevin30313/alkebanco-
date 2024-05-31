package com.alkewallet.servlets;

import com.alkewallet.models.User;
import com.alkewallet.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class BalanceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

   
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            // Obtiene el saldo del usuario desde la base de datos
            double balance = userDAO.getBalanceByUsername(user.getUsername());
            
            // Establece el saldo como un atributo de solicitud para ser mostrado en la página JSP
            request.setAttribute("balance", balance);

            // Redirige la solicitud al archivo JSP de balance para mostrar el saldo
            RequestDispatcher dispatcher = request.getRequestDispatcher("balance.jsp");
            dispatcher.forward(request, response);
        } else {
            // Si el usuario no está autenticado, redirige al usuario a la página de inicio de sesión
            response.sendRedirect("index.jsp");
        }
    }
}
