package com.global.logic.services;

import com.global.logic.dto.SignupDTO;
import com.global.logic.entities.User;

import java.util.List;

public interface UserService {
    User save(SignupDTO user);
    List<User> findAll();
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
