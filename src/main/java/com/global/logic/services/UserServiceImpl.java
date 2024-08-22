package com.global.logic.services;

import com.global.logic.dto.SignupDTO;
import com.global.logic.entities.User;
import com.global.logic.repositories.UserRepository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(SignupDTO user) {
        User userEntity = new User();
        userEntity.setId(user.getId());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setPhones(user.getPhones());
        return userRepository.save(userEntity);
    }

    @Ignore
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
