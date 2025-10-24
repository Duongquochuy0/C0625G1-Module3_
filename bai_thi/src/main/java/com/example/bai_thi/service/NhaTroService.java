package com.example.bai_thi.service;

import com.example.bai_thi.entity.NhaTro;
import com.example.bai_thi.repository.INhaTroRepository;
import com.example.bai_thi.repository.NhaTroRepository;

import java.util.List;

public class NhaTroService implements INhaTroService{
    private INhaTroRepository nhaTroRepository = new NhaTroRepository();
    @Override
    public List<NhaTro> findAll() {
        return nhaTroRepository.findAll();
    }

    @Override
    public boolean add(NhaTro nhaTro) {
        return nhaTroRepository.add(nhaTro);
    }

    @Override
    public boolean delete(int id) {
        return nhaTroRepository.delete(id);
    }

    @Override
    public NhaTro findById(int id) {
        return nhaTroRepository.findById(id);
    }

    @Override
    public List<NhaTro> search(String maNhaTro, String name, String soDienThoai) {
        return nhaTroRepository.search(maNhaTro,name,soDienThoai);
    }

    @Override
    public NhaTro findByIdHinhThucThanhToan(int id) {
        return nhaTroRepository.findByIdHinhThucThanhToan(id);
    }

    @Override
    public boolean deleteAll(List<Integer> id) {
        return nhaTroRepository.deleteAll(id);
    }

}
