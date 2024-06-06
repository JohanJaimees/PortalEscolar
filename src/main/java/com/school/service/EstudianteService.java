package com.school.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.school.model.Estudiante;

public interface EstudianteService {
    
    Estudiante save(Estudiante estudiante);

    List<Estudiante> saveAll(List<Estudiante> estudiantes);

    Optional<Estudiante> getEstudianteById(String id);

    List<Estudiante> findAll();

    Page<Estudiante> findAll(Pageable pageable);
    
    boolean delete(String id);

    Optional<Estudiante> findByUsernameAndPassword(String username, String password); // Usa Optional
    
    Optional<Estudiante> findByDni(String dni); // Usa Optional
    
    Estudiante update(String id, Estudiante estudiante);

    Estudiante loginUsuario(String username, String password); 
   
}

