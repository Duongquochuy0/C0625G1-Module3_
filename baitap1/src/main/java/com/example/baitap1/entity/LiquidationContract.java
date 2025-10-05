package com.example.baitap1.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LiquidationContract {
    private int liquidationContractId;
    private int employeeId;
    private int productId;
    private Integer customerId; // có thể NULL
    private LocalDate liquidationDate;
    private BigDecimal price;

    public LiquidationContract() {}

    public LiquidationContract(int liquidationContractId, int employeeId, int productId,
                               Integer customerId, LocalDate liquidationDate, BigDecimal price) {
        this.liquidationContractId = liquidationContractId;
        this.employeeId = employeeId;
        this.productId = productId;
        this.customerId = customerId;
        this.liquidationDate = liquidationDate;
        this.price = price;
    }

    // Getters and Setters
    public int getLiquidationContractId() { return liquidationContractId; }
    public void setLiquidationContractId(int liquidationContractId) { this.liquidationContractId = liquidationContractId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public LocalDate getLiquidationDate() { return liquidationDate; }
    public void setLiquidationDate(LocalDate liquidationDate) { this.liquidationDate = liquidationDate; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
