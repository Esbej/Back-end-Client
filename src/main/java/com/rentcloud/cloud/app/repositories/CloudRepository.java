package com.rentcloud.cloud.app.repositories;

import com.rentcloud.cloud.app.entities.cloud;
import com.rentcloud.cloud.app.repositories.crud.CloudCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CloudRepository {
    @Autowired
    private CloudCrudRepository repository;


    /* **********************************Creamos el CRUD *******************************************************************/

    /*Select * from
     * retorna todos los registros*/
    public List<cloud> getAll(){
        return (List<cloud>) repository.findAll();//busca todos los registros de la tabla admin
    }
    /*select * from table where ID=id
     * retorna un registro por el id*/
    //devuelve algo opcional devuelve nulo o e valor
    public Optional<cloud> getCloud(int id){
        return repository.findById(id);
    }

    /*Insert y Update
     * actualiza o crea nuevo registro*/
    public  cloud save(cloud cloud){
        return repository.save(cloud);
    }

    /*delete from table*/
    public void delete(cloud cloud){
        repository.delete(cloud);
    }
}
