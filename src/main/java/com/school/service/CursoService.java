// CursoService.java
package com.school.service;

import com.school.model.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoService {

    Curso save(Curso curso); // Guarda un curso

    Optional<Curso> findById(String id); // Encuentra un curso por ID

    List<Curso> findAll(); // Devuelve todos los cursos

    boolean delete(String id); // Elimina un curso por ID
    
    Curso update(String id, Curso curso); // Actualiza un curso por ID y datos
}
