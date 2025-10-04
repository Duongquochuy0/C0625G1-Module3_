package com.example.baitap1;

import com.example.baitap1.dto.PawnContractDto;
import com.example.baitap1.repository.PawnContractRepository;

import java.util.List;

public class main {
    public static void main(String[] args) {
        PawnContractRepository repo = new PawnContractRepository();
        List<PawnContractDto> contracts = repo.findAllDto();
        System.out.println("Số hợp đồng: " + contracts.size());
        contracts.forEach(System.out::println);
    }
}
