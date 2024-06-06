package com.school.service;

import com.school.model.Especialidad;
import java.util.List;
import java.util.Optional;

// Interfaz para el servicio de especialidades
public interface EspecialidadService {

    Especialidad save(Especialidad especialidad); // Guarda una especialidad
    
    Optional<Especialidad> findById(String id); // Encuentra por ID como cadena
    
    List<Especialidad> findAll(); // Devuelve todas las especialidades
    
    Boolean deleteById(String id); // Elimina por ID como cadena
    
    // Agrega este método para buscar una especialidad por nombre
    Especialidad findByNombre(String nombre);
}
