package com.school.dao;

import com.school.model.Turno;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TurnoDao extends MongoRepository<Turno, String> {
    Optional<Turno> findByNombre(String nombre);
}
