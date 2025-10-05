package com.example.demo.service;

import com.example.demo.dto.PawnContractDto;
import com.example.demo.repository.IPawnContractRepository;
import com.example.demo.repository.PawnContractRepository;

import java.util.List;

public class PawnContractService implements IPawnContractService{
    private IPawnContractRepository repository;

    public PawnContractService() {
        this.repository = new PawnContractRepository(); // Dùng implementation JDBC
    }

    @Override
    public List<PawnContractDto> getAllContracts() {
        return repository.getAll();
    }
}
