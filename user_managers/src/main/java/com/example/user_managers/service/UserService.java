package com.example.user_managers.service;

import com.example.user_managers.entity.User;
import com.example.user_managers.repository.IUserRepository;
import com.example.user_managers.repository.UserRepository;

import java.util.List;

public class UserService implements IUserService {
    IUserRepository userRepository = new UserRepository();

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean add(User user) {
        return userRepository.add(user);
    }

    @Override
    public boolean delete(int id) {
        return userRepository.delete(id);
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }
    public List<User> findByCountry(String country) {
        return ((UserRepository) userRepository).findByCountry(country);
    }
}
