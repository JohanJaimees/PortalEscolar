package com.school.dao;

import com.school.model.Nivel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NivelDao extends MongoRepository<Nivel, String> {
    Optional<Nivel> findByNombre(String nombre);
}
