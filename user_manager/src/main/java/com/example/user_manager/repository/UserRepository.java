package com.example.user_manager.repository;

import com.example.user_manager.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private static final String SELECT_BY_ID_SQL = "SELECT id, name, email, country FROM users WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, name, email, country FROM users";
    private static final String UPDATE_SQL = "UPDATE users SET name = ?, email = ?, country = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM users WHERE id = ?";

    @Override
    public User selectUser(int id) {
        User user = null;
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String country = rs.getString("country");
                    user = new User(id, name, email, country);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findALl() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                userList.add(new User(id, name, email, country));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setInt(4, user.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
