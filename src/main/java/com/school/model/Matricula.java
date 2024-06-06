package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Document(collection = "matriculas")
public class Matricula implements Serializable {

    @Id
    private String id;

    @NotEmpty(message = "no puede estar vacío")
    private String anio;

    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 8, max = 30, message = "tiene que ser entre 8 y 30 caracteres.")
    private String iEProcedencia;

    private String detalle;

    @NotNull(message = "no puede estar vacío")
    private Estudiante estudiante;

    public Matricula() {
        // Constructor vacío necesario para las operaciones de persistencia
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getiEProcedencia() {
        return iEProcedencia;
    }

    public void setiEProcedencia(String iEProcedencia) {
        this.iEProcedencia = iEProcedencia;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    private static final long serialVersionUID=1L;
}