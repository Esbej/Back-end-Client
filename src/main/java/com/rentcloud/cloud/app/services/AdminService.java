package com.rentcloud.cloud.app.services;

import com.rentcloud.cloud.app.entities.admin;
import com.rentcloud.cloud.app.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//nos indica que es la capa de servicios, actualiza el repositorio
public class AdminService {
    @Autowired//le inyectamos las dependencias
    private AdminRepository repository;
    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/
    /*Este sería el Get y nos retorna una lista de administradores*/
    public List<admin> getAll(){
        return repository.getAll();
    }


    /*Este sería el Get con Id*/
    public Optional<admin> getAdmin(int adminId){
        return repository.getAdmin(adminId);
    }

    /* *************************************************post/Create****************************************/

    /*sería el Post*/
    public admin save(admin admin){
        //consulta si no se envía el Id
        if(admin.getIdAdmin()==null){
            return repository.save(admin);
        }else{//si envian el Id
            //consulta si existe el registro enviado creando un objeto de la clase Opcional de java.util
            Optional<admin> existAdmin = repository.getAdmin(admin.getIdAdmin());
            //si los datos ya existen retorne los datos enviados
            if(existAdmin.isPresent()){
                return admin;//acá debería retornar una respuesta indicando que ya existe el dato
            }else{
                //si no existe regístrelo
                return repository.save(admin);
            }
        }
    }
    /* *************************************************Put/Update****************************************/
    public admin update(admin admin){
        //si el Id no es null, es decir si enviaron el Id
        if(admin.getIdAdmin()!=null){
            //obtener el admin por id
            Optional<admin> existAdmin= repository.getAdmin(admin.getIdAdmin());
            //si el admin existe
            if(existAdmin.isPresent()){
                //si el campo nombre no es null, reemplazar con los datos enviados
                if(admin.getName()!=null){
                    existAdmin.get().setName(admin.getName());
                }
                //si el campo email no es null, reemplazar con los datos enviados
                if(admin.getEmail()!=null){
                    existAdmin.get().setEmail(admin.getEmail());
                }
                //si el campo password no es null, reemplazar con los datos enviados
                if(admin.getPassword()!=null){
                    existAdmin.get().setPassword(admin.getPassword());
                }
                //retorne los datos con el update implementado
                return repository.save(existAdmin.get());

            }else{//si el admin no existe retorne los datos enviados,se debería enviar una notificación que los datos no existen
                return admin;
            }

        }else{//si no se envío el Id retorne los datos enviados
            return admin;
        }
    }
    /* *************************************************Delete****************************************/


    /*este sería el Delete*/
    public boolean delete(int adminId){
        //si obtiene el id, lo borramos y retornamos true
        return getAdmin(adminId).map(admin->{
           repository.delete(admin);//ejecutamos una función anónima para eliminarlo y retornamos true
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
