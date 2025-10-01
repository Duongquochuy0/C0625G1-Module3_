package com.example.user_managers.service;

import com.example.user_managers.entity.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    boolean add(User user);
    boolean delete(int id);
    boolean update(User user);
    User findById(int id);
}
