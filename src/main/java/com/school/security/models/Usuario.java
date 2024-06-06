package com.school.security.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "usuarios") // Indica que esta clase es un documento de MongoDB
public class Usuario implements Serializable {

    @Id
    private String id; // ID como cadena para MongoDB
    
    
    private String username; // Campo simple
    
 
    private String password; // Campo simple
    
    private Boolean enabled; // Campo simple
    
    @DBRef
    private Set<Rol> roles = new HashSet<>(); // Relación con otros documentos

    // Constructor sin argumentos para deserialización
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(@NotEmpty String username,
                   @NotEmpty String password, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    // Getters y setters completos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username; // Reemplazar "es" por "="
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; // Corregir sintaxis
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled; // Corregir sintaxis
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles; // Corregir sintaxis
    }
}
