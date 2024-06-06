package com.school.controller;

import com.school.model.*;
import com.school.service.MatriculaService;
import com.school.service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import jakarta.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private ClaseService claseService;

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> saveMatricula(@Valid @RequestBody Matricula matricula, BindingResult results) {
        Map<String, Object> response = new HashMap<>();

        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                .stream()
                .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage()) // Errores correctamente manejados
                .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Manejo de errores de validación
        }

        try {
            Matricula matriculaNueva = matriculaService.save(matricula); // Corrige `Matrícula` a `matricula`
            response.put("mensaje", "La matrícula se ha creado con éxito.");
            response.put("matricula", matriculaNueva);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // Respuesta exitosa
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar la matrícula en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Manejo de excepción
        }
    }

   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/niveles")
    public ResponseEntity<List<Nivel>> getNiveles() {
        return new ResponseEntity<>(matriculaService.getNiveles(), HttpStatus.OK); // Uso correcto del servicio
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/turnos")
    public ResponseEntity<List<Turno>> getTurnos() {
        return new ResponseEntity<>(matriculaService.getTurnos(), HttpStatus.OK); // Uso correcto del servicio
    }

   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dias")
    public ResponseEntity<List<DiaSemana>> getDias() {
        return new ResponseEntity<>(matriculaService.getDias(), HttpStatus.OK); // Uso correcto del servicio
    }

   // @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    @GetMapping("/notas")
    public ResponseEntity<List<Nota>> getNotas(@RequestParam("idCurso") String idCurso, @RequestParam("idAula") String idAula) {
        return new ResponseEntity<>(matriculaService.findNotasPorAulaYCurso(idCurso, idAula), HttpStatus.OK); // Corrige nombres de parámetros
    }

   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getReporteCursoXLS")
    public ResponseEntity<?> getReporteCursoXLS(@RequestParam("idCurso") String idCurso, @RequestParam("idGrado") String idGrado) {
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header("Content-Disposition", "attachment; filename=\"cursos.xlsx\"")
            .body(claseService.generarReporteCurso("xls", idCurso, idGrado)); // Corrige errores
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/matriculasPorEstudiante")
    public ResponseEntity<List<Matricula>> getMatriculasPorEstudiante(@RequestParam("id") String id) {
        return new ResponseEntity<>(matriculaService.getMatriculasPorEstudiante(id), HttpStatus.OK); // Corrige el tipo de datos
    }
}

