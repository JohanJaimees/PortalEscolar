package com.school.dao;

import com.school.model.Grado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradoDao extends MongoRepository<Grado, String> {

    Optional<Grado> findByNombre(String nombre);
    
    // Otros métodos si es necesario
}
