package com.alkewallet.servlets;

import com.alkewallet.dao.UserDAO;
import com.alkewallet.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser != null) {
            double amount = Double.parseDouble(request.getParameter("withdrawAmount"));

            if (userDAO.withdrawFunds(currentUser.getId(), amount)) {
                currentUser.setBalance(currentUser.getBalance() - amount);
                session.setAttribute("user", currentUser);
                response.sendRedirect("balance.jsp");
            } else {
                response.sendRedirect("withdraw.jsp?error=InsufficientFunds");
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
