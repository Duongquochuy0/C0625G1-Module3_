package com.example.user_managers.repository;

import com.example.user_managers.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private static final String SELECT_ALL = "select * from users";
    private static final String ADD = "insert into users (name, email, country) value(?,?,?)";
    private  static final String DELETE_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE users SET name = ?, email = ?, country = ? WHERE id = ?";

    @Override
    public List<User> findAll() {
        Connection connection = BaseRepository.getConnectDB();
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int iD = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                userList.add(new User(iD, name, email, country));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    @Override
    public boolean add(User user) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            int row = preparedStatement.executeUpdate();
            return row == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setInt(4, user.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findById(int id) {
        Connection connection = BaseRepository.getConnectDB();
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("country")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<User> findByCountry(String country) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE country LIKE ?";

        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + country + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("country")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }
}
