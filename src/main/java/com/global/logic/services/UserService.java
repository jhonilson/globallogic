package com.global.logic.services;

import com.global.logic.entities.User;

import java.util.List;

public interface UserService {
    User save(User user);
    List<User> findAll();
    User findByEmailAndPassword(String email, String password);
}
