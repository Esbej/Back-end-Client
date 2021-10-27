package com.rentcloud.cloud.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reservation")

public class reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    private Date startDate;
    private Date devolutionDate;
    private String status="created";

    /* *************************************** Empezamos a dar las relaciones entre tablas ****************************/
//muchas reservaciones una nube
    @ManyToOne
    @JoinColumn(name = "cloudId")
    @JsonIgnoreProperties("reservations")
    private cloud cloud;
//muchas reservaciones un cliente
    @ManyToOne
    //le damos un innerJoin con la tabla de cliente en el campo id
    @JoinColumn(name = "clientId")
    //le decimos que al traer la lista de clientes ignore la lista de reservas y mensajes para evitar redundancia de datos
    @JsonIgnoreProperties({"reservations","messages"})
    private client client;

    //una reservacion una calificaion
    @OneToOne(cascade = {CascadeType.REMOVE},mappedBy="reservation")
    //le decimos que al traer la lista de calificaciones ignore la lista de reservas para evitar redundancia de datos
    @JsonIgnoreProperties("reservation")
    private score score;

}
