package com.school.dao;

import com.school.model.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EstudianteDao extends MongoRepository<Estudiante, String> { // Correcto uso de `String` para MongoDB
    Optional<Estudiante> findByUsernameAndPassword(String username, String password); // Método para buscar por usuario y contraseña
    Optional<Estudiante> findByDni(String dni); // Método para buscar por DNI
}
