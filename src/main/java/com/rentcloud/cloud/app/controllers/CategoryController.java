package com.rentcloud.cloud.app.controllers;


import com.rentcloud.cloud.app.entities.category;
import com.rentcloud.cloud.app.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Category")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService service;

    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/

    @GetMapping("/all")
    public List<category> getCategory(){
        return service.getAll();
    }

    //obtenemos el get con Id, le indicamos que le vamos a pasar un parámetro
    @GetMapping("/{id]")            //pasamos el Id por parámetro a la variable clientId
    public Optional<category> getCategory(@PathVariable("id") int categoryId){
        return service.getCategory(categoryId);
    }
    /* *************************************************Post/Create****************************************/

    //este es el Post
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public category save(@RequestBody category category){//requiéralo del cuerpo de la consulta

        return service.save(category);
    }
    /* *************************************************Put/Update****************************************/

    //este es el put
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)//para retornar el estado(201)
    public category update(@RequestBody category category){//requiéralo del cuerpo de la consulta
        return service.update(category);
    }
    /* *************************************************Delete****************************************/

    //este es el delete
    @DeleteMapping("/{id }")  //con @PathVariable le indicamos que le vamos a enviar una variable a través del parámetro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int categoryId){

        return service.delete(categoryId);
    }
}
