package com.example.baitap1.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PawnContractDto {
    private int pawnContractId;
    private String customerName;
    private String employeeName;
    private String productName;
    private LocalDate pawnDate;
    private BigDecimal pawnPrice;
    private BigDecimal interestRate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    // Thêm thuộc tính trạng thái hợp đồng
    private String contractStatus;

    // Constructor mặc định
    public PawnContractDto() {}

    // Constructor đầy đủ (có contractStatus)
    public PawnContractDto(int pawnContractId, String customerName, String employeeName, String productName,
                           LocalDate pawnDate, BigDecimal pawnPrice, BigDecimal interestRate,
                           LocalDate dueDate, LocalDate returnDate, String contractStatus) {
        this.pawnContractId = pawnContractId;
        this.customerName = customerName;
        this.employeeName = employeeName;
        this.productName = productName;
        this.pawnDate = pawnDate;
        this.pawnPrice = pawnPrice;
        this.interestRate = interestRate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.contractStatus = contractStatus;
    }

    // Getter & Setter
    public int getPawnContractId() { return pawnContractId; }
    public void setPawnContractId(int pawnContractId) { this.pawnContractId = pawnContractId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public LocalDate getPawnDate() { return pawnDate; }
    public void setPawnDate(LocalDate pawnDate) { this.pawnDate = pawnDate; }

    public BigDecimal getPawnPrice() { return pawnPrice; }
    public void setPawnPrice(BigDecimal pawnPrice) { this.pawnPrice = pawnPrice; }

    public BigDecimal getInterestRate() { return interestRate; }
    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public String getContractStatus() { return contractStatus; }
    public void setContractStatus(String contractStatus) { this.contractStatus = contractStatus; }
}
