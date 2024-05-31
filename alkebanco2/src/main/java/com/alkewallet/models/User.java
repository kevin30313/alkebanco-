package com.alkewallet.models;


/**
 * Representa a un usuario en el sistema.
 * 
 * Cada instancia contiene información sobre un usuario específico,
 * incluyendo su identificador único, nombre de usuario, contraseña y saldo.
 */
public class User {
    private int id;          // Identificador único del usuario
    private String username; // Nombre de usuario del usuario
    private String password; // Contraseña del usuario
    private double balance;  // Saldo del usuario en su cuenta

    // Getters y setters
    // ...

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
