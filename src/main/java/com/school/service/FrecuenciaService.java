package com.school.service;

import com.school.model.Frecuencia;
import java.util.List;
import java.util.Optional;

public interface FrecuenciaService {
    List<Frecuencia> findAll();
    Optional<Frecuencia> findById(String id);
    Frecuencia save(Frecuencia frecuencia); // Definir el método save
    void deleteById(String id);
    void deleteFrecuenciasNulas();
}
