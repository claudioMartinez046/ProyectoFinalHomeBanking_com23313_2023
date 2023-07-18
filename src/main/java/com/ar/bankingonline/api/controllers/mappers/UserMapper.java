package com.ar.bankingonline.api.controllers.mappers;

import com.ar.bankingonline.domain.models.Account;
import com.ar.bankingonline.domain.models.User;
import com.ar.bankingonline.api.controllers.dtos.UserDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserMapper {
    //los mappers me permieten enviar los datos desde
    //una entidad hacia un dto o visceversa
    //todo aplicar patron builder
    //USER to DTOS (recibe una ENTIDAD y devuelve un DTO)
    public UserDto userMapToDto(User user){
        UserDto dto = new UserDto();
        List<Long> accountsId = new ArrayList<>();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        //verificacion si hay cuenta asociado a un usuario se lo agrega
        if(user.getAccounts() != null)
            for(Account a:
            user.getAccounts()){
                Long id = a.getId();
                accountsId.add(id);
        }
        dto.setIdAccounts(accountsId);
        dto.setId(user.getId());
        return dto;
    }
        //recibe un DTO y lo convierte en ENTIDAD
    public User dtoToUser(UserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        //TODO:modificar getAccount del DTO para obtener una lista users
        //user.setAccounts(dto.getAccounts());
        user.setAccounts(new ArrayList<>());
        //user.setId(dto.getId());
        return user;
    }
}
