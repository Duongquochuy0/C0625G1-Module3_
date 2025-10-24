package com.example.quan_ly_sinh_vien.repository;

import com.example.quan_ly_sinh_vien.entity.Department;
import com.example.quan_ly_sinh_vien.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IEmployeeRepository{
    private static final String SELECT_ALL_EMPLOYEE = "SELECT e.id, e.code, e.name, e.gender, e.salary, e.department_id, d.name AS departmentName FROM employee e JOIN department d ON e.department_id = d.id";
    private static final String INSERT_EMPLOYEE = "INSERT INTO product (name, price, quantity, description, category_id) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
    private static final String UPDATE_EMPLOYEE = "UPDATE employee SET code = ?, name = ?, gender = ?, salary = ?,department_id = ? WHERE id = ?";
    private static final String SELECT_BY_ID_EMPLOYEE = "SELECT id, code, name, gender, salary, department_id FROM employee WHERE id = ?";
    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_EMPLOYEE);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getDouble("salary"),
                        rs.getString("departmentName"),
                        rs.getInt("department_id")
                );
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public boolean add(Employee employee) {
        String sql = "INSERT INTO employee (code, name, gender, salary, department_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, employee.getCode());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getGender());
            ps.setDouble(4, employee.getSalary());
            ps.setInt(5, employee.getDepartmentId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean delete(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Employee employee) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            ps.setString(1, employee.getCode());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getGender());
            ps.setDouble(4, employee.getSalary());
            ps.setInt(5, employee.getDepartmentId());
            ps.setInt(6, employee.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Employee findById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID_EMPLOYEE)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setCode(rs.getString("code"));
                employee.setName(rs.getString("name"));
                employee.setGender(rs.getString("gender"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDepartmentId(rs.getInt("department_id"));

                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> search(String code, String name, String departmentId) {
        List<Employee> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT e.id, e.code, e.name, e.gender, e.salary, " +
                        "d.id AS departmentId, d.name AS departmentName " +
                        "FROM employee e " +
                        "JOIN department d ON e.department_id = d.id " +
                        "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        if (code != null && !code.isEmpty()) {
            sql.append(" AND e.code LIKE ?");
            params.add("%" + code + "%");
        }
        if (name != null && !name.isEmpty()) {
            sql.append(" AND e.name LIKE ?");
            params.add("%" + name + "%");
        }
        if (departmentId != null && !departmentId.isEmpty()) {
            sql.append(" AND d.id = ?");
            params.add(Integer.parseInt(departmentId));
        }

        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getDouble("salary"),
                        rs.getString("departmentName"),
                        rs.getInt("departmentId")
                );
                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



    @Override
    public Employee findByIdDepartment(int id) {
        return null;
    }
}
