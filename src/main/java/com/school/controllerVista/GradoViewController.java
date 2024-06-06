package com.school.controllerVista;
import com.school.model.Grado;
import com.school.service.GradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/grados")
public class GradoViewController {

    @Autowired
    private GradoService gradoService;

    @GetMapping
    public String listarGrados(Model model) {
        model.addAttribute("grados", gradoService.findAll());
        return "grados";
    }

    @GetMapping("/nuevo")
    public String crearGradoForm(Model model) {
        model.addAttribute("grado", new Grado());
        return "formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarGradoForm(@PathVariable String id, Model model) {
        Grado grado = gradoService.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de grado inválido:" + id));
        model.addAttribute("grado", grado);
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardarGrado(@ModelAttribute Grado grado) {
        if (grado.getId() == null) {
            // Si el ID es nulo, estamos creando un nuevo grado
            gradoService.save(grado); // Guardamos el nuevo grado
        } else {
            // Si el ID no es nulo, estamos editando un grado existente
            Grado existingGrado = gradoService.findById(grado.getId())
                .orElseThrow(() -> new IllegalArgumentException("ID de grado inválido:" + grado.getId()));
            existingGrado.setNombre(grado.getNombre()); // Actualizamos los datos del grado existente
            gradoService.save(existingGrado); // Guardamos los cambios
        }
        return "redirect:/grados";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarGrado(@PathVariable String id) {
        Grado grado = gradoService.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de grado inválido:" + id));
        gradoService.deleteById(id);
        return "redirect:/grados";
    }
}
