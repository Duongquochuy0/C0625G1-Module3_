package com.example.spring.service;

import com.example.spring.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(int id);
    void save(Product product);
    void deleteById(int id);
    List<Product> searchByName(String keyword);

}
