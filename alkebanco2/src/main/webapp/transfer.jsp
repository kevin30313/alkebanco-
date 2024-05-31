<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transfer</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="mt-4">Transfer</h2>
        <!-- Mensajes de alerta -->
        <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-danger" role="alert">
            <% if ("RecipientNotFound".equals(request.getParameter("error"))) { %>
                Usuario receptor no encontrado.
            <% } else if ("InsufficientFunds".equals(request.getParameter("error"))) { %>
                Saldo insuficiente para realizar la transferencia.
            <% } else if ("TransferFailed".equals(request.getParameter("error"))) { %>
                La transferencia de fondos ha fallado. Por favor, inténtalo de nuevo más tarde.
            <% } %>
        </div>
        <% } %>
        <!-- Formulario de transferencia -->
        <form action="transfer" method="post">
            <div class="form-group">
                <label for="recipientUsername">Recipient Username:</label>
                <input type="text" id="recipientUsername" name="recipientUsername" class="form-control">
            </div>
            <!-- Agregar campo para ingresar el ID del destinatario -->
            <div class="form-group">
                <label for="recipientId">Recipient ID:</label>
                <input type="text" id="recipientId" name="recipientId" class="form-control">
            </div>
            <div class="form-group">
                <label for="amountToTransfer">Amount to Transfer:</label>
                <input type="number" id="amountToTransfer" name="amountToTransfer" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Transfer</button>
        </form>
    </div>
    <!-- Bootstrap JavaScript (opcional, solo si necesitas funcionalidad JS de Bootstrap) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
