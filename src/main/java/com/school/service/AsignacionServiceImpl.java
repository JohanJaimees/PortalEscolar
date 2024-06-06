package com.school.service;

import com.school.dao.AsignacionDao;
import com.school.model.Asignacion;
import com.school.model.Clase; // Asegúrate de tener esta importación
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime; // Para trabajar con fechas
import java.util.Optional;

// Implementación del servicio de asignación
@Service
public class AsignacionServiceImpl implements AsignacionService {

    @Autowired
    private AsignacionDao asignacionDao; // Repositorio para MongoDB

    @Autowired
    private ClaseService claseService; // Inyección para obtener información de clases

    @Override
    public Asignacion save(Asignacion asignacion, String idClase) {
        // Encuentra la clase por su ID
        Clase claseEncontrada = claseService.findById(idClase)
            .orElseThrow(() -> new IllegalArgumentException("Clase no encontrada con ID: " + idClase));
        
        // Asocia la clase a la asignación
        asignacion.setClase(claseEncontrada);

        // Guarda y devuelve la asignación
        return asignacionDao.save(asignacion);
    }

    @Override
    public Asignacion update(Asignacion asignacion) {
        // Actualiza la asignación directamente en el repositorio
        return asignacionDao.save(asignacion);
    }

    @Override
    public Optional<Asignacion> findById(String id) { // Ajustar el tipo de ID
        // Encuentra la asignación por su ID
        Optional<Asignacion> asignacionEncontrada = asignacionDao.findById(id);

        asignacionEncontrada.ifPresent(asignacion -> {
            LocalDateTime fechaAhora = LocalDateTime.now();

            // Verifica si la asignación está activa en función de las fechas
            boolean estaActivo = fechaAhora.isBefore(asignacion.getFechaFin()) && 
                                 fechaAhora.isAfter(asignacion.getFechaInicio());
            
            asignacion.setActivo(estaActivo);

            // Guarda cambios si es necesario
            asignacionDao.save(asignacion);
        });

        return asignacionEncontrada;
    }

    @Override
    public boolean deleteById(String id) { // Ajustar el tipo de ID
        return findById(id).map(asignacion -> {
            asignacionDao.deleteById(id);
            return true;
        }).orElse(false); // Devuelve false si la asignación no se encuentra
    }
}
