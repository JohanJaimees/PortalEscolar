package com.school.controllerVista;
import org.springframework.http.MediaType;
import com.school.model.Estudiante;
import com.school.model.Matricula;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EstudianteViewController {

	 @GetMapping("/Estudiantes")
	    public String showEstudiantesView() {
	        return "Estudiantes";
	    }

	    @GetMapping("/formularioEstudiante")
	    public String mostrarFormulario(Model model) {
	        Matricula matricula = new Matricula(); // Crear una instancia de Matricula
	        model.addAttribute("matricula", matricula); // Agregarla al modelo
	        return "formularioEstudiante"; // Devolver el nombre de la plantilla Thymeleaf
	    }


    @PostMapping(value = "/api/matriculas/crear", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String crearMatricula(@RequestBody Estudiante estudiante) {
        // Lógica para manejar la creación de la matrícula aquí
        return "redirect:/Estudiantes"; // Redirige a la página de estudiantes después de crear la matrícula
    }

}

