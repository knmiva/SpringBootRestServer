package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    User getUserById(Long id);

    void deleteUser(Long id);

    void updateUser(User user);

    User findUserByUsername(String username);
}
