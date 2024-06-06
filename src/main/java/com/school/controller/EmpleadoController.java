package com.school.controller;

import com.school.model.Clase;
import com.school.model.Empleado;
import com.school.model.Especialidad;
import com.school.service.EmpleadoService;
import com.school.service.EspecialidadService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        List<Empleado> empleados = empleadoService.findAll();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/clases")
    public ResponseEntity<List<Clase>> getClasesEmpleado(@RequestParam("id") String id) {
        List<Clase> clases = empleadoService.findClasesProfesor(id);
        return ResponseEntity.ok(clases);
    }
    
    

    @PostMapping("/crear")
    public ResponseEntity<?> saveEmpleado(@Valid @RequestBody Empleado empleado, BindingResult results) {
        Map<String, Object> response = new HashMap<>();

        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        try {
            // Guarda las nuevas especialidades primero para obtener sus IDs
            Set<Especialidad> especialidades = empleado.getEspecialidades();
            Set<Especialidad> especialidadesGuardadas = new HashSet<>(); // Usamos un Set para evitar duplicados
            for (Especialidad especialidad : especialidades) {
                if (especialidad.getId() == null) {
                    Especialidad especialidadExistente = especialidadService.findByNombre(especialidad.getNombre());
                    if (especialidadExistente != null) {
                        // Si la especialidad ya existe, usamos su ID en lugar de crear una nueva
                        especialidadesGuardadas.add(especialidadExistente);
                    } else {
                        // Si no existe, la guardamos y luego obtenemos su ID
                        especialidadesGuardadas.add(especialidadService.save(especialidad));
                    }
                } else {
                    especialidadesGuardadas.add(especialidad);
                }
            }
            empleado.setEspecialidades(especialidadesGuardadas);

            // Procede a guardar el empleado
            String[] nombres = empleado.getNombres().split(" ");
            empleado.setCorreo(nombres[0] + "." + empleado.getApellidoPaterno() + "@colvia.edu.pe");
            Empleado empleadoNuevo = empleadoService.save(empleado);

            response.put("mensaje", "El empleado ha sido creado con éxito!");
            response.put("empleado", empleadoNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el empleado en la base de datos");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpleado(@PathVariable String id) {
        Optional<Empleado> empleado = empleadoService.getEmpleadoById(id);
        return empleado.map(value -> ResponseEntity.ok(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscarDni")
    public ResponseEntity<?> getEmpleadoByDni(@RequestParam String dni) {
        Empleado empleado = empleadoService.findByDni(dni);
        return empleado != null ? ResponseEntity.ok(empleado) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpleado(@Valid @RequestBody Empleado empleado, BindingResult results, @PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Empleado> empleadoActualOptional = empleadoService.getEmpleadoById(id);
        if (empleadoActualOptional.isEmpty()) {
            response.put("mensaje", "Error: No se pudo editar, el empleado con el ID: " + id + " no existe en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Empleado empleadoActual = empleadoActualOptional.get();
        empleadoActual.setNombres(empleado.getNombres());
        empleadoActual.setApellidoPaterno(empleado.getApellidoPaterno());
        empleadoActual.setApellidoMaterno(empleado.getApellidoMaterno());
        empleadoActual.setDni(empleado.getDni());
        empleadoActual.setFechaNacimiento(empleado.getFechaNacimiento());
        empleadoActual.setCorreo(empleado.getCorreo());
        empleadoActual.setSexo(empleado.getSexo());
        empleadoActual.setDomicilio(empleado.getDomicilio());
        empleadoActual.setEspecialidades(empleado.getEspecialidades());

        try {
            Empleado empleadoActualizado = empleadoService.save(empleadoActual);
            response.put("mensaje", "El empleado ha sido actualizado con éxito!");
            response.put("empleado", empleadoActualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el Empleado en la base de datos");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        Optional<Empleado> empleadoOptional = empleadoService.getEmpleadoById(id);
        if (empleadoOptional.isEmpty()) {
            response.put("mensaje", "Error: No se pudo eliminar, el empleado con el ID: " + id + " no existe en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Empleado empleado = empleadoOptional.get();
        try {
            empleadoService.delete(id);
            response.put("mensaje", "El empleado ha sido eliminado con éxito");
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el empleado en la base de datos");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/especialidades")
    public ResponseEntity<List<Especialidad>> listarEspecialidades() {
        List<Especialidad> especialidades = especialidadService.findAll();
        return ResponseEntity.ok(especialidades);
    }

    @PostMapping("/especialidades/crear")
    public ResponseEntity<?> crearEspecialidad(@RequestBody Especialidad especialidad, BindingResult results) {
        Map<String, Object> response = new HashMap<>();

        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        try {
            Especialidad especialidadNueva = especialidadService.save(especialidad);
            response.put("mensaje", "La especialidad se creó con éxito!");
            response.put("especialidad", especialidadNueva);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar la especialidad en la base de datos");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
