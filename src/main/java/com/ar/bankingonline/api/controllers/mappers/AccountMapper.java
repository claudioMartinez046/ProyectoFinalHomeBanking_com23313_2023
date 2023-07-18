package com.ar.bankingonline.api.controllers.mappers;

import com.ar.bankingonline.api.controllers.dtos.AccountDto;
import com.ar.bankingonline.api.controllers.dtos.UserDto;
import com.ar.bankingonline.domain.models.Account;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    public Account dtoToAccount(AccountDto dto){
        Account account = new Account();
        account.setBalance(dto.getAmount());
        return account;
    }

    public AccountDto accountToDto(Account account){
        AccountDto dto = new AccountDto();
        dto.setAmount(account.getBalance());
        UserDto userDto = UserMapper.userMapToDto(account.getOwner());
        dto.setOwner(userDto);
        dto.setId(account.getId());
        return dto;
    }
}
