package com.ar.bankingonline.api.controllers;


import com.ar.bankingonline.api.controllers.dtos.UserDto;
import com.ar.bankingonline.aplication.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//***********CAPA de presentacion de la APP DONDE RECIBE LOS DATOS Y LA DELEGA(AL SERVICE)
//definiendola arriba del controlador ,despues de la url base define a donde se tiene que dirigiren
@RequestMapping("/api")
public class UserController {
    @Autowired
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }
    //Metodos HTTP
    //GET Users
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        //mapear la respuesta del DTO a un a lista de obgeto user(mapper)
        //1)obtener la lista de los DTOs user de la DB
        //agregar el servicio a la implementacion del metodo del controlador
        List<UserDto> usuarios = service.getUsers();
        //2)realizado_mapear la repuesta del DTO a una lista de obgeto User(Mapper)
        //3)devolver a la lista y enviar como repuesta
         return ResponseEntity.status(200).body(usuarios);
         //harcodeado  para test
        /*User user =new User("Cris", "1234");
        user.setId(01);*/
    }
    // GET User (solo un usuario)
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.getUserById(id));
    }

    //POST (crear usuario)
    //por convencion en los mapping el recursose coloca en plural
    @PostMapping("/users")
    //RequestBody = recibimos un usario por el body del HTML
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto){
        //redijiera hacia el responsable de crear el user en la DB
        return ResponseEntity.status(201).body(service.createUser(dto));
    }
    //PUT
    @PutMapping("/users")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user){
        return ResponseEntity.status(200).body(service.update(user));
    }

    //DELETE
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.status(200).body("se ha eliminado el Usuario correctamente");
    }
}
