package com.example.quan_ly_san_phams.repository;

import com.example.quan_ly_san_phams.dto.ProductDto;
import com.example.quan_ly_san_phams.enity.Product;

import java.util.List;

public interface IProductDtoRepository {
    List<ProductDto> findAll();
    boolean add(Product product);
    boolean delete(int id);
    boolean update(Product product);
    ProductDto findById(int id);
    List<ProductDto> search(String name, String categoryId);
    Product findByIdProduct(int id);
    List<ProductDto> findAll(int offset, int limit);
    int countProducts();
}
