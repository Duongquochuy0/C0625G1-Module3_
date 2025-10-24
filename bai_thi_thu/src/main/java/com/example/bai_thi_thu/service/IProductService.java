package com.example.bai_thi_thu.service;

import com.example.bai_thi_thu.dto.ProductDto;
import com.example.bai_thi_thu.entity.Product;

import java.util.List;

public interface IProductService {
    List<ProductDto> findAll();
    boolean add(Product product);
    List<ProductDto> search(String name, String categoryId);
    boolean delete(int id);
}
