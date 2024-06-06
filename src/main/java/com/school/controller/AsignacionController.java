package com.school.controller;

import com.school.model.*;
import com.school.service.AsignacionService;
import com.school.service.EstudianteService;
import com.school.service.RespuestaAsignacionService;
import com.school.service.UploadFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/api/asignaciones")
public class AsignacionController {

    @Autowired
    private AsignacionService asignacionService; // Servicio que trabaja con MongoDB

    @Autowired
    private RespuestaAsignacionService respuestaAsignacionService; // Servicio MongoDB

    @Autowired
    private UploadFileService uploadFileService; // Servicio de subida de archivos

    @Autowired
    private EstudianteService estudianteService; // Servicio MongoDB

    @PostMapping("/crear/{idClase}")
    public ResponseEntity<?> crearAsignacion(@Valid @RequestBody Asignacion asignacion, BindingResult results, @PathVariable String idClase) {
        // Verificar que idClase sea cadena, porque MongoDB no usa ID numérico generado
        Asignacion asignacionNueva = null;
        Map<String, Object> response = new HashMap<>();

        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            asignacionNueva = asignacionService.save(asignacion, idClase); // Adaptar lógica a MongoDB
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar la asignación en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La asignación ha sido creada con éxito!");
        response.put("asignacion", asignacionNueva);

        return new ResponseEntity<>(response, HttpStatus.CREATED); // Respuesta de creación exitosa
    }

    @PutMapping("/actualizar/{idAsignacion}")
    public ResponseEntity<?> actualizarAsignacion(@Valid @RequestBody Asignacion asignacion, BindingResult results, @PathVariable String idAsignacion) {
        // Usar String para ID, compatible con MongoDB
        Asignacion asignacionEncontrada = asignacionService.findById(idAsignacion).orElse(null); // Acceder a MongoDB
        Asignacion asignacionActualizada = null;
        Map<String, Object> response = new HashMap<>();

        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (asignacionEncontrada == null) {
            response.put("mensaje", "Error: No se pudo editar la asignación con el ID: ".concat(idAsignacion).concat(" no existe en la base de datos."));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Manejo del caso no encontrado
        }

        try {
            asignacionEncontrada.setTitulo(asignacion.getTitulo());
            asignacionEncontrada.setDescripcion(asignacion.getDescripcion());
            asignacionEncontrada.setFechaFin(asignacion.getFechaFin());
            asignacionEncontrada.setFechaInicio(asignacion.getFechaInicio());
            asignacionActualizada = asignacionService.update(asignacionEncontrada); // Adaptación a MongoDB
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Manejo de error
        }

        response.put("mensaje", "La asignación ha sido actualizada con éxito!");
        response.put("asignacion", asignacionActualizada);

        return new ResponseEntity<>(response, HttpStatus.CREATED); // Respuesta de éxito
    }
}
