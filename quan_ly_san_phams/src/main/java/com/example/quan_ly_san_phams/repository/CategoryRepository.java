package com.example.quan_ly_san_phams.repository;

import com.example.quan_ly_san_phams.enity.Category;
import com.example.quan_ly_san_phams.enity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository{
    private static final String SELECT_ALL_CATEGORY = "SELECT id, name FROM category";
    private static final String ADD = "insert into users (name, email, country) value(?,?,?)";
    private  static final String DELETE_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE users SET name = ?, email = ?, country = ? WHERE id = ?";
    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            System.out.println("Error when getting categories: " + e.getMessage());
        }
        return categories;
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
