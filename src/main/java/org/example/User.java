package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private String username;
    private String emailId;
    private String role;
    private String password;
    private AtomicInteger superCoins; // Use AtomicInteger

    public User(String username, String emailId, String password, int superCoins, String role) {
        this.username = username;
        this.emailId = emailId;
        this.password = password;
        this.superCoins = new AtomicInteger(superCoins); // Initialize Atomic
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSuperCoins() {
        return superCoins.get();
    }

    public void setSuperCoins(int superCoins) {
        this.superCoins.set(superCoins);
    }
}
