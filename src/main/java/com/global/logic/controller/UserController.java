package com.global.logic.controller;

import com.global.logic.dto.LoginDTO;
import com.global.logic.dto.LoginResponse;
import com.global.logic.dto.SignupDTO;
import com.global.logic.dto.SignupResponse;
import com.global.logic.entities.User;
import com.global.logic.security.services.JwtService;
import com.global.logic.services.UserServiceImpl;
import io.swagger.v3.oas.annotations.Hidden;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import com.global.logic.excepcion.UserAlreadyExistsException;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Value("${encrypt.salt}")
    private String salt;
    private final UserServiceImpl userService;
    private final JwtService jwtService;


    @Ignore
    public UserController(UserServiceImpl userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }


    @PostMapping("/sign-up")
    public ResponseEntity<SignupResponse> save(@Valid @RequestBody SignupDTO user)  {


        User userExists = userService.findByEmail(user.getEmail());

        if (userExists != null) {
            throw new UserAlreadyExistsException("Usuario con email " + user.getEmail() + " ya existe.");
        }

        String pwdEncrypt = hashPassword(user.getPassword());
        user.setPassword(pwdEncrypt);
        final var uuid = UUID.randomUUID().toString();
        user.setId(uuid);
        User u = userService.save(user);
        String token = jwtService.createToken(u.getEmail());
        u.setToken(token);

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
        String pwdEncrypt = hashPassword(loginDTO.getPassword());
        Optional<User> userOpt = Optional.ofNullable(userService.findByEmailAndPassword(loginDTO.getEmail(), pwdEncrypt));

        return userOpt.map(user -> {
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
                    .phones(user.getPhones())
                    .build());
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Ignore
    @Hidden
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll().stream()
                .filter(User::getIsActive)
                .collect(Collectors.toList());
    }

    @Ignore
    public String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, salt);
    }
}
