package com.school.dao;

import com.school.model.Nota;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaDao extends MongoRepository<Nota, String> { // Cambiado a MongoRepository

    @Query("{ 'curso.id': ?0, 'estudiante.aulaEstudiante.id': ?1 }") // Consulta adaptada para MongoDB
    List<Nota> notasPorAulaYCurso(String idCurso, String idAula); // Cambiado a String
}
