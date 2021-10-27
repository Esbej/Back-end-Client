package com.rentcloud.cloud.app.services;

import com.rentcloud.cloud.app.entities.cloud;
import com.rentcloud.cloud.app.repositories.CloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudService {

    @Autowired//creamos objeto de la clase repository y lo inyectamos
    private CloudRepository repository;

    /* ********************************Implementamos el CRUD**************************************************************/
    /* ************************************* GET o Read*****************************************/
    /*Este sería el Get y nos retorna una lista de administradores*/
    public List<cloud> getAll(){
        return repository.getAll();
    }

    /*Este sería el Get con Id*/
    public Optional<cloud> getCloud(int cloudId){
        return repository.getCloud( cloudId);
    }
    /* *************************************Post o Create**********************************************/

    public cloud save(cloud cloud){
        //consulta si no se envía el Id
        if(cloud.getId() == null){
            return repository.save(cloud);
        }else{//si  envian el Id
            //consulta si existe el registro enviado creando un objeto de la clase Opcional de java.util
            Optional<cloud> existCloud = repository.getCloud(cloud.getId());
            //si los datos ya existen retorne los datos enviados
            if(existCloud.isPresent()){
                return cloud;//acá debería retornar una respuesta indicando que ya existe el dato
            }else{
                //si no existe regístrelo
                return repository.save(cloud);
            }
        }
    }
/* *********************************************Put o Update************************************************/

public cloud update(cloud cloud){
    //si el Id no es null, es decir si enviaron el Id
    if(cloud.getId()!=null){
        //obtener el admin por id
        Optional<cloud> existCloud= repository.getCloud(cloud.getId());
        //si cloud existe
        if(existCloud.isPresent()){
            //si el campo nombre no es null, reemplazar con los datos enviados
            if(cloud.getName()!=null){
                existCloud.get().setName(cloud.getName());
            }
            if(cloud.getBrand()!=null){
                existCloud.get().setBrand(cloud.getBrand());
            }
            if(cloud.getYear()!=null){
                existCloud.get().setYear(cloud.getYear());
            }
            if(cloud.getDescription()!=null){
                existCloud.get().setDescription(cloud.getDescription());
            }

            //retorne los datos con el update implementado
            return repository.save(existCloud.get());

        }else{//si cloud no existe retorne los datos enviados,se debería enviar una notificación que los datos no existen
            return cloud;
        }

    }else{//si no se envío el Id retorne los datos enviados
        return cloud;
    }
}
/* *************************************Delete******************************************************/

public boolean delete(int cloudId){
    //si obtiene el id, lo borramos y retornamos true
    return getCloud(cloudId).map(cloud->{
        repository.delete(cloud);//ejecutamos una función anónima para eliminarlo y retornamos true
        return true;
    }).orElse(false);//si no obtiene el id retorna false

    //si obtiene Id, los mapea a una variable
        /* boolean respuesta=getAdmin(adminId).map(admin->{
          repository.delete(admin);//ejecutamos una función anónima para eliminarlo y retornamos true
          return true;
       }).orElse(false);//ni no lo elimina devuelve un false
        return respuesta;
    }*/
}
}
