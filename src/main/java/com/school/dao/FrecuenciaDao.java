package com.school.dao;

import com.school.model.Frecuencia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrecuenciaDao extends MongoRepository<Frecuencia, String> { // Usando MongoRepository

    @Query("{ 'clase': null }") // Consulta para MongoDB
    List<Frecuencia> findAllFrecuenciaNulos(); // Buscando documentos donde 'clase' es null
}
