package com.rentcloud.cloud.app.services;

import com.rentcloud.cloud.app.entities.category;
import com.rentcloud.cloud.app.entities.score;
import com.rentcloud.cloud.app.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository repository;

    /* ********************************Implementamos el CRUD**************************************************************/
    /* ************************************* GET o Create*****************************************/
    /*Este sería el Get y nos retorna una lista de administradores*/
    public List<score> getAll(){
        return repository.getAll();
    }

    /*Este sería el Get con Id*/
    public Optional<score> getScore(int scoreId){
        return repository.getScore(scoreId);
    }
    /* *************************************Post o Read**********************************************/

    public score save(score score){
        //consulta si no se envía el Id
        if(score.getIdScore()==null){
            return repository.save(score);
        }else{//si envian el Id
            //consulta si existe el registro enviado creando un objeto de la clase Opcional de java.util
            Optional<score> existCategory = repository.getScore(score.getIdScore());
            //si los datos ya existen retorne los datos enviados
            if(existCategory.isPresent()){
                return score;//acá debería retornar una respuesta indicando que ya existe el dato
            }else{
                //si no existe regístrelo
                return repository.save(score);
            }
        }
    }
    /* ********************************Put o Update*************************************************************************/

    public score update(score score){
        //si el Id no es null, es decir si enviaron el Id
        if(score.getIdScore()!=null){
            //obtener el admin por id creamos un objeto de la clase Optional de java.util
            Optional<score> existScore= repository.getScore(score.getIdScore());
            //si el id del  admin existe
            if(existScore.isPresent()){
                //si el campo nombre no es null, reemplazar con los datos enviados
                if(score.getMessageText()!=null){
                    existScore.get().setMessageText(score.getMessageText());
                }

                //si el campo descripción no es null, reemplazar con los datos enviados
                if(score.getStars()!=null){
                    existScore.get().setStars(score.getStars());
                }
                //retorne los datos con el update implementado
                return repository.save(existScore.get());

            }else{//si el admin no existe retorne los datos enviados,se debería enviar una notificación que los datos no existen
                return score;
            }

        }else{//si no se envío el Id retorne los datos enviados
            return score;
        }
    }
    /* ********************************Delete*************************************************************************/

    public boolean delete(int scoreId){
        return getScore(scoreId).map(score->{
            repository.delete(score);
            return true;
        }).orElse(false);
    }
}