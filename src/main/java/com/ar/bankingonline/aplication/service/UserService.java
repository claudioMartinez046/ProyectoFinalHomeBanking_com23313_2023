package com.ar.bankingonline.aplication.service;

import com.ar.bankingonline.api.controllers.dtos.AccountDto;
import com.ar.bankingonline.api.controllers.mappers.UserMapper;
import com.ar.bankingonline.domain.exceptions.AccountNotFoundException;
import com.ar.bankingonline.domain.models.Account;
import com.ar.bankingonline.domain.models.User;
import com.ar.bankingonline.api.controllers.dtos.UserDto;
import com.ar.bankingonline.infrastructure.repositories.AccountRepository;
import com.ar.bankingonline.infrastructure.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//********CAPA DE FUNCIONALIDAD DE LA APP EN SI******
//EL SERVICE TIENE LA LOGICA DE NEGOCIOS ,Y LUEGO DEVUELVE AL CONTROLADOR
@Service
public class UserService {
    //declaro una instancia del repositorio con la notyacion @autowire y sin
    @Autowired
    private UserRepository repository;

    @Autowired
    private AccountRepository accountRepository;
    //constructor
    public UserService(UserRepository repository,AccountRepository accountRepository){

        this.repository = repository;
        this.accountRepository=accountRepository;
    }

    //Primero generar los metodos del CRUD
        //una forma de casteo usando el UserMapper, convirtiendo los uderDTO del la base de datos a user normales de entidas
    public List<UserDto> getUsers(){
        List<User> users = repository.findAll();
        return users.stream()
                .map(UserMapper::userMapToDto)
                .collect(Collectors.toList());

    }
    // todo: Refactor
    public UserDto getUserById( Long id) {
        //OPTIONAL maneja que eld ato venga null o not null
        Optional<User> user = repository.findById(id);
        return UserMapper.userMapToDto(user.get());
    }
    public UserDto createUser(UserDto user) {
        return UserMapper.userMapToDto(repository.save(UserMapper.dtoToUser(user)));
    }

    public UserDto update(long id, UserDto user) {
        Optional<User> userCreated = repository.findById(id);

        if (userCreated.isPresent()){
            User entity = userCreated.get();

            User accountUpdated = UserMapper.dtoToUser(user);
            accountUpdated.setAccounts(entity.getAccounts());

            if (user.getIdAccounts() != null) { // Verifica que la lista de cuentas no sea null
                List <Account> accountList =accountRepository.findAllById(user.getIdAccounts());
                List<Account> accountListFilter=accountList.stream().filter(e->!entity.getAccounts().contains(e)).toList();
                accountUpdated.getAccounts().addAll(accountListFilter);
                accountUpdated.setAccounts(accountList);
            }

            accountUpdated.setId(entity.getId());

            User saved = repository.save(accountUpdated);

            return UserMapper.userMapToDto(saved);
        } else {
            throw new AccountNotFoundException("User not found with id: " + id);
        }
        //explicacion return = se recibe un userDTO que en el ultimoparentesis,
        //con los mappers los convertimos a una entidad que se guarda a la DB
        //a su vez ese usuario entidad es mapeado a userDto para retornar al controller
        //para finalmente devolver al body.
        //return UserMapper.userMapToDto(repository.save(UserMapper.dtoToUser(user)));
    }

      public String delete(Long id){
          if (repository.existsById(id)){
              repository.deleteById(id);
              return "Se ha eliminado la cuenta";
          } else {
              return "No se ha eliminado la cuenta";
          }
      }

         // TODO: Generar la asociación de una primer cuenta al crear un User
         // Agregar una cuenta al usuario
        public UserDto addAccountToUser(AccountDto account, Long id){
        // primero: buscar el usuario por id
        // segundo: añadir la cuenta a la lista del usuario encontrado
        // tercero: devolver el usuario con la cuenta agregada
        return null;
        //repository.deleteById(id);
    }

}
