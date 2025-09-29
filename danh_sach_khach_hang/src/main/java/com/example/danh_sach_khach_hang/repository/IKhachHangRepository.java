package com.example.danh_sach_khach_hang.repository;

import com.example.danh_sach_khach_hang.entity.KhachHang;

import java.util.List;

public interface IKhachHangRepository {
    List<KhachHang> findAll();
    boolean add(KhachHang khachHang);
}
