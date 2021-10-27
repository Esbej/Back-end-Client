package com.rentcloud.cloud.app.services;

import com.rentcloud.cloud.app.entities.category;
import com.rentcloud.cloud.app.entities.message;
import com.rentcloud.cloud.app.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired//creamos objeto de la clase repository y lo inyectamos con Autowired
    private MessageRepository repository;

    /* ********************************Implementamos el CRUD**************************************************************/
    /* ************************************* GET o Create*****************************************/
    /*Este sería el Get y nos retorna una lista de administradores*/
    public List<message> getAll(){
        return repository.getAll();
    }

    /*Este sería el Get con Id*/
    public Optional<message> getMessage(int messageId){
        return repository.getMessage(messageId);
    }
    /* *************************************Post o Read**********************************************/

    public message save(message message){
        //consulta si no se envía el Id
        if(message.getIdMessage()==null){
            return repository.save(message);
        }else{//si envian el Id
            //consulta si existe el registro enviado creando un objeto de la clase Opcional de java.util
            Optional<message> existMessage = repository.getMessage(message.getIdMessage());
            //si los datos ya existen retorne los datos enviados
            if(existMessage.isPresent()){
                return message;//acá debería retornar una respuesta indicando que ya existe el dato
            }else{
                //si no existe regístrelo
                return repository.save(message);
            }
        }
    }
    /* ********************************Put o Update*************************************************************************/

    public message update(message message){
        //si el Id no es null, es decir si enviaron el Id
        if(message.getIdMessage()!=null){
            //obtener el admin por id creamos un objeto de la clase Optional de java.util
            Optional<message> existMessage= repository.getMessage(message.getIdMessage());
            //si el id del  admin existe
            if(existMessage.isPresent()){
                //si el campo messageText no es null, reemplazar con los datos enviados
                if(message.getMessageText()!=null){
                    existMessage.get().setMessageText(message.getMessageText());
                }


                //retorne los datos con el update implementado
                return repository.save(existMessage.get());

            }else{//si el admin no existe retorne los datos enviados,se debería enviar una notificación que los datos no existen
                return message;
            }

        }else{//si no se envío el Id retorne los datos enviados
            return message;
        }
    }
    /* ***************************************Delete**********************************************************/

    public boolean delete(int messageId){
        return getMessage(messageId).map(message -> {
            repository.delete(message);
            return true;
        }).orElse(false);
    }
}