package com.example.baitap1.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PawnContract {
    private int pawnContractId;
    private int customerId;
    private int employeeId;
    private int productId;
    private LocalDate pawnDate;
    private BigDecimal pawnPrice;
    private BigDecimal interestRate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public PawnContract() {}

    public PawnContract(int pawnContractId, int customerId, int employeeId, int productId,
                        LocalDate pawnDate, BigDecimal pawnPrice, BigDecimal interestRate,
                        LocalDate dueDate, LocalDate returnDate) {
        setPawnContractId(pawnContractId);
        setCustomerId(customerId);
        setEmployeeId(employeeId);
        setProductId(productId);
        setPawnDate(pawnDate);
        setPawnPrice(pawnPrice);
        setInterestRate(interestRate);
        setDueDate(dueDate);
        setReturnDate(returnDate);
    }

    public int getPawnContractId() {
        return pawnContractId;
    }

    public void setPawnContractId(int pawnContractId) {
        this.pawnContractId = pawnContractId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Mã khách hàng phải lớn hơn 0");
        }
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("Mã nhân viên phải lớn hơn 0");
        }
        this.employeeId = employeeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Mã sản phẩm phải lớn hơn 0");
        }
        this.productId = productId;
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

    @Override
    public String toString() {
        return "PawnContract{" +
                "pawnContractId=" + pawnContractId +
                ", customerId=" + customerId +
                ", employeeId=" + employeeId +
                ", productId=" + productId +
                ", pawnDate=" + pawnDate +
                ", pawnPrice=" + pawnPrice +
                ", interestRate=" + interestRate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}