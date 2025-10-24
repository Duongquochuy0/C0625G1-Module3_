package com.example.bai_thi_thu.repository;

import com.example.bai_thi_thu.dto.ProductDto;
import com.example.bai_thi_thu.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {

    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> list = new ArrayList<>();
        String sql = "SELECT p.id, p.code, p.name, p.unit, p.price, c.id as categoryId, c.name as categoryName " +
                "FROM product p JOIN category c ON p.category_id = c.id";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new ProductDto(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("unit"),
                        rs.getDouble("price"),
                        rs.getInt("categoryId"),
                        rs.getString("categoryName")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean add(Product product) {
        String sql = "INSERT INTO product (code, name, unit, price, category_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getCode());
            ps.setString(2, product.getName());
            ps.setString(3, product.getUnit());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getCategoryId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ProductDto> search(String name, String categoryId) {
        List<ProductDto> list = new ArrayList<>();
        String sql = "SELECT p.id, p.code, p.name, p.unit, p.price, c.id as categoryId, c.name as categoryName " +
                "FROM product p JOIN category c ON p.category_id = c.id " +
                "WHERE p.name LIKE ? " +
                (categoryId != null && !categoryId.isEmpty() ? "AND c.id = ?" : "");
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            if (categoryId != null && !categoryId.isEmpty()) {
                ps.setInt(2, Integer.parseInt(categoryId));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ProductDto(
                            rs.getInt("id"),
                            rs.getString("code"),
                            rs.getString("name"),
                            rs.getString("unit"),
                            rs.getDouble("price"),
                            rs.getInt("categoryId"),
                            rs.getString("categoryName")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
