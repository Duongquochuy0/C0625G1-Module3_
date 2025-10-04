package com.example.baitap1.service;

import com.example.baitap1.dto.PawnContractDto;
import com.example.baitap1.entity.PawnContract;

import java.util.List;

public interface IPawnContractService {
    List<PawnContract> findAll();
    List<PawnContractDto> findAllDto();
    boolean save(PawnContract pawnContract);
    boolean update(PawnContract pawnContract);
    boolean delete(int pawnContractId);
    List<PawnContractDto> search(String customerName, String employeeName, String productName);
}
