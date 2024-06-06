package com.school.controllerVista;



import com.school.model.Empleado;
import com.school.model.Especialidad;
import com.school.service.EmpleadoService;
import com.school.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmpleadoViewController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public String listEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.findAll();
        model.addAttribute("empleados", empleados);
        return "empleados";
    }

    @GetMapping("/nuevo")
    public String createEmpleadoForm(Model model) {
        Empleado empleado = new Empleado();
        List<Especialidad> especialidades = especialidadService.findAll();
        model.addAttribute("empleado", empleado);
        model.addAttribute("especialidades", especialidades);
        return "formularioempleado";
    }

    @PostMapping
    public String saveEmpleado(@Valid @ModelAttribute("empleado") Empleado empleado, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Especialidad> especialidades = especialidadService.findAll();
            model.addAttribute("especialidades", especialidades);
            return "formularioempleado";
        }
        empleadoService.save(empleado);
        return "redirect:/empleados";
    }
    
    

    @GetMapping("/editar/{id}")
    public String editEmpleadoForm(@PathVariable String id, Model model) {
        Optional<Empleado> empleadoOptional = empleadoService.getEmpleadoById(id);
        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();
            List<Especialidad> especialidades = especialidadService.findAll();
            model.addAttribute("empleado", empleado);
            model.addAttribute("especialidades", especialidades);
            return "formularioempleado";
        }
        return "redirect:/empleados";
    }


    @PostMapping("/editar/{id}")
    public String updateEmpleado(@PathVariable String id, @Valid @ModelAttribute("empleado") Empleado empleado, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Especialidad> especialidades = especialidadService.findAll();
            model.addAttribute("especialidades", especialidades);
            return "formularioempleado";
        }
        
        Optional<Empleado> empleadoExistenteOptional = empleadoService.getEmpleadoById(id);
        if (empleadoExistenteOptional.isPresent()) {
            Empleado empleadoExistente = empleadoExistenteOptional.get();
            
            empleadoExistente.setNombres(empleado.getNombres());
            empleadoExistente.setApellidoPaterno(empleado.getApellidoPaterno());
            empleadoExistente.setApellidoMaterno(empleado.getApellidoMaterno());
            empleadoExistente.setDni(empleado.getDni());
            empleadoExistente.setFechaNacimiento(empleado.getFechaNacimiento());
            empleadoExistente.setCelular(empleado.getCelular());
            empleadoExistente.setDomicilio(empleado.getDomicilio());
            empleadoExistente.setSexo(empleado.getSexo());
            empleadoExistente.setEspecialidades(empleado.getEspecialidades());

            empleadoService.save(empleadoExistente);
        }
        
        return "redirect:/empleados";
    }


    @GetMapping("/eliminar/{id}")
    public String deleteEmpleado(@PathVariable String id) {
        empleadoService.delete(id);
        return "redirect:/empleados";
    }
}
