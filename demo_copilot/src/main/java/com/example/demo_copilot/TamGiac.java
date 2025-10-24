package com.example.demo_copilot;

public class TamGiac {
    /**
     * Kiểm tra xem ba cạnh a, b, c có thể tạo thành một tam giác hay không.
     *
     * @param a độ dài cạnh thứ nhất
     * @param b độ dài cạnh thứ hai
     * @param c độ dài cạnh thứ ba
     * @return true nếu a, b, c có thể tạo thành tam giác; false nếu không thể
     */
    public boolean laTamGiac(double a, double b, double c) {
        return a + b > c && a + c > b && b + c > a;
    }
}
