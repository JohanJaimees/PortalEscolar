package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@Document(collection = "especialidades")
public class Especialidad implements Serializable {

    @Id
    private String id; // ID como cadena para MongoDB
    
    private String nombre; // Declaración de campo

    // Constructor con @JsonCreator y @JsonProperty
    @JsonCreator
    public Especialidad(@JsonProperty("nombre") String nombre) {
        this.nombre = nombre;
    }

    // Constructor vacío necesario para la deserialización
    public Especialidad() {}

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private static final long serialVersionUID = 1L;
}
