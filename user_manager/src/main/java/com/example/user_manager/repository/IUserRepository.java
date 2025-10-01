package com.example.user_manager.repository;

import com.example.user_manager.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {
     User selectUser(int id);
     List<User> findALl();
     boolean deleteUser(int id) throws SQLException;
     boolean updateUser(User user) throws SQLException;
}
