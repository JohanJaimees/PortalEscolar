package com.school.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.school.security.enums.RolNombre;
import com.school.security.models.Rol;
import com.school.security.service.IRolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private IRolService rolService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearRol(@RequestBody RolNombre rolNombre, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            response.put("mensaje", "Error en la solicitud");
            response.put("errores", result.getAllErrors());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<Rol> rolExistente = rolService.findByRolNombre(rolNombre);
        if (rolExistente.isPresent()) {
            response.put("mensaje", "El rol ya existe en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Rol nuevoRol = new Rol(rolNombre);
        try {
            rolService.save(nuevoRol);
            response.put("mensaje", "Rol creado exitosamente");
            response.put("rol", nuevoRol);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("mensaje", "Error al crear el rol");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}