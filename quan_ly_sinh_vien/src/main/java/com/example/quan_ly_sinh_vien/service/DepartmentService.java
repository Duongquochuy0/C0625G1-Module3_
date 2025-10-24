package com.example.quan_ly_sinh_vien.service;

import com.example.quan_ly_sinh_vien.entity.Department;
import com.example.quan_ly_sinh_vien.repository.DepartmentRepository;
import com.example.quan_ly_sinh_vien.repository.IDepartmentRepository;


import java.util.List;

public class DepartmentService implements IDepartmentService{
    IDepartmentRepository departmentRepository = new DepartmentRepository();
    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
