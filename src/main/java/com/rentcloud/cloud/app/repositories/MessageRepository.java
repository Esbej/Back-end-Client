package com.rentcloud.cloud.app.repositories;

import com.rentcloud.cloud.app.entities.admin;
import com.rentcloud.cloud.app.entities.message;
import com.rentcloud.cloud.app.repositories.crud.MessageCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepository {

    @Autowired
    private MessageCrudRepository repository;

    /* **********************************Creamos el CRUD *******************************************************************/


    /*Select * from
     * retorna todos los registros*/
    public List<message> getAll(){
        return (List<message>) repository.findAll();//busca todos los registros de la tabla admin
    }
    /*select * from table where ID=id
     * retorna un registro por el id*/
    //devuelve algo opcional devuelve nulo o e valor
    public Optional<message> getMessage(int id){
        return repository.findById(id);
    }

    /*Insert y Update
     * actualiza o crea nuevo registro*/
    public  message save(message message){
        return repository.save(message);
    }

    /*delete from table*/
    public void delete(message message){
        repository.delete(message);
    }
}
