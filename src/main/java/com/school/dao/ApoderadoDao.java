package com.school.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.school.model.Apoderado;

@Repository
public interface ApoderadoDao extends MongoRepository<Apoderado, String> { // Usando MongoRepository

    Apoderado findByDni(String dni); // Método personalizado para buscar por DNI
}
