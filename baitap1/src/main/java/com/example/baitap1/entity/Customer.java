package com.example.baitap1.entity;

import java.time.LocalDate;

public class Customer {
    private int customerId;
    private int accountId;
    private String fullName;
    private String citizenNumber;
    private String phoneNumber;
    private String address;
    private String email;
    private LocalDate dob;

    // Constructors
    public Customer() {}

    public Customer(int customerId, int accountId, String fullName, String citizenNumber, String phoneNumber,
                    String address, String email, LocalDate dob) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.fullName = fullName;
        this.citizenNumber = citizenNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.dob = dob;
    }

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getCitizenNumber() { return citizenNumber; }
    public void setCitizenNumber(String citizenNumber) { this.citizenNumber = citizenNumber; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
}
