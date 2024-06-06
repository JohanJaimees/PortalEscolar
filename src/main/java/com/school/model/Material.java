package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "materiales")
public class Material implements Serializable {

    @Id
    private String id; // ID como cadena para MongoDB
    
    private String nombre; // Campo simple
    
    private String archivo; // Campo simple
    
    private Date createAt; // Fecha de creación inicializada en el constructor

    public Material() {
        this.createAt = new Date(); // Inicialización correcta
    }

    // Getters y setters completos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id; // Asignación correcta
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Corregir asignación
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo; // Corregir asignación
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt; // Corregir asignación
    }

    // Definición correcta de serialVersionUID
    private static final long serialVersionUID = 1L; // Corregir asignación
}
