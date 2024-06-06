package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.io.Serializable;

@Document(collection = "aulas")
public class Aula implements Serializable {

    @Id
    private String id; // ID como cadena para MongoDB

    @NotEmpty(message = "El nombre no puede estar vacío.")
    @Size(min = 2, max = 20, message = "Debe tener entre 2 y 20 caracteres.")
    private String nombre;

    @NotEmpty(message = "La sección no puede estar vacía.")
    @Size(min = 1, max = 10, message = "Debe tener entre 1 y 10 caracteres.")
    private String seccion;

    @NotNull(message = "La capacidad no puede estar vacía.")
    private Integer capacidad;

    @DBRef
    @NotNull(message = "El grado no puede estar vacío.")
    private Grado gradoAula; // Referencia a otro documento

    @NotEmpty(message = "El turno no puede estar vacío.")
    private String turno; // Turno como cadena

    @DBRef
    private Nivel nivel; // Referencia a otro documento

    public Aula() {
        // Constructor sin argumentos para serialización/deserialización
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

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Grado getGradoAula() {
        return gradoAula;
    }

    public void setGradoAula(Grado gradoAula) {
        this.gradoAula = gradoAula;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    private static final long serialVersionUID = 1L; // Serializable para compatibilidad con frameworks
}
