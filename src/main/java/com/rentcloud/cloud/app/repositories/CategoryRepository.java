package com.rentcloud.cloud.app.repositories;


import com.rentcloud.cloud.app.entities.category;
import com.rentcloud.cloud.app.repositories.crud.CategoryCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    @Autowired
    private CategoryCrudRepository repository;

    /* **********************************Creamos el CRUD *******************************************************************/

    /*Select * from
     * retorna todos los registros*/
    public List<category> getAll(){
        return (List<category>) repository.findAll();//busca todos los registros de la tabla admin
    }

    /*select * from table where ID=id
     * retorna un registro por el id*/
    //devuelve algo opcional devuelve nulo o e valor
    public Optional<category> getCategory(int id){
        return repository.findById(id);
    }

    /*Insert y Update
     * actualiza o crea nuevo registro*/
    public category save(category category){
        return repository.save(category);
    }

    /*delete from table*/
    public void delete(category category){
        repository.delete(category);
    }
}
