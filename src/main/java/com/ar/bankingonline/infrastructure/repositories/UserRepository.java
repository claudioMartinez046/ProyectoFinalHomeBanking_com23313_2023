package com.ar.bankingonline.infrastructure.repositories;

//CAPA DE PERSISTENCIA DE DATOS QUE HACE CONEXION A LA DDBB EL CRUD MISMO


import com.ar.bankingonline.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository                             //nombre la entidad y tipo de dato del ID de la misma
public interface UserRepository extends JpaRepository<User, Integer> {

}
