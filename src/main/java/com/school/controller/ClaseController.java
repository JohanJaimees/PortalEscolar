package com.school.controller;

import com.school.model.Aula;
import com.school.model.Clase;
import com.school.model.Frecuencia;
import com.school.service.AulaService;
import com.school.service.ClaseService;
import com.school.service.FrecuenciaService;
import com.school.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/clases")
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    @Autowired
    private FrecuenciaService frecuenciaService;
    
    @Autowired
    private AulaService aulaService;
    
    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }
    

    @Autowired
    public ClaseController(AulaService aulaService, ClaseService claseService) {
        this.aulaService = aulaService;
        this.claseService = claseService;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Clase>> getAllClases() {
        return new ResponseEntity<>(claseService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/clases/crear")
    public String crearClase(Model model, @RequestParam("aulaId") String aulaId) {
        model.addAttribute("clase", new Clase());
        model.addAttribute("aulaId", aulaId);
        // Agregar otros atributos necesarios al modelo, como cursos y empleados
        return "crearClase";
    }

    @GetMapping("/clases/ver")
    public String verClasesAula(@RequestParam("aulaId") String aulaId, Model model) {
        Optional<Aula> optionalAula = aulaService.findById(aulaId);
        if (optionalAula.isPresent()) {
            Aula aula = optionalAula.get();
            List<Clase> clases = claseService.findByAula(aula);
            model.addAttribute("aula", aula);
            model.addAttribute("clases", clases);
            return "verClases";
        } else {
            // Manejo de error si el aula no se encuentra
            return "error";
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarClase(@ModelAttribute Clase clase, BindingResult results, RedirectAttributes redirectAttributes) {
        if (results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());
            redirectAttributes.addFlashAttribute("errors", errors);
            return ResponseEntity.badRequest().body("Error en los datos de la clase");
        }

        try {
            Clase claseNueva = claseService.save(clase);
            redirectAttributes.addFlashAttribute("mensaje", "La clase ha sido creada con éxito");
            return ResponseEntity.status(HttpStatus.CREATED).body("Clase guardada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al insertar la clase: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al insertar la clase");
        }
    }






    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClase(@ModelAttribute Clase clase, BindingResult results, @PathVariable String id) {
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
            Optional<Clase> claseEncontrada = claseService.findById(id);
            
            if (claseEncontrada.isEmpty()) {
                response.put("mensaje", "La clase con el ID: " + id + " no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            Clase claseActualizada = claseService.update(id, clase);

            // Actualiza las frecuencias relacionadas
            List<Frecuencia> frecuencias = clase.getFrecuencias();
            for (Frecuencia frecuencia : frecuencias) {
                frecuencia.setClase(claseActualizada);
                frecuenciaService.save(frecuencia);
            }
            claseActualizada.setFrecuencias(frecuencias);

            response.put("clase", claseActualizada);
            response.put("mensaje", "La clase ha sido actualizada con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar la clase");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClase(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Clase> claseEncontrada = claseService.findById(id);
            
            if (claseEncontrada.isEmpty()) {
                response.put("mensaje", "No se pudo eliminar la clase con el ID: " + id + " porque no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            claseService.delete(id);
            response.put("mensaje", "La clase ha sido eliminada con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "Error al eliminar la clase");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

