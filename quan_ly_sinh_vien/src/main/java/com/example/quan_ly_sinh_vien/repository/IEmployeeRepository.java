package com.example.quan_ly_sinh_vien.repository;

import com.example.quan_ly_sinh_vien.entity.Employee;

import java.util.List;

public interface IEmployeeRepository {
    List<Employee> findAll();
    boolean add(Employee employee);
    boolean delete(int id);
    boolean update(Employee employee);
    Employee findById(int id);
    List<Employee> search(String code, String name, String departmentId);
    Employee findByIdDepartment(int id);

}
