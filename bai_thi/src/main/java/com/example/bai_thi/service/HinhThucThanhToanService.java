package com.example.bai_thi.service;

import com.example.bai_thi.entity.HinhThucThanhToan;
import com.example.bai_thi.repository.HinhThucThanhToanRepository;
import com.example.bai_thi.repository.IHinhThucThanhToanRepository;

import java.util.List;

public class HinhThucThanhToanService implements IHinhThucThanhToanService{
    IHinhThucThanhToanRepository hinhThucThanhToanRepository = new HinhThucThanhToanRepository();
    @Override
    public List<HinhThucThanhToan> findAll() {
        return hinhThucThanhToanRepository.findAll();
    }
}
