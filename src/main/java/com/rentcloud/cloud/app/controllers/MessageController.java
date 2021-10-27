package com.rentcloud.cloud.app.controllers;

import com.rentcloud.cloud.app.entities.client;
import com.rentcloud.cloud.app.entities.message;
import com.rentcloud.cloud.app.services.ClientService;
import com.rentcloud.cloud.app.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Message")
@CrossOrigin(origins = "*")

public class MessageController {

    @Autowired
    private MessageService service;

    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/

    @GetMapping("/all")
    public List<message> getMessage(){
        return service.getAll();
    }

    //obtenemos el get con Id, le indicamos que le vamos a pasar un parámetro
    @GetMapping("/{id]")            //pasamos el Id por parámetro a la variable clientId
    public Optional<message> getMessage(@PathVariable("id") int messageId){
        return service.getMessage(messageId);
    }
    /* *************************************************Post/Create****************************************/

    //este es el Post
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public message save(@RequestBody message message){//requiéralo del cuerpo de la consulta

        return service.save(message);
    }
    /* *************************************************Put/Update****************************************/

    //este es el put
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public message update(@RequestBody message message){//requiéralo del cuerpo de la consulta
        return service.update(message);
    }
    /* *************************************************Delete****************************************/

    //este es el delete
    @DeleteMapping("/{id }")  //con @PathVariable le indicamos que le vamos a enviar una variable a través del parámetro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int messageId){

        return service.delete(messageId);
    }
}
