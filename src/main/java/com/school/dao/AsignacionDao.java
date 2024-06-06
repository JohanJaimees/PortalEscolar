package com.school.dao;

import com.school.model.Asignacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AsignacionDao extends MongoRepository<Asignacion, String> { // Usando MongoRepository
}
