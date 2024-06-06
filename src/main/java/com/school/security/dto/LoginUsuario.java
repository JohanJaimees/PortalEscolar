package com.school.security.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "usuarios") // Nombre de la colección en MongoDB
public class LoginUsuario {

    @NotBlank // Asegurarse de que el campo no esté vacío
    private String username;

    @NotBlank // Validación para campo no vacío
    private String password;

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; // Asegurarse de que no haya caracteres invisibles
    }
}
