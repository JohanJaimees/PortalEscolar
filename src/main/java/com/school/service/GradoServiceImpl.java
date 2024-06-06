package com.school.service;

import com.school.dao.GradoDao;
import com.school.model.Grado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GradoServiceImpl implements GradoService {

    private final GradoDao gradoDao;

    @Autowired
    public GradoServiceImpl(GradoDao gradoDao) {
        this.gradoDao = gradoDao;
    }

    @Override
    public Grado save(Grado grado) {
        return gradoDao.save(grado);
    }

    @Override
    public Optional<Grado> findById(String id) {
        return gradoDao.findById(id);
    }

    @Override
    public Optional<Grado> findByNombre(String nombre) {
        return gradoDao.findByNombre(nombre);
    }

    @Override
    public List<Grado> findAll() {
        return gradoDao.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        gradoDao.deleteById(id);
        return true;
    }
}

