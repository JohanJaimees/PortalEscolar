package com.school.service;

import com.school.model.RespuestaAsignacion;

import java.util.List;
import java.util.Optional;

public interface RespuestaAsignacionService {

    // Guarda una RespuestaAsignacion vinculada a una asignación específica
    RespuestaAsignacion save(RespuestaAsignacion respuestaAsignacion, String idAsignacion);

    // Actualiza una RespuestaAsignacion existente
    RespuestaAsignacion update(RespuestaAsignacion respuestaAsignacion);

    // Encuentra una RespuestaAsignacion por su ID
    Optional<RespuestaAsignacion> findById(String id);

    // Encuentra todas las RespuestaAsignacion para una asignación específica
    List<RespuestaAsignacion> findAllByAsignacion(String idAsignacion);

    // Elimina una RespuestaAsignacion por su ID
    boolean delete(String id);

    // Encuentra una RespuestaAsignacion por DNI de estudiante y ID de asignación
    Optional<RespuestaAsignacion> findPorDniEstudianteAndAsignacion(String dniEstudiante, String idAsignacion);
}
