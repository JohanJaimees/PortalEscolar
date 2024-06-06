package com.school.service;

import com.school.dao.NivelDao;
import com.school.model.Nivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NivelServiceImpl implements NivelService {

    @Autowired
    private NivelDao nivelDao;

    @Override
    public Nivel save(Nivel nivel) {
        return nivelDao.save(nivel);
    }

    @Override
    public Optional<Nivel> findById(String id) {
        return nivelDao.findById(id);
    }

    @Override
    public Optional<Nivel> findByNombre(String nombre) {
        return nivelDao.findByNombre(nombre);
    }

    @Override
    public List<Nivel> findAll() {
        return nivelDao.findAll();
    }

    @Override
    public void deleteById(String id) {
        nivelDao.deleteById(id);
    }
}
