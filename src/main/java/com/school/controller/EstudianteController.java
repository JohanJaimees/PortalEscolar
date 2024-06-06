package com.school.controller;

import com.school.model.Estudiante;
import com.school.service.EstudianteService; // Importar el servicio correcto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService; // Inyectar el servicio correcto

  //  @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    @GetMapping
    public ResponseEntity<List<Estudiante>> getAllEstudiantes() {
        return new ResponseEntity<>(estudianteService.findAll(), HttpStatus.OK); // Devuelve todos los estudiantes
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getEstudiante(@PathVariable("id") String id) { // Uso correcto de @PathVariable
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Estudiante> estudiante = estudianteService.getEstudianteById(id); // Encuentra por ID
            if (estudiante.isEmpty()) {
                response.put("mensaje", "El estudiante con el ID: " + id + " no existe.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Manejo de no encontrado
            }
            return new ResponseEntity<>(estudiante.get(), HttpStatus.OK); // Respuesta exitosa
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al consultar el estudiante en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Manejo de excepción
        }
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> saveEstudiante(@Valid @RequestBody Estudiante estudiante, BindingResult results) {
        Map<String, Object> response = new HashMap<>();

        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Manejo de errores de validación
        }

        try {
            Estudiante estudianteNuevo = estudianteService.save(estudiante); // Uso correcto del servicio
            response.put("mensaje", "El estudiante ha sido creado con éxito!");
            response.put("estudiante", estudianteNuevo);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // Respuesta exitosa
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el estudiante en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEstudiante(@Valid @RequestBody Estudiante estudiante, BindingResult results, @PathVariable("id") String id) { // Uso correcto de @PathVariable
        Map<String, Object> response = new HashMap<>();

        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Manejo de errores de validación
        }

        try {
            Estudiante estudianteActualizado = estudianteService.update(id, estudiante); // Uso correcto del servicio
            response.put("mensaje", "El estudiante ha sido actualizado con éxito!");
            response.put("estudiante", estudianteActualizado);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // Respuesta exitosa
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el estudiante en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstudiante(@PathVariable("id") String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Estudiante> estudiante = estudianteService.getEstudianteById(id); // Uso correcto del servicio
            if (estudiante.isEmpty()) {
                response.put("mensaje", "El estudiante con el ID: " + id + " no existe.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Manejo de no encontrado
            }

            estudianteService.delete(id); // Eliminación por ID
            response.put("mensaje", "El estudiante ha sido eliminado con éxito.");
            return new ResponseEntity<>(response, HttpStatus.OK); // Respuesta exitosa
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el estudiante.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Manejo de excepción
        }
    }
}

