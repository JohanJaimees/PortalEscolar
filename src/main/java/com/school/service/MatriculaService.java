package com.school.service;

import com.school.model.Matricula;
import com.school.model.Nivel;
import com.school.model.Turno;
import com.school.model.DiaSemana;
import com.school.model.Nota;

import java.util.List;
import java.util.Optional;

public interface MatriculaService {

    Matricula save(Matricula matricula); // Guarda una nueva matrícula

    Optional<Matricula> getMatriculaById(String id); // Encuentra matrícula por ID usando String

    List<Matricula> findAll(); // Devuelve todas las matrículas

    boolean delete(String id); // Elimina por ID usando String

    List<Nivel> getNiveles(); // Devuelve todos los niveles

    List<Turno> getTurnos(); // Devuelve todos los turnos

    List<DiaSemana> getDias(); // Devuelve todos los días de la semana

    List<Matricula> getMatriculasPorEstudiante(String idEstudiante); // Encuentra matrículas por ID del estudiante

    List<Nota> findNotasPorAulaYCurso(String idAula, String idCurso); // Encuentra notas por aula y curso
}
