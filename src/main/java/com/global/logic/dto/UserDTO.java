package com.global.logic.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
        private long id;
        private String name;
        private String email;
        private String password;
        private String token;
        private LocalDateTime created;
        private LocalDateTime lastLogin;
        private Boolean isActive;
        private PhoneDTO[] phones;
}
