package com.school.service;

import com.school.model.Grado;
import java.util.List;
import java.util.Optional;

// Interfaz para el servicio de grados
public interface GradoService {

    // Guarda un grado
    Grado save(Grado grado);

    // Encuentra un grado por ID como cadena (MongoDB)
    Optional<Grado> findById(String id);

    // Encuentra un grado por nombre
    Optional<Grado> findByNombre(String nombre); // Agregado

    // Devuelve todos los grados
    List<Grado> findAll();

    // Elimina un grado por su ID como cadena (MongoDB)
    boolean deleteById(String id);
}