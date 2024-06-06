package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "cursos")
public class Curso implements Serializable {

    @Id
    private String id; // ID como cadena para MongoDB
    
    @NotEmpty(message = "El nombre no puede estar vacío.")
    @Size(min = 2, max = 20, message = "Debe tener entre 2 y 20 caracteres.") // Validación para el campo 'nombre'
    private String nombre;

    @NotEmpty(message = "La descripción no puede estar vacía.") // Validación para el campo 'descripcion'
    private String descripcion; // Nuevo campo 'descripcion'

    @DBRef
    private List<Clase> clases = new ArrayList<>(); // Referencias a otros documentos

    public Curso() {
        // Constructor sin argumentos para serialización/deserialización
    }

    // Getters y setters para todos los campos

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

    public String getDescripcion() { // Getter para 'descripcion'
        return descripcion;
    }

    public void setDescripcion(String descripcion) { // Setter para 'descripcion'
        this.descripcion = descripcion;
    }

    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    private static final long serialVersionUID = 1L; // Asegura compatibilidad con serialización
}
