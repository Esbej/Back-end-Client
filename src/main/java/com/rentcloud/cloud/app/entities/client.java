package com.rentcloud.cloud.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//usamos Lonbok para ahorrarnos los getters y setters
@Data//genera getters y setters
@NoArgsConstructor//genera constructor sin argumentos
@AllArgsConstructor//genera constructor con todos los argumentos

@Entity //le indicamos que es una entidad o tabla y que es serializable
@Table(name = "client")//le indicamos que es una tabla y le damos el nombre
public class client implements Serializable {

    @Id//le indicamos que es la llave principal
    @GeneratedValue(strategy = GenerationType.IDENTITY)//generar auto incremento
    private Integer idClient;
    //le damos los demas atributos de la tabla client
    @Column(name="email",length=45)
    private String email;
    @Column(name="password",length=45)
    private String password;
    @Column(name = "name",length=250)
    private String name;
    @Column(name = "age")
    private Integer age;

    /* *************************************** Empezamos a dar las relaciones entre tablas ****************************/
//relación uno a muchos, un cliente muchos mensajes
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="client")
    //le decimos que ignore la lista de clientes al traer la lista de mensajes para evitar la redundancia de datos
    @JsonIgnoreProperties("client")
    public List<message> messages;

//relación uno a muchos, un cliente muchas reservaciones
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="client")
    //le decimos que ignore la lista de clientes al traer la lista de reservaciones para evitar la redundancia de datos
    @JsonIgnoreProperties("client")
    //creamos una lista de tipo reservation
    public List<reservation>reservations;
}
