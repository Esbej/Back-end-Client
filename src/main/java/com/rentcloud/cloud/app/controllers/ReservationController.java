package com.rentcloud.cloud.app.controllers;

import com.rentcloud.cloud.app.entities.client;
import com.rentcloud.cloud.app.entities.reservation;
import com.rentcloud.cloud.app.services.ClientService;
import com.rentcloud.cloud.app.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Reservation")
@CrossOrigin(origins = "*")

public class ReservationController {

    @Autowired
    private ReservationService service;

    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/

    @GetMapping("/all")
    public List<reservation> getReservation(){
        return service.getAll();
    }

    //obtenemos el get con Id, le indicamos que le vamos a pasar un parámetro
    @GetMapping("/{id]")            //pasamos el Id por parámetro a la variable clientId
    public Optional<reservation> getReservation(@PathVariable("id") int reservationId){
        return service.getReservation(reservationId);
    }
    /* *************************************************Post/Create****************************************/

    //este es el Post
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public reservation save(@RequestBody reservation reservation){//requiéralo del cuerpo de la consulta

        return service.save(reservation);
    }
    /* *************************************************Put/Update****************************************/

    //este es el put
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public reservation update(@RequestBody reservation reservation){//requiéralo del cuerpo de la consulta
        return service.update(reservation);
    }
    /* *************************************************Delete****************************************/

    //este es el delete
    @DeleteMapping("/{id }")  //con @PathVariable le indicamos que le vamos a enviar una variable a través del parámetro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int reservationId){

        return service.delete(reservationId);
    }
}
