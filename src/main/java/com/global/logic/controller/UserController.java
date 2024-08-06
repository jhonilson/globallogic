package com.global.logic.controller;

import com.global.logic.dto.LoginDTO;
import com.global.logic.dto.LoginResponse;
import com.global.logic.dto.SignupResponse;
import com.global.logic.entities.User;
import com.global.logic.security.services.JwtService;
import com.global.logic.services.UserServiceImpl;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;
    private final JwtService jwtService;

    public UserController(UserServiceImpl userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }


    @PostMapping("/sign-up")
    public ResponseEntity<SignupResponse> save(@RequestBody User user) {
        //String pwdEncrypt = passwordEncoder().encode(user.getPassword());
        String pwdEncrypt = hashPassword(user.getPassword());
        user.setPassword(pwdEncrypt);
        User u = userService.save(user);
        String token = jwtService.createToken(u.getEmail());
        u.setToken(token);
        final var uuid = UUID.randomUUID().toString();
        //u.setId(uuid);
        return ResponseEntity.ok(SignupResponse.builder()
                .id(u.getId())
                .created(u.getCreated())
                .lastLogin(u.getLastLogin())
                .isActive(u.getIsActive())
                .token(u.getToken())
                .build());
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDTO) {
        //String pwdEncrypt = passwordEncoder().encode(loginDTO.getPassword());
        String pwdEncrypt = hashPassword(loginDTO.getPassword());
        User user = userService.findByEmailAndPassword(loginDTO.getEmail(), pwdEncrypt);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }else {
            String token = jwtService.createToken(loginDTO.getEmail());
            user.setToken(token);
            return ResponseEntity.ok(LoginResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .created(user.getCreated())
                    .lastLogin(user.getLastLogin())
                    .isActive(user.getIsActive())
                    .token(user.getToken())
                    //.phones(user.getPhones())
                    .build());
        }
    }

    @Hidden
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

/*    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    public String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, "$2a$10$2w6z7z7z7z7z7z7z7z7z7z");
    }
}
