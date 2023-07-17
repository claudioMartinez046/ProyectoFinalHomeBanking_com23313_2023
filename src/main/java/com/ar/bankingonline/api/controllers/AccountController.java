package com.ar.bankingonline.api.controllers;

import com.ar.bankingonline.api.controllers.dtos.AccountDto;
import com.ar.bankingonline.aplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    private AccountService service;

    @Autowired
    public AccountController(AccountService service){
        this.service = service;
    }

    @GetMapping(value = "/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts(){
        //mapear la respuesta del DTO a un a lista de obgeto user(mapper)
        //1)obtener la lista de los DTOs user de la DB
        //agregar el servicio a la implementacion del metodo del controlador
        List<AccountDto> accounts = service.getAccounts();
        //2)realizado_mapear la repuesta del DTO a una lista de obgeto User(Mapper)
        //3)devolver a la lista y enviar como repuesta
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
       }
    // GET User (solo una cuenta)
    @GetMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto account = service.getAccountById(id);
        return ResponseEntity.status(200).body(account);
    }
    //POST (crear usuario)
    @PostMapping("/accounts")
    //RequestBody = recibimos un usario por el body del HTML
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto dto){
        //redijiera hacia el responsable de crear el user en la DB
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(dto));
    }

    //PUT update
    @PutMapping(value = "/accounts")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto account){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, account));
    }
    // delete
    @DeleteMapping(value = "/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
    }

}
