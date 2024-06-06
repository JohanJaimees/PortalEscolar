package com.school.controller;

import com.school.model.Aula;
import com.school.model.Clase;
import com.school.model.Estudiante;
import com.school.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//@CrossOrigin(origins = {"*"})
@RequestMapping("/api/aulas")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @PostMapping("/guardar")
    public String saveAula(@Valid @ModelAttribute Aula aula, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "aula-form";
        }

        aulaService.save(aula);
        return "redirect:/aulas";
    }
    
    @GetMapping
    public ResponseEntity<List<Aula>> getAllAulas() {
        return new ResponseEntity<>(aulaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/estudiantes")
    public ResponseEntity<List<Estudiante>> getAllEstudiantesAula(@RequestParam("id") String id) {
        return new ResponseEntity<>(aulaService.findEstudiantesAula(id), HttpStatus.OK);
    }

    @GetMapping("/clases")
    public ResponseEntity<List<Clase>> getAllClasesAula(@RequestParam("id") String id) {
        return new ResponseEntity<>(aulaService.findClasesAula(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAula(@PathVariable String id) {
        Optional<Aula> aulaOpt = aulaService.findById(id);
        if (aulaOpt.isPresent()) {
            Aula aula = aulaOpt.get();
            return new ResponseEntity<>(aula, HttpStatus.OK);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "El aula con el ID: ".concat(id).concat(" no existe en la base de datos"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> saveAula(@Valid @RequestBody Aula aula, BindingResult results) {
        Aula aulaNuevo = null;
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
            aulaNuevo = aulaService.save(aula);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el aula en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("aula", aulaNuevo);
        response.put("mensaje", "El aula ha sido creada con éxito");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAula(@Valid @RequestBody Aula aula, BindingResult results, @PathVariable String id) {
        Aula aulaActual = aulaService.findById(id).orElse(null);
        Aula aulaActualizado = null;
        Map<String, Object> response = new HashMap<>();

        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (aulaActual == null) {
            response.put("mensaje", "Error: No se pudo editar el aula con el ID: ".concat(id).concat(" no existe en la base de datos"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            aulaActual.setNombre(aula.getNombre());
            aulaActual.setSeccion(aula.getSeccion());
            aulaActual.setTurno(aula.getTurno());
            aulaActual.setNivel(aula.getNivel());
            aulaActual.setGradoAula(aula.getGradoAula());
            aulaActual.setCapacidad(aula.getCapacidad());
            
            aulaActualizado = aulaService.save(aulaActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el aula en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("aula", aulaActualizado);
        response.put("mensaje", "El aula ha sido actualizada con éxito");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAula(@PathVariable String id) {
        Aula aula = aulaService.findById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();

        if (aula == null) {
            response.put("mensaje", "Error: No se pudo eliminar el aula con el ID: ".concat(id).concat(" no existe en la base de datos"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            aulaService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el aula en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El aula ha sido eliminada con éxito");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
