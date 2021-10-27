package com.rentcloud.cloud.app.repositories;


import com.rentcloud.cloud.app.entities.admin;
import com.rentcloud.cloud.app.entities.reservation;
import com.rentcloud.cloud.app.entities.score;
import com.rentcloud.cloud.app.repositories.crud.ScoreCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ScoreRepository {

    @Autowired /*acá es como crear un objeto del interface ScoreCrudRepository
    la cual usaremos para la conexión a la base de datos*/
    private ScoreCrudRepository repository;


    /* **********************************Creamos el CRUD *******************************************************************/

    /*Select * from
     * retorna todos los registros*/
    public List<score> getAll(){
        return (List<score>) repository.findAll();//busca todos los registros de la tabla admin
    }
    /*select * from table where ID=id
     * retorna un registro por el id*/
    //devuelve algo opcional devuelve nulo o e valor
    public Optional<score> getScore(int id){
        return repository.findById(id);
    }

    /*Insert y Update
     * actualiza o crea nuevo registro*/
    public  score save(score score){
        return repository.save(score);
    }

    /*delete from table*/
    public void delete(score score){
        repository.delete(score);
    }
}
