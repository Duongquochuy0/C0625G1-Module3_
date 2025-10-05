package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface IProductService {
    boolean create(Product product);
    Product findById(int id);
    Product findByName(String name);
    List<Product> findAll();
}
