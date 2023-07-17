package com.ar.bankingonline.aplication.service;

import com.ar.bankingonline.api.controllers.dtos.AccountDto;

import com.ar.bankingonline.api.controllers.mappers.AccountMapper;

import com.ar.bankingonline.domain.exceptions.AccountNotFoundException;
import com.ar.bankingonline.domain.models.Account;
import com.ar.bankingonline.domain.models.User;
import com.ar.bankingonline.infrastructure.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {
    //declaro una instancia del repositorio con la notyacion @autowire y sin
    @Autowired
    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }
    public List<AccountDto> getAccounts(){
        List<Account> accounts = repository.findAll();
        return accounts.stream()
                .map(AccountMapper ::accountToDto)
                .collect(Collectors.toList());

    }

    public AccountDto createAccount(AccountDto account) {
        return AccountMapper.accountToDto(repository.save(AccountMapper.dtoToAccount(account)));
    }

    public AccountDto getAccountById(Long id) {
        AccountDto account =AccountMapper.accountToDto(repository.findById(id).get());//o .findById(id).orElse(null);
        return account;
    }

    public AccountDto updateAccount(Long id, AccountDto account) throws AccountNotFoundException {
        //return AccountMapper.accountToDto(repository.save(AccountMapper.dtoToAccount(account)));
        Optional<Account> accountCreated = repository.findById(id);

        if (accountCreated.isPresent()){
            //me traigo la entidad de base de datos. esta es la que voy a modificar
            Account entity = accountCreated.get();
            //valido que informacion traigo en el request para solo modificar lo de la peticion y no toda la entidad
            if (account.getAmount()!=null){
                entity.setBalance(account.getAmount());
            }
            if (account.getOwner()!=null){
                //si hay un owner en el body me traigo la entidad completa de bd por el id para vinculale ese usuario a la cuenta
                User user=userRepository.getReferenceById(account.getOwner().getId());
                if (user!=null){
                    entity.setOwner(user);
                }

            }
            //no hay que usar este maper ya que te crea un account distinto al de bd y no tiene los valores del account en cuestion, hay que modificar solo el account traido de base de datos
            //Account accountUpdated = AccountMapper.dtoToAccount(account);

            Account saved = repository.save(entity);

            return AccountMapper.accountToDto(saved);
        } else {
            throw new AccountNotFoundException("Account not found with id: " + id);
        }
    }

    public String deleteAccount(Long id){

        if (repository.existsById(id)){
            repository.deleteById(id);
            return "Se ha eliminado la cuenta";
        } else {
            return "No se ha eliminado la cuenta";
        }
        // Agregar métodos de ingreso y egreso de dinero y realizacion de transferencia
        public BigDecimal withdraw(BigDecimal amount, Long idOrigin){
            // primero: Obtenemos la cuenta
            Account account = repository.findById(idOrigin).orElse(null);
            // segundo: debitamos el valor del amount con el amount de esa cuenta (validar si hay dinero disponible)
            if (account.getBalance().subtract(amount).intValue() > 0){
                account.setBalance(account.getBalance().subtract(amount));
                repository.save(account);
            }
            // tercero: devolvemos esa cantidad
            return account.getBalance().subtract(amount);
        }

        public BigDecimal addAmountToAccount(BigDecimal amount, Long idOrigin){
            // primero: Obtenemos la cuenta
            Account account = repository.findById(idOrigin).orElse(null);
            // segundo: acreditamos el valor del amount con el amount de esa cuenta
            account.setBalance(account.getBalance().add(amount));
            repository.save(account);
            // tercero: devolvemos esa cantidad
            return amount;

        }
    }


    }
    //agregar metods de ingreso y egreso de dinero y realizacion de tanferencia

