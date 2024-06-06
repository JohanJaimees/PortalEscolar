package com.school.service;

import com.school.model.Asignacion;
import java.util.Optional;

// Define la interfaz para el servicio de asignación
public interface AsignacionService {

    // Método para guardar una asignación, con idClase como parámetro adicional
    Asignacion save(Asignacion asignacion, String idClase);

    // Método para encontrar una asignación por su ID
    Optional<Asignacion> findById(String id);

    // Método para actualizar una asignación
    Asignacion update(Asignacion asignacion);
    
    boolean deleteById(String id); 
}
