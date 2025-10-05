package com.example.demo.service;

import com.example.demo.entity.Employee;
import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
}
