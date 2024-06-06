package com.school.dao;

import com.school.model.Especialidad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadDao extends MongoRepository<Especialidad, String> {
    Especialidad findByNombre(String nombre);
}
