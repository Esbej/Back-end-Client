package com.rentcloud.cloud.app.services;

import com.rentcloud.cloud.app.entities.category;
import com.rentcloud.cloud.app.entities.reservation;
import com.rentcloud.cloud.app.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    /* ********************************Implementamos el CRUD**************************************************************/
    /* ************************************* GET o Create*****************************************/
    /*Este sería el Get y nos retorna una lista de administradores*/
    public List<reservation> getAll(){
        return repository.getAll();
    }

    /*Este sería el Get con Id*/
    public Optional<reservation> getReservation(int reservationId){
        return repository.getReservation(reservationId);
    }
    /* *************************************Post o Read**********************************************/

    public reservation save(reservation reservation){
        //consulta si no se envía el Id
        if(reservation.getIdReservation()==null){
            return repository.save(reservation);
        }else{//si envian el Id
            //consulta si existe el registro enviado creando un objeto de la clase Opcional de java.util
            Optional<reservation> existReservation = repository.getReservation(reservation.getIdReservation());
            //si los datos ya existen retorne los datos enviados
            if(existReservation.isPresent()){
                return reservation;//acá debería retornar una respuesta indicando que ya existe el dato
            }else{
                //si no existe regístrelo
                return repository.save(reservation);
            }
        }
    }
    /* ********************************Put o Update*************************************************************************/

    public reservation update(reservation reservation){
        //si el Id no es null, es decir si enviaron el Id
        if(reservation.getIdReservation()!=null){
            //obtener el admin por id creamos un objeto de la clase Optional de java.util
            Optional<reservation> existReservation= repository.getReservation(reservation.getIdReservation());
            //si el id del  admin existe
            if(existReservation.isPresent()){
                //si el campo StartDate no es null, reemplazar con los datos enviados
                if(reservation.getStartDate()!=null){
                    existReservation.get().setStartDate(reservation.getStartDate());
                }

                //si el campo devoliutionDate no es null, reemplazar con los datos enviados
                if(reservation.getDevolutionDate()!=null){
                    existReservation.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    existReservation.get().setStatus(reservation.getStatus());
                }
                //retorne los datos con el update implementado
                return repository.save(existReservation.get());

            }else{//si  reservation no existe retorne los datos enviados,se debería enviar una notificación que los datos no existen
                return reservation;
            }

        }else{//si no se envío el Id retorne los datos enviados
            return reservation;
        }
    }
    /* ***************************************Delete**********************************************************/

    public boolean delete(int reservationId){
        //si obtiene el id, lo borramos y retornamos true
        return getReservation(reservationId).map(reservation ->{
            repository.delete(reservation);//ejecutamos una función anónima para eliminarlo y retornamos true
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
