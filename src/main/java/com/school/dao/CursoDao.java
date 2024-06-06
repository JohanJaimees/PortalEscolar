package com.school.dao;

import com.school.model.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoDao extends MongoRepository<Curso, String> { // Usando MongoRepository
}
