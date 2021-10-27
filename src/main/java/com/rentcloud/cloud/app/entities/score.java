package com.rentcloud.cloud.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="score")

public class score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idScore;
    @Column(length=250)
    private String messageText;
    private Integer stars;

    /* *************************************** Empezamos a dar las relaciones entre tablas ****************************/
//relación uno a uno, una calificación una reservación
    @OneToOne
    //le decimos que al traer la lista de reservas ignore la lista de calificaciones para evitar redundancia de datos
    @JsonIgnoreProperties("score")
    //creamos un objeto de la clase reservation para mostrar la reserva a la que hace referencia esta calificación
    private reservation reservation;

}
