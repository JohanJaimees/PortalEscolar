package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Document(collection = "grados")
public class Grado implements Serializable {

    @Id
    private String id; // ID como cadena para MongoDB
    
    @NotEmpty(message = "El nombre no puede estar vacío.")
    @Size(min = 2, max = 20, message = "Debe tener entre 2 y 20 caracteres.")
    private String nombre; // Campo simple

    // Constructor que acepta una cadena de texto como argumento
    public Grado(String nombre) {
        this.nombre = nombre;
    }

    // Constructor por defecto requerido por Spring Data MongoDB
    public Grado() {
        // Constructor por defecto requerido por Spring Data MongoDB
    }

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

    // Definición de serialVersionUID sin caracteres no válidos
    private static final long serialVersionUID = 1L;
}