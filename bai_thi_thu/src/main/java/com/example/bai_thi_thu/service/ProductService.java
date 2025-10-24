package com.example.bai_thi_thu.service;

import com.example.bai_thi_thu.dto.ProductDto;
import com.example.bai_thi_thu.entity.Product;
import com.example.bai_thi_thu.repository.IProductRepository;
import com.example.bai_thi_thu.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {
    IProductRepository productRepository = new ProductRepository();
    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean add(Product product) {
        return productRepository.add(product);
    }

    @Override
    public List<ProductDto> search(String name, String categoryId) {
        return productRepository.search(name, categoryId);
    }

    @Override
    public boolean delete(int id) {
        return productRepository.delete(id);
    }
}
