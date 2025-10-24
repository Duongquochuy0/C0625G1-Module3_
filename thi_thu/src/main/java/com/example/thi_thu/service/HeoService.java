package com.example.thi_thu.service;

import com.example.thi_thu.entity.Heo;
import com.example.thi_thu.repository.HeoRepository;
import com.example.thi_thu.repository.IHeoRepository;

import java.util.List;

public class HeoService implements IHeoService{
    IHeoRepository heoRepository = new HeoRepository();
    @Override
    public List<Heo> findAll() {
        return heoRepository.findAll();
    }

    @Override
    public List<Heo> search(String maHeo, String tinhTrang, String xuatXu) {
        return heoRepository.search(maHeo,tinhTrang,xuatXu);
    }

    @Override
    public Heo findById(int id) {
        return heoRepository.findById(id);
    }

    @Override
    public boolean update(Heo heo) {
        return heoRepository.update(heo);
    }

    @Override
    public boolean delete(int id) {
        return heoRepository.delete(id);
    }

    @Override
    public List<Heo> findTopHeoByTrongLuongXuat(int limit) {
        return heoRepository.findTopHeoByTrongLuongXuat(limit);
    }
}
