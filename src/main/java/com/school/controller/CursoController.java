package com.school.controller;

import com.school.model.Clase;
import com.school.model.Curso;
import com.school.model.Empleado;
import com.school.service.CursoService; // Importa CursoService
import com.school.service.EmpleadoService;

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
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService; // Verifica que está importado y definido correctamente

    @Autowired
    private EmpleadoService empleadoService;
    
    
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        return new ResponseEntity<>(cursoService.findAll(), HttpStatus.OK); // Adaptación para MongoDB
    }

    
    @GetMapping("/crearclase")
    public String mostrarFormulario(Model model) {
        List<Curso> cursos = cursoService.findAll();
        List<Empleado> empleados = empleadoService.findAll();

        model.addAttribute("cursos", cursos);
        model.addAttribute("empleados", empleados);
        model.addAttribute("clase", new Clase());

        return "crearclase";
    }

    @PostMapping("/crearclase")
    public String crearClase(@ModelAttribute Clase clase) {
        // Lógica para guardar la clase
        // claseService.guardarClase(clase); // Asegúrate de tener un servicio para guardar la clase

        return "redirect:/clases";
    }

    
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCurso(@PathVariable String id) { // Corregido @PathVariable
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Curso> curso = cursoService.findById(id); // Método correcto en CursoService
            if (curso.isEmpty()) {
                response.put("mensaje", "El curso con el ID: ".concat(id).concat(" no existe en la base de datos"));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(curso.get(), HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al consultar el curso en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Uso correcto
        }
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> saveCurso(@Valid @RequestBody Curso curso, BindingResult results) {
        Map<String, Object> response = new HashMap<>();

        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage()) // Corregido
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Curso cursoNuevo = cursoService.save(curso); // Adaptación a MongoDB
            response.put("mensaje", "El curso ha sido creado con éxito!");
            response.put("curso", cursoNuevo);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // Respuesta exitosa
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el curso en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Uso correcto
        }
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurso(@Valid @RequestBody Curso curso, BindingResult results, @PathVariable String id) { // Corregido @PathVariable
        Map<String, Object> response = new HashMap<>();

        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage()) // Elimina espacio adicional
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Manejo de errores de validación
        }

        try {
            Curso cursoActualizado = cursoService.update(id, curso); // Método correcto en CursoService
            response.put("mensaje", "El curso ha sido actualizado con éxito!");
            response.put("curso", cursoActualizado);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // Respuesta de éxito
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el curso en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Manejo de excepción
        }
    }


    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable String id) { // Corregido @PathVariable
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Curso> curso = cursoService.findById(id); // Método correcto en CursoService
            if (curso.isEmpty()) {
                response.put("mensaje", "Error: No se pudo eliminar el curso con el ID: ".concat(id));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            cursoService.delete(id); // Elimina por ID
            response.put("mensaje", "El curso ha sido eliminado con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el curso en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
