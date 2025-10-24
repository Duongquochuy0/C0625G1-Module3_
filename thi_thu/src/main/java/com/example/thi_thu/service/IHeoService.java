package com.example.thi_thu.service;

import com.example.thi_thu.entity.Heo;

import java.util.List;

public interface IHeoService {
    List<Heo> findAll();
    List<Heo> search(String maHeo, String tinhTrang, String xuatXu);
    Heo findById(int id);
    boolean update(Heo heo);
    boolean delete(int id);
    List<Heo> findTopHeoByTrongLuongXuat(int limit);
}
