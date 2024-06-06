package com.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "asignaciones") // Indica la colección en MongoDB
public class Asignacion implements Serializable {

    @Id
    private String id; // Clave primaria para MongoDB
    
    private String idClase; // Campo para ID de clase

    @NotEmpty(message = "El título no puede estar vacío.")
    private String titulo;

    @NotEmpty(message = "La descripción no puede estar vacía.")
    private String descripcion;

    @NotNull(message = "La fecha de inicio no puede estar vacía.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha de fin no puede estar vacía.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaFin;

    private LocalDate fechaCreada;

    private Boolean activo;

    @DBRef
    @NotNull(message = "La clase no puede estar vacía.")
    private Clase clase; // Referencia a otro documento MongoDB

    public Asignacion() {
        this.fechaCreada = LocalDate.now();
        this.activo = true;
    }

    // Getters y setters
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdClase() {
        return idClase; // Método para obtener el ID de clase
    }

    public void setIdClase(String idClase) {
        this.idClase = idClase; // Método para establecer el ID de clase
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaCreada() {
        return fechaCreada;
    }

    public void setFechaCreada(LocalDate fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    private static final long serialVersionUID = -5316124618822832560L; // Para Serializable
}
