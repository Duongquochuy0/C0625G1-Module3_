package com.example.bai_thi.repository;



import com.example.bai_thi.entity.HinhThucThanhToan;

import java.util.List;

public interface IHinhThucThanhToanRepository {
    List<HinhThucThanhToan> findAll();
}
