package com.example.quan_ly_san_phams.repository;

import com.example.quan_ly_san_phams.enity.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> findAll();
    boolean add(Category category);
    boolean delete(int id);
    boolean update(Category category);
    Category findById(int id);
}
