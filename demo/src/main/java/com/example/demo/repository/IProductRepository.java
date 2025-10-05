package com.example.demo.repository;

import com.example.demo.entity.Product;

import java.util.List;

public interface IProductRepository {
    boolean create(Product product);
    Product findById(int id);
    Product findByName(String name);
    List<Product> findAll();
}
