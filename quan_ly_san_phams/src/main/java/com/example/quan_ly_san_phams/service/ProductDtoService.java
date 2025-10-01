package com.example.quan_ly_san_phams.service;

import com.example.quan_ly_san_phams.dto.ProductDto;
import com.example.quan_ly_san_phams.enity.Product;
import com.example.quan_ly_san_phams.repository.IProductDtoRepository;
import com.example.quan_ly_san_phams.repository.ProductDtoRepository;

import java.util.List;

public class ProductDtoService implements IProductDtoService {
    private final IProductDtoRepository productDtoRepository = new ProductDtoRepository();

    @Override
    public List<ProductDto> findAll() {
        return productDtoRepository.findAll();
    }

    @Override
    public boolean add(Product product) {
        return productDtoRepository.add(product);
    }

    @Override
    public boolean delete(int id) {
        return productDtoRepository.delete(id);
    }

    @Override
    public boolean update(Product product) {
        return productDtoRepository.update(product);
    }

    @Override
    public ProductDto findById(int id) {
        return productDtoRepository.findById(id);
    }

    public List<ProductDto> search(String name, String categoryId) {
        return productDtoRepository.search(name, categoryId);
    }
    public Product findByIdProduct(int id) {
        return productDtoRepository.findByIdProduct(id);
    }
    @Override
    public List<ProductDto> findAll(int offset, int limit) {
        return productDtoRepository.findAll(offset, limit);
    }
    @Override
    public int countProducts() {
        return productDtoRepository.countProducts();
    }
}
