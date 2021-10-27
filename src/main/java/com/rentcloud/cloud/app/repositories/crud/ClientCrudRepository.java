package com.rentcloud.cloud.app.repositories.crud;

import com.rentcloud.cloud.app.entities.category;
import com.rentcloud.cloud.app.entities.client;
import org.springframework.data.repository.CrudRepository;

/*en la interface definimos un modelo pero no lo implementamos, la implementación la hacemos en el Repository*/

public interface ClientCrudRepository extends CrudRepository<client,Integer> {//se hace referencia a la entidad que va a implementar el crud y el tipo de llave primaria Integer
    //post, get, put, delete==create,read,upload,delete
}
