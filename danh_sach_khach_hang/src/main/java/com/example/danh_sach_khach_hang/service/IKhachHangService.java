package com.example.danh_sach_khach_hang.service;

import com.example.danh_sach_khach_hang.entity.KhachHang;

import java.util.List;

public interface IKhachHangService {
    List<KhachHang> findAll();
    boolean add(KhachHang khachHang);
}
