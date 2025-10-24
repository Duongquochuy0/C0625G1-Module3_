package com.example.quan_ly_sinh_vien.repository;

import com.example.quan_ly_sinh_vien.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository implements IDepartmentRepository{
    private static final String SELECT_ALL = "SELECT * FROM department";
    @Override
    public List<Department> findAll() {
        List<Department> list = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                department.setLocation("location");
                list.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
