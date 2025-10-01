package com.example.user_manager.service;

import com.example.user_manager.entity.User;
import com.example.user_manager.repository.IUserRepository;
import com.example.user_manager.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService {

    // Gọi repository
    private final IUserRepository userRepository = new UserRepository();

    @Override
    public User selectUser(int id) {
        return userRepository.selectUser(id);
    }

    @Override
    public List<User> findALl() {
        return userRepository.findALl();
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return userRepository.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return userRepository.updateUser(user);
    }
}
