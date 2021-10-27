package com.rentcloud.cloud.app.controllers;


import com.rentcloud.cloud.app.entities.client;
import com.rentcloud.cloud.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Client")
@CrossOrigin(origins = "*")

public class ClientController {

    @Autowired
    private ClientService service;

    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/

    @GetMapping("/all")
    public List<client> getClient(){
        return service.getAll();
    }

    //obtenemos el get con Id, le indicamos que le vamos a pasar un parámetro
    @GetMapping("/{id]")            //pasamos el Id por parámetro a la variable clientId
    public Optional<client> getClient(@PathVariable("id") int clientId){
        return service.getClient(clientId);
    }
    /* *************************************************Post/Create****************************************/

    //este es el Post
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public client save(@RequestBody client client){//requiéralo del cuerpo de la consulta

        return service.save(client);
    }
    /* *************************************************Put/Update****************************************/

    //este es el put
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public client update(@RequestBody client client){//requiéralo del cuerpo de la consulta
        return service.update(client);
    }
    /* *************************************************Delete****************************************/

    //este es el delete
    @DeleteMapping("/{id }")  //con @PathVariable le indicamos que le vamos a enviar una variable a través del parámetro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int clientId){

        return service.delete(clientId);
    }
}
