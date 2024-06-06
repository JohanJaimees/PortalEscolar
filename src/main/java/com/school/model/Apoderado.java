package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


@Document(collection = "apoderados")
public class Apoderado implements Serializable {

    @Id
    private String id; // ID como cadena para MongoDB
    
    @NotEmpty(message = "no puede estar vacío.")
    @Size(min = 2, max = 40, message = "tiene que ser entre 2 y 40 caracteres.")
    private String nombres;

    @Field(name = "apellido_paterno")
    @NotEmpty(message = "no puede estar vacío.")
    @Size(min = 2, max = 20, message = "tiene que ser entre 2 y 20 caracteres.")
    private String apellidoPaterno;
    
    @Field(name = "apellido_materno")
    @NotEmpty(message = "no puede estar vacío.")
    @Size(min = 2, max = 20, message = "tiene que ser entre 2 y 20 caracteres.")
    private String apellidoMaterno;
    
    @NotEmpty(message = "no puede estar vacío.")
    private String dni;
    
    @NotEmpty(message = "no puede estar vacío.")
    private String celular;

    public Apoderado() {
        // Constructor sin argumentos para serialización/deserialización
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    private static final long serialVersionUID = 1L; // Serializable para compatibilidad con algunos frameworks
}
