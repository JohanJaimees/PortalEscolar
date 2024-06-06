package com.school.service;

import com.school.dao.EspecialidadDao;
import com.school.model.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    private EspecialidadDao especialidadDao; // Repositorio para MongoDB

    @Override
    public Especialidad save(Especialidad especialidad) {
        return especialidadDao.save(especialidad); // Guarda la especialidad
    }

    @Override
    public Optional<Especialidad> findById(String id) {
        return especialidadDao.findById(id); // Encuentra por ID como cadena
    }

    @Override
    public List<Especialidad> findAll() { 
        return especialidadDao.findAll(); // Devuelve todas las especialidades
    }

    @Override
    public Boolean deleteById(String id) {
        return findById(id).map(especialidad -> {
            especialidadDao.deleteById(id); // Elimina por ID
            return true; // Devuelve `true` si se eliminó
        }).orElse(false); // Devuelve `false` si no se encontró
    }
    
    @Override
    public Especialidad findByNombre(String nombre) {
        return especialidadDao.findByNombre(nombre); // Busca por nombre
    }
}

