package com.school.dao;

import com.school.model.Empleado;
import com.school.model.Clase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoDao extends MongoRepository<Empleado, String> { // Usando MongoRepository

    @Query("{ 'empleado.id': ?0 }") // Consulta para MongoDB
    List<Clase> findClasesProfesor(String id); // Cambiado a String

    Empleado findByDni(String dni); // Método para buscar por DNI
}
