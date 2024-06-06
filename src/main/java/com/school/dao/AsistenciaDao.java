package com.school.dao;

import com.school.model.Asistencia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaDao extends MongoRepository<Asistencia, String> { // Usamos MongoRepository

    List<Asistencia> findByFecha(String fecha); // Método para buscar por fecha

    @Query("{ 'fecha': ?0, 'estudiante.aulaEstudiante.id': ?1 }") // Consulta adaptada para MongoDB
    List<Asistencia> findAsistenciaByFechaAula(String fecha, String idAula);
}
