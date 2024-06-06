package com.school.security.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "usuarios") // Colección para MongoDB
public class NuevoUsuario {

    @NotBlank // Validación para campo no vacío
    private String username;

    @NotBlank // Validación para campo no vacío
    private String password;

    private Boolean enabled; // Campo booleano

    private Set<String> roles = new HashSet<>(); // Conjunto de roles

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
        this.password = password; // Asegúrate de cerrar la línea con un punto y coma
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled; // Verifica la estructura del método
    }

    public Set<String> getRoles() {
        return roles; // Verifica el tipo y asegúrate de cerrar la línea correctamente
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles; // Asegúrate de que no haya caracteres extraños
    }
}
