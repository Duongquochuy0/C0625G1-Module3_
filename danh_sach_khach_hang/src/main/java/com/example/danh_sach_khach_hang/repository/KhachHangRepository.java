package com.example.danh_sach_khach_hang.repository;

import com.example.danh_sach_khach_hang.entity.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangRepository implements IKhachHangRepository {
    private static List<KhachHang> khachHangList = new ArrayList<>();
    static {
        khachHangList.add(new KhachHang(1,"Dương Quốc Huy","03-11-2001","Huế","images/huy.jfif"));
        khachHangList.add(new KhachHang(2,"Nguyễn Nhật Thành","03-11-2002","Quảng Trị","images/athanh.jfif"));
        khachHangList.add(new KhachHang(3,"Nguyễn Đăng Thiên An","03-11-2003","Đà Nẵng","images/an.jfif"));
        khachHangList.add(new KhachHang(4,"Nguyễn Ngọc Thới","03-11-2004","Đà Nẵng","images/aThoi.jfif"));
    }
    @Override
    public List<KhachHang> findAll() {
        return khachHangList;
    }

    @Override
    public boolean add(KhachHang khachHang) {
        return khachHangList.add(khachHang);
    }
}
