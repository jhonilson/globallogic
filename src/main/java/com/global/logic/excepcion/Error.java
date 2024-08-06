package com.global.logic.excepcion;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Error {
    private List<ErrorResponse> error;
}