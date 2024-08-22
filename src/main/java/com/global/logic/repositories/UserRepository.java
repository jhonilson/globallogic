package com.global.logic.repositories;

import com.global.logic.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //User findByName(String name);
    User findByEmailAndPassword(String email, String password);
    User save(User user);
    User findByEmail(String email);
}
