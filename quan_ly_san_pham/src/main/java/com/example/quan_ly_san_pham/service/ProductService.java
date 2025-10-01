package com.example.quan_ly_san_pham.service;

import com.example.quan_ly_san_pham.enity.Product;
import com.example.quan_ly_san_pham.repository.IProductRepository;
import com.example.quan_ly_san_pham.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService{
    private final IProductRepository productRepository = new ProductRepository();
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean update(int id, Product product) {
        return productRepository.update(id, product);
    }

    @Override
    public boolean remove(int id) {
        return productRepository.remove(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }
}
