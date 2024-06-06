package com.school.service;

import com.school.model.Aula;
import com.school.model.Clase;
import com.school.model.Estudiante;

import java.util.List;
import java.util.Optional;

// Define el contrato para el servicio de Aula
public interface AulaService {

    // Encuentra estudiantes por ID del aula
    List<Estudiante> findEstudiantesAula(String id);

    // Devuelve todas las aulas
    List<Aula> findAll();

    // Guarda el aula
    Aula save(Aula aula);

    // Encuentra aula por ID
    Optional<Aula> findById(String id);

    // Encuentra clases por ID del aula
    List<Clase> findClasesAula(String id);

    // Elimina aula por ID
    void delete(String id);
    
    
   
}
