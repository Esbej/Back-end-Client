package com.rentcloud.cloud.app.repositories;

import com.rentcloud.cloud.app.entities.admin;
import com.rentcloud.cloud.app.repositories.crud.AdminCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository//le indicamos que es el repositorio para crear las consultas o HTTP Request
public class AdminRepository {

    /*acá es como crear un objeto del interface AdminCrudRepository
    la cual usaremos para la conexión a la base de datos*/
    @Autowired
    private AdminCrudRepository repository;

/* **********************************Creamos el CRUD *******************************************************************/
         /*Select * from
         * retorna todos los registros*/
        public List<admin> getAll(){
            return (List<admin>) repository.findAll();//busca todos los registros de la tabla admin
        }

        /*select * from table where ID=id
        * retorna un registro por el id*/
        //devuelve algo opcional devuelve nulo o e valor
        public Optional<admin>getAdmin(int id){
            return repository.findById(id);
        }

        /*Insert y Update
        * actualiza o crea nuevo registro*/
        public  admin save(admin admin){
            return repository.save(admin);
        }

        /*delete from table*/
       public void delete(admin admin){
        repository.delete(admin);
    }
}
