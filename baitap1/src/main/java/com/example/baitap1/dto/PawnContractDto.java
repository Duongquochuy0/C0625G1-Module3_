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
    private String status;

    public PawnContractDto() {}

    public PawnContractDto(int pawnContractId, String customerName, String employeeName, String productName,
                           LocalDate pawnDate, BigDecimal pawnPrice, BigDecimal interestRate,
                           LocalDate dueDate, LocalDate returnDate, String status) {
        setPawnContractId(pawnContractId);
        setCustomerName(customerName);
        setEmployeeName(employeeName);
        setProductName(productName);
        setPawnDate(pawnDate);
        setPawnPrice(pawnPrice);
        setInterestRate(interestRate);
        setDueDate(dueDate);
        setReturnDate(returnDate);
        setStatus(status);
    }

    public int getPawnContractId() {
        return pawnContractId;
    }

    public void setPawnContractId(int pawnContractId) {
        this.pawnContractId = pawnContractId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên khách hàng không được rỗng");
        }
        this.customerName = customerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        if (employeeName == null || employeeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên nhân viên không được rỗng");
        }
        this.employeeName = employeeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được rỗng");
        }
        this.productName = productName;
    }

    public LocalDate getPawnDate() {
        return pawnDate;
    }

    public void setPawnDate(LocalDate pawnDate) {
        this.pawnDate = pawnDate;
    }

    public BigDecimal getPawnPrice() {
        return pawnPrice;
    }

    public void setPawnPrice(BigDecimal pawnPrice) {
        if (pawnPrice != null && pawnPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Giá cầm phải lớn hơn 0");
        }
        this.pawnPrice = pawnPrice;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate != null && interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Lãi suất không được âm");
        }
        this.interestRate = interestRate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null && !status.matches("ACTIVE|CLOSED|LIQUIDATED")) {
            throw new IllegalArgumentException("Trạng thái phải là ACTIVE, CLOSED hoặc LIQUIDATED");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "PawnContractDto{" +
                "pawnContractId=" + pawnContractId +
                ", customerName='" + customerName + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", productName='" + productName + '\'' +
                ", pawnDate=" + pawnDate +
                ", pawnPrice=" + pawnPrice +
                ", interestRate=" + interestRate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                '}';
    }
}