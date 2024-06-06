package com.school.dao;

import com.school.model.Aula;
import com.school.model.Clase;
import com.school.model.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulaDao extends MongoRepository<Aula, String> {
    @Query("{ 'aulaEstudiante.id': ?0 }")
    List<Estudiante> findEstudiantesAula(String id);

    @Query("{ 'aula.id': ?0 }")
    List<Clase> findClasesAula(String id);

    @Query("{ 'gradoAula.id': ?0 }")
    List<Aula> findAulaPorGrado(String id);
}
