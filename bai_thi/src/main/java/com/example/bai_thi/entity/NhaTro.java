package com.example.bai_thi.entity;

import java.time.LocalDate;

public class NhaTro {
    private int id;
    private String maNhaTro;
    private String ten;
    private String soDienThoai;
    private LocalDate ngayBatDauThue;
    private String hinhThucThanhToan;
    private String ghiChu;
    private int hinhThucThanhToanID;

    public NhaTro() {
    }

    public NhaTro(int id, String ten, String soDienThoai, LocalDate ngayBatDauThue, String hinhThucThanhToan, String ghiChu, int hinhThucThanhToanID) {
        this.id = id;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauThue = ngayBatDauThue;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ghiChu = ghiChu;
        this.hinhThucThanhToanID = hinhThucThanhToanID;
    }

    public NhaTro(String ten, String soDienThoai, LocalDate ngayBatDauThue, String hinhThucThanhToan, String ghiChu) {
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauThue = ngayBatDauThue;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ghiChu = ghiChu;
    }

    public NhaTro(String ten, String soDienThoai, LocalDate ngayBatDauThue, String hinhThucThanhToan, String ghiChu, int hinhThucThanhToanID) {
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauThue = ngayBatDauThue;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ghiChu = ghiChu;
        this.hinhThucThanhToanID = hinhThucThanhToanID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public LocalDate getNgayBatDauThue() {
        return ngayBatDauThue;
    }

    public void setNgayBatDauThue(LocalDate ngayBatDauThue) {
        this.ngayBatDauThue = ngayBatDauThue;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getHinhThucThanhToanID() {
        return hinhThucThanhToanID;
    }

    public void setHinhThucThanhToanID(int hinhThucThanhToanID) {
        this.hinhThucThanhToanID = hinhThucThanhToanID;
    }

    public NhaTro(String maNhaTro, String ten, int id, String soDienThoai, LocalDate ngayBatDauThue, String hinhThucThanhToan, String ghiChu, int hinhThucThanhToanID) {
        this.maNhaTro = maNhaTro;
        this.ten = ten;
        this.id = id;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauThue = ngayBatDauThue;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ghiChu = ghiChu;
        this.hinhThucThanhToanID = hinhThucThanhToanID;
    }

    public NhaTro(String maNhaTro, String ten, String soDienThoai, LocalDate ngayBatDauThue, String hinhThucThanhToan, String ghiChu, int hinhThucThanhToanID) {
        this.maNhaTro = maNhaTro;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauThue = ngayBatDauThue;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ghiChu = ghiChu;
        this.hinhThucThanhToanID = hinhThucThanhToanID;
    }

    public NhaTro(String maNhaTro, String ten, String soDienThoai, LocalDate ngayBatDauThue, String hinhThucThanhToan, String ghiChu) {
        this.maNhaTro = maNhaTro;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauThue = ngayBatDauThue;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ghiChu = ghiChu;
    }

    public NhaTro(int id, String maNhaTro, String ten, String soDienThoai, LocalDate ngayBatDauThue, String hinhThucThanhToan, String ghiChu, int hinhThucThanhToanID) {
        this.id = id;
        this.maNhaTro = maNhaTro;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauThue = ngayBatDauThue;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ghiChu = ghiChu;
        this.hinhThucThanhToanID = hinhThucThanhToanID;
    }

    public String getMaNhaTro() {
        return maNhaTro;
    }

    public void setMaNhaTro(String maNhaTro) {
        this.maNhaTro = maNhaTro;
    }
}
