package com.school.service;

import com.school.model.Nivel;

import java.util.List;
import java.util.Optional;

public interface NivelService {
    Nivel save(Nivel nivel);
    Optional<Nivel> findById(String id);
    Optional<Nivel> findByNombre(String nombre);  // Aceptar un String
    List<Nivel> findAll();
    void deleteById(String id);
}
