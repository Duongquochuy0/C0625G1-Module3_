package com.example.baitap1.entity;

public class Account {
    private int accountId;
    private String username;
    private String passwordHash;
    private String role; // ADMIN, STAFF, USER

    // Constructors
    public Account() {}

    public Account(int accountId, String username, String passwordHash, String role) {
        this.accountId = accountId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Getters and Setters
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
