<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Balance</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/balancestyles.css">
</head>
<body>
    <div id="custom-balance-container" class="container mt-5">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-header">
                        <h2 class="text-center">Balance</h2>
                    </div>
                    <div class="card-body">
                        <h4 class="text-center">Welcome, ${sessionScope.user.username}</h4>
                        <hr>
                        <div class="text-center">
                            <h5>Your Balance: ${requestScope.balance}</h5>
                        </div>
                    </div>
                    <div class="card-footer text-muted text-center">
    <!-- Botones -->
                      <a href="transfer.jsp" class="btn btn-custom">Transfer Funds</a>
                      <a href="withdraw.jsp" class="btn btn-custom">Withdraw Funds</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap JavaScript (opcional, solo si necesitas funcionalidad JS de Bootstrap) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
