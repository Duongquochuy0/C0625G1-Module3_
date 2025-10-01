package com.example.quan_ly_san_phams.service;

import com.example.quan_ly_san_phams.dto.ProductDto;
import com.example.quan_ly_san_phams.enity.Product;

import java.util.List;

public interface IProductDtoService {
    List<ProductDto> findAll();
    boolean add(Product product);
    boolean delete(int id);
    boolean update(Product product);
    ProductDto findById(int id);
    List<ProductDto> search(String name, String categoryId);
    Product findByIdProduct(int id);

}
