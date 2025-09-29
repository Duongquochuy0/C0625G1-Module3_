package com.example.danh_sach_khach_hang.service;

import com.example.danh_sach_khach_hang.entity.KhachHang;
import com.example.danh_sach_khach_hang.repository.IKhachHangRepository;
import com.example.danh_sach_khach_hang.repository.KhachHangRepository;

import java.util.List;

public class KhachHangService implements IKhachHangService {
    private IKhachHangRepository khachHangRepository =new KhachHangRepository();
    @Override
    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public boolean add(KhachHang khachHang) {
        return khachHangRepository.add(khachHang);
    }
}
