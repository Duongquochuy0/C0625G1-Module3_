package com.example.quan_ly_sinh_vien.repository;

import com.example.quan_ly_sinh_vien.entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
}
