package com.ar.bankingonline.api.controllers.dtos;

import com.ar.bankingonline.domain.models.Account;
import lombok.*;

import java.util.List;

@Data // incluye varias tareas
public class UserDto {
    public UserDto(){}

    private Long id;
    private String username;
    private String password;
    //va a mostrar los ID de las cuentas
    private List<Long> idAccounts;
}
