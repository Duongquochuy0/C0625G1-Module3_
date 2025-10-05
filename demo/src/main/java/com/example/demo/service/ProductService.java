package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.IProductRepository;
import com.example.demo.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {

    private final IProductRepository productRepository = new ProductRepository();

    @Override
    public boolean create(Product product) {
        return productRepository.create(product);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
