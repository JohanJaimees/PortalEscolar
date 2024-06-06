package com.school.dao;

import com.school.model.RespuestaAsignacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespuestAsignacionDao extends MongoRepository<RespuestaAsignacion, String> { // Cambiado a MongoRepository

    @Query("{ 'asignacion.id': ?0 }") // Consulta para MongoDB
    List<RespuestaAsignacion> findAllByAsignacion(String idAsignacion); // Cambiado a String

    @Query("{ 'dniEstudiante': ?0, 'asignacion.id': ?1 }") // Consulta para MongoDB
    Optional<RespuestaAsignacion> findPorDniEstudianteAndAsignacion(String dniEstudiante, String idAsignacion); // Clave primaria es String
}
