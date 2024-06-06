package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;

@Document(collection = "asistencias")
public class Asistencia implements Serializable {

    @Id
    private String id; // ID como cadena para MongoDB
    
    private String fecha;

    private String estado;

    @DBRef
    private Estudiante estudiante; // Relación con otro documento

    public Asistencia() {
        // Constructor sin argumentos para serialización/deserialización
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    private static final long serialVersionUID = 1L; // Serializable
}
