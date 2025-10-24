package com.example.quan_ly_sinh_vien.service;

import com.example.quan_ly_sinh_vien.entity.Employee;
import com.example.quan_ly_sinh_vien.repository.EmployeeRepository;
import com.example.quan_ly_sinh_vien.repository.IEmployeeRepository;

import java.util.List;

public class EmployeeService implements IEmployeeService{
    IEmployeeRepository employeeRepository = new EmployeeRepository();
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean add(Employee employee) {
        return employeeRepository.add(employee);
    }

    @Override
    public boolean delete(int id) {
        return employeeRepository.delete(id);
    }

    @Override
    public boolean update(Employee employee) {
        return employeeRepository.update(employee);
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> search(String code, String name, String departmentId) {
        return employeeRepository.search(code,name,departmentId);
    }

    @Override
    public Employee findByIdDepartment(int id) {
        return null;
    }
}
