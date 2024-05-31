package com.alkewallet.dao;

import java.sql.*;

import com.alkewallet.models.User;

/**
 * Data Access Object (DAO) para la entidad User.
 * 
 * Proporciona métodos para interactuar con la tabla 'users' en la base de datos,
 * incluyendo operaciones como inicio de sesión, obtención de saldo, transferencia
 * de fondos y retiro de fondos.
 */
public class UserDAO {

    // Configuración de conexión a la base de datos
    private String jdbcURL = "jdbc:mysql://localhost:3306/wallet_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "C0m0D3b3S3r!";

    // Consultas SQL para operaciones CRUD
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT id, username, password, balance FROM users WHERE username = ? AND password = ?";
    private static final String SELECT_BALANCE_BY_USERNAME = "SELECT balance FROM users WHERE username = ?";
    private static final String SELECT_ID_BY_USERNAME = "SELECT id FROM users WHERE username = ?";
    private static final String UPDATE_BALANCE = "UPDATE users SET balance = ? WHERE id = ?";
    private static final String GET_BALANCE_BY_ID = "SELECT balance FROM users WHERE id = ?";
    

    /**
     * Establece una conexión a la base de datos.
     * 
     * @return La conexión establecida.
     */
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Realiza el inicio de sesión de un usuario.
     * 
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return Objeto User si el inicio de sesión es exitoso, null de lo contrario.
     */
    public User login(String username, String password) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    

    public double getBalanceByUsername(String username) {
        double balance = 0.0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BALANCE_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public int getIdByUsername(String username) {
        int id = -1;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean transferFunds(int senderId, int recipientId, double amount) {
        boolean success = false;
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            double senderBalance = getBalanceById(senderId);
            double recipientBalance = getBalanceById(recipientId);

            if (senderBalance >= amount) {
                updateBalance(senderId, senderBalance - amount, connection);
                updateBalance(recipientId, recipientBalance + amount, connection);
                connection.commit();
                success = true;
            } else {
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean withdrawFunds(int userId, double amount) {
        boolean success = false;
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            double balance = getBalanceById(userId);

            if (balance >= amount) {
                updateBalance(userId, balance - amount, connection);
                connection.commit();
                success = true;
            } else {
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    private double getBalanceById(int userId) {
        double balance = 0.0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BALANCE_BY_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    private void updateBalance(int userId, double newBalance, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BALANCE)) {
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        }
    }

    public User getUserByUsername(String username) {
        User user = null;
        String SELECT_USER_BY_USERNAME = "SELECT id, username, password, balance FROM users WHERE username = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
