package com.example.thi_thu.entity;


public class XuatXu {
    private int id;
    private String ten;

    public XuatXu() {
    }

    public XuatXu(String ten) {
        this.ten = ten;
    }

    public XuatXu(String ten, int id) {
        this.ten = ten;
        this.id = id;
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
}
