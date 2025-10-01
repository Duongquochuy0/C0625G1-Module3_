package com.example.quan_ly_san_phams.service;

import com.example.quan_ly_san_phams.enity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    boolean add(Category category);
    boolean delete(int id);
    boolean update(Category category);
    Category findById(int id);
}
