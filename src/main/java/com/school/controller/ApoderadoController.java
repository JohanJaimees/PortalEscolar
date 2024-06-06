package com.school.controller;

import com.school.model.Apoderado;
import com.school.service.ApoderadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/apoderados")
public class ApoderadoController {

    @Autowired
    private ApoderadoService apoderadoService; // Verificar que usa repositorio MongoDB

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/findByDni")
    public ResponseEntity<?> getApoderado(@RequestParam("dni") String dni) {

        Apoderado apoderado = null;
        Map<String, Object> response = new HashMap<>();

        try {
            apoderado = apoderadoService.findByDni(dni); // Verificar que el método es compatible con MongoDB
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al consultar al apoderado en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (apoderado == null) {
            response.put("mensaje", "El apoderado con el DNI: ".concat(dni).concat(" no existe en la base de datos"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Manejar el caso en el que el apoderado no exista
        }

        response.put("apoderado", apoderado); // Respuesta con el apoderado encontrado

        return new ResponseEntity<>(response, HttpStatus.OK); // Respuesta exitosa
    }
}
