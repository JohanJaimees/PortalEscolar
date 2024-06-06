package com.school.controllerVista;


import com.school.model.Curso;
import com.school.service.CursoService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cursos")
public class CursoViewController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.findAll());
        return "cursos";
    }

    @GetMapping("/nuevo")
    public String crearCursoForm(Model model) {
        model.addAttribute("curso", new Curso());
        return "formulario-curso";
    }

    @GetMapping("/editar/{id}")
    public String editarCursoForm(@PathVariable String id, Model model) {
        Curso curso = cursoService.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de curso inválido:" + id));
        model.addAttribute("curso", curso);
        return "formulario-curso";
    }

    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute Curso curso) {
        try {
            if (curso.getId() == null || curso.getId().isEmpty()) {
                // Si el ID es nulo o vacío, estamos creando un nuevo curso
                curso.setId(UUID.randomUUID().toString()); // Generamos un ID único
            }
            cursoService.save(curso); // Guardamos el nuevo curso o actualizamos uno existente
            return "redirect:/cursos";
        } catch (Exception e) {
            e.printStackTrace();
            // Manejamos cualquier excepción que pueda ocurrir y mostramos un mensaje de error
            return "error"; // Puedes crear una página de error personalizada
        }
    }



    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable String id) {
        cursoService.delete(id); // Cambia de deleteById a delete
        return "redirect:/cursos";
    }
}
