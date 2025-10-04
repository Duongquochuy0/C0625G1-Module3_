package com.example.baitap1.service;

import com.example.baitap1.dto.PawnContractDto;
import com.example.baitap1.entity.PawnContract;
import com.example.baitap1.repository.IPawnContractRepository;
import com.example.baitap1.repository.PawnContractRepository;

import java.util.List;

public class PawnContractService implements IPawnContractService {

    private final IPawnContractRepository pawnContractRepository = new PawnContractRepository();

    @Override
    public List<PawnContract> findAll() {
        return pawnContractRepository.findAll();
    }

    @Override
    public List<PawnContractDto> findAllDto() {
        return pawnContractRepository.findAllDto();
    }

    @Override
    public boolean save(PawnContract pawnContract) {
        return pawnContractRepository.save(pawnContract);
    }

    @Override
    public boolean update(PawnContract pawnContract) {
        return pawnContractRepository.update(pawnContract);
    }

    @Override
    public boolean delete(int pawnContractId) {
        return pawnContractRepository.delete(pawnContractId);
    }

    @Override
    public List<PawnContractDto> search(String customerName, String employeeName, String productName) {
        return pawnContractRepository.search(customerName, employeeName, productName);
    }
}
