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
            regexp = "^(?=.*[A-Z]{1})(?=.*\\d{2})(?!.*\\d{3})(?!.*[A-Z]{2})(?!.*[^a-zA-Z\\d])(?!.*[A-Z].*[A-Z])(?!.*\\d.*\\d.*\\d)(?!.*[^a-zA-Z0-9]).{8,12}$",
            message = "Password debe tener una mayuscula, dos numeros, y entre 8 y 12 caracteres"
    )
    private String password;
}
