package com.example.bai_thi.service;

import com.example.bai_thi.entity.NhaTro;

import java.util.List;

public interface INhaTroService {
    List<NhaTro> findAll();
    boolean add(NhaTro nhaTro);
    boolean delete(int id);
    NhaTro findById(int id);
    List<NhaTro> search(String maNhaTro, String name, String soDienThoai);
    NhaTro findByIdHinhThucThanhToan(int id);
    boolean deleteAll(List<Integer> id);
}
