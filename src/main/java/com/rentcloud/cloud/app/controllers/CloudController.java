package com.rentcloud.cloud.app.controllers;

import com.rentcloud.cloud.app.entities.client;
import com.rentcloud.cloud.app.entities.cloud;
import com.rentcloud.cloud.app.services.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/*
@RestController lo marcamos como controlador
@RequestMapping("/Cloud") le damos una URL base
@CrossOrigin(origins = "*") habilitamos para que pueda ser accedido por otro origen
public class CategoryController {//con esta clase vamos a acceder a los servicos
@Autowired//creamos objeto de la clase CategoryService y lo inyectamos
 */
@RestController
@RequestMapping("/Cloud")
@CrossOrigin(origins = "*")
public class CloudController {
    @Autowired
    private CloudService service;

    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/

    @GetMapping("/all")
    public List<cloud> getCloud(){
        return service.getAll();
    }

    //obtenemos el get con Id, le indicamos que le vamos a pasar un parámetro
    @GetMapping("/{id]")            //pasamos el Id por parámetro a la variable clientId
    public Optional<cloud> getCloud(@PathVariable("id") int cloudId){
        return service.getCloud(cloudId);
    }
    /* *************************************************Post/Create****************************************/

    //este es el Post
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public cloud save(@RequestBody cloud cloud){//requiéralo del cuerpo de la consulta

        return service.save(cloud);
    }
    /* *************************************************Put/Update****************************************/

    //este es el put
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public cloud update(@RequestBody cloud cloud){//requiéralo del cuerpo de la consulta
        return service.update(cloud);
    }
    /* *************************************************Delete****************************************/

    //este es el delete
    @DeleteMapping("/{id }")  //con @PathVariable le indicamos que le vamos a enviar una variable a través del parámetro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int cloudId){

        return service.delete(cloudId);
    }
}
