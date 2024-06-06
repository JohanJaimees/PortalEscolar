package com.school.service;

import com.school.model.Turno;

import java.util.List;
import java.util.Optional;

public interface TurnoService {
    Turno save(Turno turno);
    Optional<Turno> findById(String id);
    Optional<Turno> findByNombre(String nombre);  // Aceptar un String
    List<Turno> findAll();
    void deleteById(String id);
}
