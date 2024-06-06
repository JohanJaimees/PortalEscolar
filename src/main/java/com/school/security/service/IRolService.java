package com.school.security.service;

import java.util.Optional;

import com.school.security.enums.RolNombre;
import com.school.security.models.Rol;

public interface IRolService { // Define la interfaz para el servicio de roles

    Optional<Rol> findByRolNombre(RolNombre rolNombre); // Encuentra un rol por su nombre
    
    void save(Rol rol); // Guarda un rol en la base de datos
}
