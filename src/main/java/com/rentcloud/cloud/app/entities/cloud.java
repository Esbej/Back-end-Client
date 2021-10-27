package com.rentcloud.cloud.app.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//************usamos Lonbok para ahorrarnos los getters y setters******************************************************

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//*******************************************************************************************************************
/*
@Data//genera getters y setters
@Entity //le indicamos que es una entidad o tabla y que es serializable
@NoArgsConstructor//genera constructor sin argumentos
@AllArgsConstructor//genera constructor con argumentos
@Table(name = "cloud")//le indicamos que es una tabla y le damos el nombre

 */
@Table(name = "cloud")
public class cloud implements Serializable {
    /*
    *le damos la anotación de id y generated para la clave primaria
    * y column para darle caracteristicas a las tablas
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name",length=45)
    private String name;
    @Column(name="brand",length=45)
    private String brand;
    @Column(name="year",length=4)
    private Integer year;
    @Column(name="description",length=250)
    private String description;

    /* *************************************** Empezamos a da las relaciones entre tablas ****************************/
    /*
    creamos un objeto de tipo category para traer los datos de la categoria y le damos un join con el id de category
    @JsonIgnoreProperties("clouds") para que no traiga redundancia de datos cuando se use category
    relación muchas nubes pueden tener una categoria
     */
    @ManyToOne
    @JsonIgnoreProperties("clouds")
    @JoinColumn(name="idCategory")
    private category category;

    /*
    relación una nube muchos mensajes
    creamos objeto de tipo messages para traer los mensajes de esa nube
    @JsonIgnoreProperties({"cloud","client"}) le decinos que ignore las nubes y los clientes para evitar redundancia
     */
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "cloud")
    @JsonIgnoreProperties({"cloud","client"})
    private List<message> messages;
    /*
        relación una nube muchas reservaciones
        creamos objeto de tipo reservation para traer las reservaciones de esa nube
        @JsonIgnoreProperties({"cloud","messages"}) le decinos que ignore las nubes y los mensajes para evitar redundancia
         */
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "cloud")
    @JsonIgnoreProperties({"cloud","messages"})
    public List<reservation> reservations;


}
