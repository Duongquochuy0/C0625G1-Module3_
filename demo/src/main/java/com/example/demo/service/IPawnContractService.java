package com.example.demo.service;

import com.example.demo.dto.PawnContractDto;

import java.util.List;

public interface IPawnContractService {
    List<PawnContractDto> getAllContracts();
}
