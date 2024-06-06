package com.school.security.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.school.security.enums.RolNombre;

import jakarta.validation.constraints.NotNull;

@Document(collection = "roles") // Indica que esta clase es un documento de MongoDB
public class Rol {

    @Id
    private String id; // ID como cadena para MongoDB
    
    @NotNull
    private RolNombre rolNombre; // Campo simple que puede ser una enumeración
    
    // Constructor sin argumentos para deserialización
    public Rol() {
    }

    // Constructor con parámetros
    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    // Getters y setters completos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id; // Corregir error de sintaxis
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre; // Corregir error de sintaxis
    }
}


