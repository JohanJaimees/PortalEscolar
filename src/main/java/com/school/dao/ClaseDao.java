package com.school.dao;

import com.school.model.Clase;
import com.school.model.Asignacion;
import com.school.model.Aula;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaseDao extends MongoRepository<Clase, String> {

    @Query("{ 'curso.id': ?0, 'aula.id': ?1 }")
    Clase findClasePorAulaYCurso(String idCurso, String idAula);

    @Query("{ 'clase.id': ?0 }")
    List<Asignacion> asignacionesPorClase(String idClase);

    @Query("{ 'aula.id': ?0 }") // Consulta para encontrar clases por aula
    List<Clase> findByAula(String idAula);
}
