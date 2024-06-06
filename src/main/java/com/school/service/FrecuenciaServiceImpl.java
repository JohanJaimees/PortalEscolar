package com.school.service;

import com.school.dao.FrecuenciaDao;
import com.school.model.Frecuencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FrecuenciaServiceImpl implements FrecuenciaService {

    @Autowired
    private FrecuenciaDao frecuenciaDao; // Repositorio para MongoDB

    @Override
    public List<Frecuencia> findAll() {
        return frecuenciaDao.findAll();
    }

    @Override
    public Optional<Frecuencia> findById(String id) {
        return frecuenciaDao.findById(id);
    }

    @Override
    public Frecuencia save(Frecuencia frecuencia) { // Implementar el método save
        return frecuenciaDao.save(frecuencia);
    }

    @Override
    public void deleteById(String id) { // Ajustar a `String`
        frecuenciaDao.deleteById(id); // Elimina por ID como cadena
    }

    @Override
    public void deleteFrecuenciasNulas() { // Corregir el nombre del método
        List<Frecuencia> frecuenciasNulas = frecuenciaDao.findAllFrecuenciaNulos(); // Obten frecuencias nulas
        frecuenciasNulas.forEach(f -> deleteById(f.getId())); // Usa `deleteById` para eliminar
    }
}

