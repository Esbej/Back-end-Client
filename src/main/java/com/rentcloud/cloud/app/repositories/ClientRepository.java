package com.rentcloud.cloud.app.repositories;


import com.rentcloud.cloud.app.entities.admin;
import com.rentcloud.cloud.app.entities.category;
import com.rentcloud.cloud.app.entities.client;
import com.rentcloud.cloud.app.repositories.crud.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    @Autowired
    private ClientCrudRepository repository;

    /* **********************************Creamos el CRUD *******************************************************************/

    /*Select * from
     * retorna todos los registros*/
    public List<client> getAll(){
        return (List<client>) repository.findAll();//busca todos los registros de la tabla admin
    }
    /*select * from table where ID=id
     * retorna un registro por el id*/
    //devuelve algo opcional devuelve nulo o e valor
    public Optional<client> getClient(int id){
        return repository.findById(id);
    }

    /*Insert y Update
     * actualiza o crea nuevo registro*/
    public client save(client client){
        return repository.save(client);
    }

    /*delete from table*/
    public void delete(client client){
        repository.delete(client);
    }
}
