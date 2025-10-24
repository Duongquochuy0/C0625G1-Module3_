package com.example.thi_thu.entity;

import java.time.LocalDate;

public class Heo {
    private int id;
    private String maHeo;
    private LocalDate ngayNhap;
    private int trongLuongNhap;
    private LocalDate ngayXuat;
    private Integer trongLuongXuat;
    private String xuatXu;
    private int idXuatXu;

    public Heo() {
    }

    public Heo(String maHeo, LocalDate ngayNhap, int trongLuongNhap, LocalDate ngayXuat, Integer trongLuongXuat, String xuatXu, int idXuatXu) {
        this.maHeo = maHeo;
        this.ngayNhap = ngayNhap;
        this.trongLuongNhap = trongLuongNhap;
        this.ngayXuat = ngayXuat;
        this.trongLuongXuat = trongLuongXuat;
        this.xuatXu = xuatXu;
        this.idXuatXu = idXuatXu;
    }

    public Heo(int id, String maHeo, LocalDate ngayNhap, int trongLuongNhap, LocalDate ngayXuat, Integer trongLuongXuat, String xuatXu, int idXuatXu) {
        this.id = id;
        this.maHeo = maHeo;
        this.ngayNhap = ngayNhap;
        this.trongLuongNhap = trongLuongNhap;
        this.ngayXuat = ngayXuat;
        this.trongLuongXuat = trongLuongXuat;
        this.xuatXu = xuatXu;
        this.idXuatXu = idXuatXu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHeo() {
        return maHeo;
    }

    public void setMaHeo(String maHeo) {
        this.maHeo = maHeo;
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getTrongLuongNhap() {
        return trongLuongNhap;
    }

    public void setTrongLuongNhap(int trongLuongNhap) {
        this.trongLuongNhap = trongLuongNhap;
    }

    public LocalDate getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(LocalDate ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public Integer getTrongLuongXuat() {
        return trongLuongXuat;
    }

    public void setTrongLuongXuat(Integer trongLuongXuat) {
        this.trongLuongXuat = trongLuongXuat;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public int getIdXuatXu() {
        return idXuatXu;
    }

    public void setIdXuatXu(int idXuatXu) {
        this.idXuatXu = idXuatXu;
    }

    public String getTinhTrang() {
        return (ngayXuat == null) ? "Chưa bán" : "Đã bán";
    }
}
