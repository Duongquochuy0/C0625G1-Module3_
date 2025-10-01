package com.example.user_managers.repository;

import com.example.user_managers.entity.User;

import java.util.List;

public interface IUserRepository {
    List<User> findAll();
    boolean add(User user);
    boolean delete(int id);
    boolean update(User user);
    User findById(int id);

}
