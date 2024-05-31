package com.alkewallet.servlets;

import com.alkewallet.dao.UserDAO;
import com.alkewallet.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class TransferServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser != null) {
            String recipientUsername = request.getParameter("recipientUsername");
            double amountToTransfer = Double.parseDouble(request.getParameter("amountToTransfer"));

            // Verificar si el usuario receptor existe en la base de datos
            User recipientUser = userDAO.getUserByUsername(recipientUsername);
            if (recipientUser != null) {
                // Verificar si el saldo es suficiente para la transferencia
                if (currentUser.getBalance() >= amountToTransfer) {
                    // Realizar la transferencia de fondos
                    if (userDAO.transferFunds(currentUser.getId(), recipientUser.getId(), amountToTransfer)) {
                        // Actualizar los balances en la sesión
                        currentUser.setBalance(currentUser.getBalance() - amountToTransfer);
                        recipientUser.setBalance(recipientUser.getBalance() + amountToTransfer);
                        session.setAttribute("user", currentUser);
                        session.setAttribute("recipientUser", recipientUser);
                        response.sendRedirect("balance.jsp");
                    } else {
                        // Error de transferencia
                        response.sendRedirect("transfer.jsp?error=TransferFailed");
                    }
                } else {
                    // Saldo insuficiente
                    response.sendRedirect("transfer.jsp?error=InsufficientFunds");
                }
            } else {
                // Usuario receptor no encontrado
                response.sendRedirect("transfer.jsp?error=RecipientNotFound");
            }
        } else {
            // Usuario no autenticado, redirigir a la página de inicio de sesión
            response.sendRedirect("index.jsp");
        }
    }
}
