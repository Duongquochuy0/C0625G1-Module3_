package com.example.quan_ly_san_phams.service;

import com.example.quan_ly_san_phams.enity.Category;
import com.example.quan_ly_san_phams.repository.CategoryRepository;
import com.example.quan_ly_san_phams.repository.ICategoryRepository;

import java.util.List;

public class CategoryService implements ICategoryService {
    ICategoryRepository categoryRepository = new CategoryRepository();
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean add(Category category) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(Category category) {
        return false;
    }

    @Override
    public Category findById(int id) {
        return null;
    }
}
