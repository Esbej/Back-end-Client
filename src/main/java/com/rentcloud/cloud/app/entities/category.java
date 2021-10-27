package com.rentcloud.cloud.app.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="category")

public class category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",length=45)
    private String name;
    @Column(name="description",length=250)
    private String description;
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy= "category")
    @JsonIgnoreProperties("category")

    private List<cloud> clouds;
}