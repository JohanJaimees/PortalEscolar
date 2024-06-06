package com.school.service;

import com.school.dao.ApoderadoDao;
import com.school.model.Apoderado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ApoderadoServiceImpl implements ApoderadoService {

    @Autowired
    private ApoderadoDao apoderadoDao;

    @Override
    @Transactional
    public Apoderado save(Apoderado apoderado) {
        return apoderadoDao.save(apoderado);
    }

    @Override
    @Transactional
    public Optional<Apoderado> getApoderadoById(String id) {
        return apoderadoDao.findById(id);
    }

    @Override
    @Transactional
    public List<Apoderado> findAll() {
        return apoderadoDao.findAll();
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        return getApoderadoById(id).map(apoderado -> {
            apoderadoDao.delete(apoderado);
            return true;
        }).orElse(false);
    }

    @Override
    @Transactional
    public Apoderado findByDni(String dni) {
        return apoderadoDao.findByDni(dni);
    }
}
