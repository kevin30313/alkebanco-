<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Withdraw Amount</title>
</head>
<body>
    <h2>Withdraw Funds</h2>
    <form action="withdraw" method="post">
        <label for="withdrawAmount">Amount:</label>
        <input type="number" id="withdrawAmount" name="withdrawAmount" step="0.01"><br><br>
        <input type="submit" value="Withdraw">
    </form>
</body>
</html>
