package com.rentcloud.cloud.app.controllers;

import com.rentcloud.cloud.app.entities.client;
import com.rentcloud.cloud.app.entities.score;
import com.rentcloud.cloud.app.services.ClientService;
import com.rentcloud.cloud.app.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Score")
@CrossOrigin(origins = "*")

public class ScoreController {

    @Autowired
    private ScoreService service;

    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/

    @GetMapping("/all")
    public List<score> getClient(){
        return service.getAll();
    }

    //obtenemos el get con Id, le indicamos que le vamos a pasar un parámetro
    @GetMapping("/{id]")            //pasamos el Id por parámetro a la variable clientId
    public Optional<score> getScore(@PathVariable("id") int scoreId){
        return service.getScore(scoreId);
    }
    /* *************************************************Post/Create****************************************/

    //este es el Post
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public score save(@RequestBody score score){//requiéralo del cuerpo de la consulta

        return service.save(score);
    }
    /* *************************************************Put/Update****************************************/

    //este es el put
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public score update(@RequestBody score score){//requiéralo del cuerpo de la consulta
        return service.update(score);
    }
    /* *************************************************Delete****************************************/

    //este es el delete
    @DeleteMapping("/{id}")  //con @PathVariable le indicamos que le vamos a enviar una variable a través del parámetro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int scoreId){

        return service.delete(scoreId);
    }
}
