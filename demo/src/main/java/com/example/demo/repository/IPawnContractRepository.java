package com.example.demo.repository;

import com.example.demo.dto.PawnContractDto;

import java.util.List;

public interface IPawnContractRepository {
    List<PawnContractDto> getAll();
}
