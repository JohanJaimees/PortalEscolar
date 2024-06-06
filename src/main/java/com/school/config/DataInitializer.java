package com.school.config;

import com.school.security.enums.RolNombre;
import com.school.security.models.Rol;
import com.school.security.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private IRolService rolService;

    @Override
    public void run(String... args) throws Exception {
        List<RolNombre> roles = Arrays.asList(RolNombre.ROLE_ADMIN, RolNombre.ROLE_ESTUDIANTE, RolNombre.ROLE_PROFESOR);
        
        roles.forEach(rolNombre -> {
            if (!rolService.findByRolNombre(rolNombre).isPresent()) {
                Rol rol = new Rol(rolNombre);
                rolService.save(rol);
            }
        });
    }
}
