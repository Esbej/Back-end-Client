package com.rentcloud.cloud.app.services;

import com.rentcloud.cloud.app.entities.category;
import com.rentcloud.cloud.app.entities.client;
import com.rentcloud.cloud.app.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired//creamos un objeto de la clase repository u le inyectamos los datos con @Autowired
    private ClientRepository repository;

    /* ********************************Implementamos el CRUD**************************************************************/
    /* ************************************* GET o Create*****************************************/
    /*Este sería el Get y nos retorna una lista de clientes*/
    public List<client> getAll(){
        return repository.getAll();
    }

    /*Este sería el Get con Id*/
    public Optional<client> getClient(int clientId){
        return repository.getClient(clientId);
    }
    /* *************************************Post o Read**********************************************/

    public client save(client client){
        //consulta si no se envía el Id
        if(client.getIdClient() == null){
            return repository.save(client);
        }else{//si envian el Id
            //consulta si existe el registro enviado creando un objeto de la clase Opcional de java.util
            Optional<client> existCategory = repository.getClient(client.getIdClient());
            //si los datos ya existen retorne los datos enviados
            if(existCategory.isPresent()){
                return client;//acá debería retornar una respuesta indicando que ya existe el dato
            }else{
                //si no existe regístrelo
                return repository.save(client);
            }
        }
    }
    /* ********************************Put o Update*************************************************************************/

    public client update(client client){
        //si el Id no es null, es decir si enviaron el Id
        if(client.getIdClient()!=null){
            //obtener el admin por id creamos un objeto de la clase Optional de java.util
            Optional<client> existCategory= repository.getClient(client.getIdClient());
            //si el id del cliente existe
            if(existCategory.isPresent()){
                //si el campo nombre no es null, reemplazar con los datos enviados
                if(client.getName()!=null){
                    existCategory.get().setName(client.getName());
                }

                //si el campo descripción no es null, reemplazar con los datos enviados
                if(client.getEmail()!=null){
                    existCategory.get().setEmail(client.getEmail());
                }
                if(client.getPassword()!=null){
                    existCategory.get().setPassword(client.getPassword());
                }
                if(client.getAge()!=null){
                    existCategory.get().setAge(client.getAge());
                }
                //retorne los datos con el update implementado
                return repository.save(existCategory.get());

            }else{//si el admin no existe retorne los datos enviados,se debería enviar una notificación que los datos no existen
                return client;
            }

        }else{//si no se envío el Id retorne los datos enviados
            return client;
        }
    }
    /* ***************************************Delete**********************************************************/

    public boolean delete(int clientId){
        return getClient(clientId).map(client->{
            repository.delete(client);
            return true;
        }).orElse(false);
    }
}