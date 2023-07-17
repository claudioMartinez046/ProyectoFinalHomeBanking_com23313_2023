package com.ar.bankingonline.api.controllers.mappers;

import com.ar.bankingonline.domain.models.User;
import com.ar.bankingonline.api.controllers.dtos.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    //los mappers me permieten enviar los datos desde
    //una entidad hacia un dto o visceversa
    //todo aplicar patron builder
    //USER to DTOS (recibe una ENTIDAD y devuelve un DTO)
    public UserDto userMapToDto(User user){
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setId(user.getId());
        return dto;
    }
        //recibe un DTO y lo convierte en ENTIDAD
    public User dtoToUser(UserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setId(dto.getId());
        return user;
    }
}