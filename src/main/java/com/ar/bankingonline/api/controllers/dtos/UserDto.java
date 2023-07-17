package com.ar.bankingonline.api.controllers.dtos;

import lombok.*;

@Data // incluye varias tareas
public class UserDto {
    public UserDto(){}

    private Long id;
    private String username;
    private String password;
}
