package com.example.quan_ly_san_phams.repository;


import com.example.quan_ly_san_phams.dto.ProductDto;
import com.example.quan_ly_san_phams.enity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDtoRepository implements IProductDtoRepository {
    private static final String SELECT_ALL_PRODUCT = "SELECT p.id, p.name, p.price, p.quantity, p.description, c.name AS categoryName " + "FROM product p " + "JOIN category c ON p.category_id = c.id;";
    private static final String INSERT_PRODUCT = "INSERT INTO product (name, price, quantity, description, category_id) VALUES (?, ?, ?, ?, ?)";
    private  static final String DELETE_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE users SET name = ?, email = ?, country = ? WHERE id = ?";

    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> productList = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String categoryName = rs.getString("categoryName");
                ProductDto product = new ProductDto(id, name, price, quantity, description, categoryName);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error when getting products: " + e.getMessage());
        }
        return productList;
    }

    @Override
    public boolean add(Product product) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getCategoryId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Product product) {
        String sql = "UPDATE product SET name = ?, price = ?, quantity = ?, description = ?, category_id = ? WHERE id = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getCategoryId());
            ps.setInt(6, product.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public ProductDto findById(int id) {
        return null;
    }
    public List<ProductDto> search(String name, String categoryId) {
        List<ProductDto> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT p.id, p.name, p.price, p.quantity, p.description, c.name AS categoryName " + "FROM product p JOIN category c ON p.category_id = c.id WHERE 1=1 ");
        if (name != null && !name.isEmpty()) {
            sql.append(" and p.name like ?");
        }
        if (categoryId != null && !categoryId.isEmpty()) {
            sql.append(" and c.id = ?");
        }
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int index = 1;
            if (name != null && !name.isEmpty()) {
                ps.setString(index++, "%" + name + "%");
            }
            if (categoryId != null && !categoryId.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(categoryId));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductDto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("categoryName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Product findByIdProduct(int id) {
        String sql = "SELECT id, name, price, quantity, description, category_id FROM product WHERE id = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getInt("category_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
