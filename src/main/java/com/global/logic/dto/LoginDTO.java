package com.global.logic.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class LoginDTO {
    @NotBlank(message = "Email es obligatorio")
    //@Email(message = "Email should be valid")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$",
            message = "Formato de email incorrecto"
    )
    private String email;

    @NotBlank(message = "Password es obligatorio")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d{2})(?=.*[a-z]).{8,12}$",
            message = "Password must have one uppercase letter, two numbers, and be between 8 and 12 characters long"
    )
    private String password;
}
