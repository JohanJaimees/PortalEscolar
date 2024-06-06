package com.school.dao;

import com.school.model.Matricula;
import com.school.model.Nivel;
import com.school.model.Turno;
import com.school.model.DiaSemana;
import com.school.model.Nota;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaDao extends MongoRepository<Matricula, String> {

    @Query("{ }") // Para obtener todos los documentos de Turno
    List<Turno> getTurnos();

    @Query("{ }") // Para obtener todos los documentos de Nivel
    List<Nivel> getNiveles();

    @Query("{ }") // Para obtener todos los documentos de DiaSemana
    List<DiaSemana> getDias();

    @Query("{ 'estudiante.id': ?0 }") // Encuentra matrículas por ID de estudiante
    List<Matricula> getMatriculasPorEstudiante(String idEstudiante);

    @Query("{ 'aula.id': ?0, 'curso.id': ?1 }") // Encuentra notas por aula y curso
    List<Nota> findNotasPorAulaYCurso(String idAula, String idCurso);
}
