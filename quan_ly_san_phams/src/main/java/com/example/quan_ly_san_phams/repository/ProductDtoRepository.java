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
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";
    private static final String UPDATE_PRODUCT = "UPDATE product SET name = ?, price = ?, quantity = ?, description = ?, category_id = ? WHERE id = ?";
    private static final String SELECT_BY_ID_PRODUCT = "SELECT id, name, price, quantity, description, category_id FROM product WHERE id = ?";
    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> productList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PRODUCT);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductDto product = new ProductDto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("categoryName")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getCategoryId());
            ps.setInt(6, product.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProductDto findById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PRODUCT);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    return new ProductDto(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity"),
                            rs.getString("description"),
                            rs.getString("categoryName")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product findByIdProduct(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID_PRODUCT)) {

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

    public List<ProductDto> search(String name, String categoryId) {
        List<ProductDto> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT p.id, p.name, p.price, p.quantity, p.description, c.name AS categoryName " + "FROM product p JOIN category c ON p.category_id = c.id WHERE 1=1");
        if (name != null && !name.isEmpty()) {
            sql.append(" AND p.name LIKE ?");
        }
        if (categoryId != null && !categoryId.isEmpty()) {
            sql.append(" AND c.id = ?");
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
    public List<ProductDto> findAll(int offset, int limit) {
        List<ProductDto> productList = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.price, p.quantity, p.description, c.name AS categoryName " +
                "FROM product p JOIN category c ON p.category_id = c.id " +
                "LIMIT ? OFFSET ?";

        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDto product = new ProductDto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("categoryName")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return productList;
    }
    public int countProducts() {
        String sql = "SELECT COUNT(*) AS total FROM product";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
