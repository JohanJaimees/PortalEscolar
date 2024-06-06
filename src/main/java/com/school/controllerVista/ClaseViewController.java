package com.school.controllerVista;

import com.school.model.Aula;
import com.school.model.Clase;
import com.school.model.Curso;
import com.school.model.Empleado;
import com.school.service.ClaseService;
import com.school.service.CursoService;
import com.school.service.EmpleadoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clases")
public class ClaseViewController {

    @Autowired
    private ClaseService claseService;
    
    @Autowired
    private EmpleadoService empleadoService; // Suponiendo que tienes un servicio para empleados

    @Autowired
    private CursoService cursoService; // Suponiendo que tienes un servicio para cursos


    @GetMapping("/crear")
    public String mostrarFormularioCrearClase(@RequestParam("aulaId") String aulaId, Model model) {
        List<Curso> cursos = cursoService.findAll();
        List<Empleado> empleados = empleadoService.findAll();

        Clase clase = new Clase();
        Aula aula = new Aula();
        aula.setId(aulaId);
        clase.setAula(aula);

        model.addAttribute("clase", clase);
        model.addAttribute("cursos", cursos);
        model.addAttribute("empleados", empleados);
        model.addAttribute("aulaId", aulaId);  // Asegúrate de agregar el aulaId al modelo
        return "crearclase";
    }


    
    @PostMapping("/redirigir")
    public String redirigirACrearClase(@RequestParam("aulaId") String idDelAula) {
        return "redirect:/clases/crear?aulaId=" + idDelAula;
    }

    @GetMapping
    public String mostrarListaDeClases(Model model) {
        List<Clase> clases = claseService.findAll();
        model.addAttribute("clases", clases);
        return "listadeclases";
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarClase(@Valid @RequestBody Clase clase, BindingResult results) {
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
            Clase claseNueva = claseService.save(clase); // Guarda la nueva clase
            response.put("clase", claseNueva);
            response.put("mensaje", "La clase ha sido creada con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED); // Devuelve respuesta de creación
        } catch (Exception e) {
            response.put("mensaje", "Error al insertar la clase");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Manejo de errores
        }
    }
}
