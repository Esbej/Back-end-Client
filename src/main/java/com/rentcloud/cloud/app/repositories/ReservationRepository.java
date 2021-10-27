package com.rentcloud.cloud.app.repositories;

import com.rentcloud.cloud.app.entities.admin;
import com.rentcloud.cloud.app.entities.reservation;
import com.rentcloud.cloud.app.repositories.crud.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository repository;


    /* **********************************Creamos el CRUD *******************************************************************/

    /*Select * from
     * retorna todos los registros*/
    public List<reservation> getAll(){
        return (List<reservation>) repository.findAll();//busca todos los registros de la tabla admin
    }
    /*select * from table where ID=id
     * retorna un registro por el id*/
    //devuelve algo opcional devuelve nulo o e valor
    public Optional<reservation> getReservation(int id){
        return repository.findById(id);
    }

    /*Insert y Update
     * actualiza o crea nuevo registro*/
    public  reservation save(reservation reservation){
        return repository.save(reservation);
    }

    /*delete from table*/
    public void delete(reservation reservation){
        repository.delete(reservation);
    }
}