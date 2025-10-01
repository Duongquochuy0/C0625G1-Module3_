package com.example.quan_ly_san_pham.service;

import com.example.quan_ly_san_pham.enity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    boolean save(Product product);
    Product findById(int id);
    boolean update(int id, Product product);
    boolean remove(int id);
    List<Product> findByName(String name);
}
