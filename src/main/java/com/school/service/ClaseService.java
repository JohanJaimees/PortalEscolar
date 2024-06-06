package com.school.service;

import com.school.model.Clase;
import com.school.model.Asignacion; // Asegúrate de importar esta clase
import com.school.model.Aula;

import java.util.List;
import java.util.Optional;

// Interfaz para el servicio de clases
public interface ClaseService {

    Clase save(Clase clase);

    Clase update(String id, Clase clase); // Definir método para actualizar por ID

    Optional<Clase> findById(String id);

    List<Clase> findAll();

    boolean delete(String id);

    byte[] generarReporteCurso(String formato, String idCurso, String idGrado); // Definir método para generar reporte

    List<Asignacion> asignacionesPorClase(String idClase); // Método para obtener asignaciones por clase
    
    List<Clase> findByAula(Aula aula);
}
