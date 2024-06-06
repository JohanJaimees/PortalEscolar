package com.school.controller;

import com.school.model.Grado;
import com.school.service.GradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/grados")
public class GradoController {

    @Autowired
    private GradoService gradoService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Grado>> getAllGrados() {
        return new ResponseEntity<>(gradoService.findAll(), HttpStatus.OK);
    }
    



    @GetMapping("/{id}")
    public ResponseEntity<?> getGrado(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Grado> grado = gradoService.findById(id);

            if (grado.isEmpty()) {
                response.put("mensaje", "El grado con el ID: ".concat(id).concat(" no existe en la base de datos"));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(grado.get(), HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al consultar el grado en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> saveGrado(@Valid @RequestBody Grado grado, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Grado gradoNuevo = gradoService.save(grado);
            response.put("grado", gradoNuevo);
            response.put("mensaje", "El grado ha sido creado con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el grado en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGrado(@Valid @RequestBody Grado grado, BindingResult result, @PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<Grado> gradoActual = gradoService.findById(id);

            if (gradoActual.isEmpty()) {
                response.put("mensaje", "Error: No se pudo editar, el grado con el ID: ".concat(id).concat(" no existe en la base de datos"));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            Grado gradoActualizado = gradoActual.get();
            gradoActualizado.setNombre(grado.getNombre());
            gradoService.save(gradoActualizado);

            response.put("grado", gradoActualizado);
            response.put("mensaje", "El grado ha sido actualizado con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el grado en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGrado(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Grado> grado = gradoService.findById(id);

            if (grado.isEmpty()) {
                response.put("mensaje", "Error: No se pudo eliminar el grado con el ID: ".concat(id));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            gradoService.deleteById(id);

            response.put("mensaje", "El grado ha sido eliminado con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el grado en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
