package com.rentcloud.cloud.app.controllers;

import com.rentcloud.cloud.app.entities.admin;
import com.rentcloud.cloud.app.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController//le indicamos que es un controlador, encargado de gestionar las peticiones o HTTP Request, define la URL
@RequestMapping("Admin")//le damos una URL base, no importa si lleva o no el / antes
@CrossOrigin(origins = "*")

//para acceder a los servicios
public class AdminController {

    //Metodos
    @Autowired
    private AdminService service;//creamos objeto de tipo AdminService
    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/
    //get
    @GetMapping("/all")
    public List<admin> getAdmin(){
        return service.getAll();
    }

    //obtenemos el get con Id, le indicamos que le vamos a pasar un parámetro
    @GetMapping("/{id]")            //pasamos el Id por parámetro a la variable adminId
    public Optional<admin> getAdmin(@PathVariable("id") int adminId){
      return service.getAdmin(adminId);
    }
    /* *************************************************Post/Create****************************************/

    //este es el Post
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public admin save(@RequestBody admin admin){//requiéralo del cuerpo de la consulta
        return service.save(admin);
    }
    /* *************************************************Put/Update****************************************/

    //este es el put
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public admin update(@RequestBody admin admin){//requiéralo del cuerpo de la consulta
        return service.update(admin);
    }
    /* *************************************************Delete****************************************/

    //este es el delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//Retorna Status 204
    public boolean delete(@PathVariable("id") int adminId){//con @PathVariable le indicamos que le vamos a enviar una variable a través del parámetro

        return service.delete(adminId);
    }

}


