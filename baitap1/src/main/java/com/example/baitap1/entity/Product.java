package com.example.baitap1.entity;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String productName;
    private String description;
    private BigDecimal pawnPrice;
    private String status;

    public Product() {}

    public Product(int productId, String productName, String description, BigDecimal pawnPrice, String status) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.pawnPrice = pawnPrice;
        this.status = status;
    }

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPawnPrice() { return pawnPrice; }
    public void setPawnPrice(BigDecimal pawnPrice) { this.pawnPrice = pawnPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
