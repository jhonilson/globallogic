package com.global.logic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.global.logic.entities.Phone;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class LoginResponse {
    private String id;
    private String name;
    private String email;
    private String password;
    private String token;
    @JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss a")
    private LocalDateTime created;
    @JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss a")
    private LocalDateTime lastLogin;
    private Boolean isActive;
    private List<Phone> phones;
}
