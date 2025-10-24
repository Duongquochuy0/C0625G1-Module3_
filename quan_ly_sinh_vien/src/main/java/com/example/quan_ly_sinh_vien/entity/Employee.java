package com.example.quan_ly_sinh_vien.entity;

public class Employee {
    private int id;
    private String code;
    private String name;
    private String gender;
    private double  salary;
    private String departmentName;
    private int departmentId;
    public Employee() {
    }

    public Employee(int id, String code, String name, String gender, double salary, String departmentName, int departmentId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Employee(String code, String name, String gender, double salary, String departmentName, int departmentId) {
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }

    public Employee(String code, String name, String gender, double salary, String departmentName) {
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.departmentName = departmentName;
    }
}
