package com.example.baitap1.repository;

import com.example.baitap1.dto.PawnContractDto;
import com.example.baitap1.entity.PawnContract;

import java.util.List;

public interface IPawnContractRepository {
    List<PawnContractDto> findAll();
    boolean add(PawnContract pawnContract);
    boolean delete(int id);
    boolean update(PawnContract pawnContract);
    PawnContractDto findById(int id);
    List<PawnContractDto> search(String customerName, String employeeName, String productName);
    PawnContract findByIdProduct(int id);
    List<PawnContractDto> findAll(int offset, int limit);
    int countPawnContract();
}