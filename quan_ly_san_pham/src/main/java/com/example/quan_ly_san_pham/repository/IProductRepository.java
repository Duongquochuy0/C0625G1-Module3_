package com.example.quan_ly_san_pham.repository;

import com.example.quan_ly_san_pham.enity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    boolean save(Product product);
    Product findById(int id);
    boolean update(int id, Product product);
    boolean remove(int id);
    List<Product> findByName(String name);
}
