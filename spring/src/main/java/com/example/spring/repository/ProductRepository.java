package com.example.spring.repository;

import com.example.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
    }

    @Override
    public void save(Product product) {
        if (product.getId() > 0) {
            // Cập nhật sản phẩm có sẵn
            String sql = "UPDATE product SET name = ?, price = ? WHERE id = ?";
            jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getId());
        } else {
            // Thêm mới sản phẩm
            String sql = "INSERT INTO product (name, price) VALUES (?, ?)";
            jdbcTemplate.update(sql, product.getName(), product.getPrice());
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    @Override
    public List<Product> searchByName(String keyword) {
        String sql = "SELECT * FROM product WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), "%" + keyword + "%");
    }

}
