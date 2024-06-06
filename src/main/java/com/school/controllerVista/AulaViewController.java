
package com.school.controllerVista;

import org.slf4j.Logger;
import com.school.model.Aula;
import com.school.model.Nivel;
import com.school.model.Turno;
import com.school.service.AulaService;
import com.school.service.NivelService;
import com.school.service.TurnoService;

import jakarta.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/aulas")
public class AulaViewController {

    private static final Logger logger = LoggerFactory.getLogger(AulaViewController.class);

    @Autowired
    private AulaService aulaService;

    @Autowired
    private NivelService nivelService;

    @Autowired
    private TurnoService turnoService;

    
    @PostMapping
    public String crearAula(@ModelAttribute Aula aula) {
        aulaService.save(aula);
        return "redirect:/aulas"; // Redirige a la lista de aulas después de guardar
    }
    
    @GetMapping("/formularioAula")
    public String mostrarFormularioAula(Model model) {
        model.addAttribute("aula", new Aula());
        return "formularioAula";
    }

    @PostMapping("/guardarAula")
    public String guardarAula(@ModelAttribute Aula aula) {
        aulaService.save(aula);
        return "redirect:/aulas"; // Redirige a la lista de aulas después de guardar
    }


    @GetMapping
    public String mostrarListaDeAulas(Model model) {
        List<Aula> aulas = aulaService.findAll();
        logger.info("Aulas recibidas en el controlador de vista: " + aulas.size());
        model.addAttribute("aulas", aulas);
        return "aulas";
    }

    @GetMapping("/ver/{id}")
    public String verDetalleDeAula(@PathVariable String id, Model model) {
        Optional<Aula> aulaOpt = aulaService.findById(id);
        if (aulaOpt.isPresent()) {
            Aula aula = aulaOpt.get();
            model.addAttribute("aula", aula);
            return "verAula"; // Redirige al archivo HTML que muestra la información del aula
        } else {
            // Si el aula no existe, redirige a la lista de aulas o maneja el error apropiadamente
            return "redirect:/aulas"; // Otra opción: return "error"; para mostrar una página de error
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> saveAula(@Valid @RequestBody Aula aula, BindingResult results) {
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
            // Obtener el nombre del turno basado en el objeto Aula
            String nombreTurno = aula.getTurno();

            // Obtener el ID del nivel basado en el nombre proporcionado
            Optional<Nivel> nivelOpt = nivelService.findByNombre(aula.getNivel().getNombre());

            // Manejar el caso en que el nivel no existe en la base de datos
            if (!nivelOpt.isPresent()) {
                response.put("mensaje", "El nivel no existe en la base de datos");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Obtener el ID del turno basado en el nombre proporcionado
            Optional<Turno> turnoOpt = turnoService.findByNombre(nombreTurno);

            // Manejar el caso en que el turno no existe en la base de datos
            if (!turnoOpt.isPresent()) {
                response.put("mensaje", "El turno no existe en la base de datos");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Establecer los IDs correspondientes en el objeto Aula
            aula.getNivel().setId(nivelOpt.get().getId());
            aula.setTurno(turnoOpt.get().getId());

            Aula aulaNuevo = aulaService.save(aula);
            response.put("aula", aulaNuevo);
            response.put("mensaje", "El aula ha sido creada con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el aula en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

    
