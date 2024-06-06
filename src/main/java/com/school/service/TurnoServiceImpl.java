package com.school.service;

import com.school.dao.TurnoDao;
import com.school.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnoDao turnoDao;

    @Override
    public Turno save(Turno turno) {
        return turnoDao.save(turno);
    }

    @Override
    public Optional<Turno> findById(String id) {
        return turnoDao.findById(id);
    }

    @Override
    public Optional<Turno> findByNombre(String nombre) {
        return turnoDao.findByNombre(nombre);
    }

    @Override
    public List<Turno> findAll() {
        return turnoDao.findAll();
    }

    @Override
    public void deleteById(String id) {
        turnoDao.deleteById(id);
    }
}
