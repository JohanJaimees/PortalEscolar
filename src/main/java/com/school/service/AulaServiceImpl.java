package com.school.service;

import com.school.dao.AulaDao;
import com.school.model.Aula;
import com.school.model.Clase;
import com.school.model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaServiceImpl implements AulaService {

    @Autowired
    private AulaDao aulaDao;

    @Override
    public List<Estudiante> findEstudiantesAula(String id) {
        return aulaDao.findEstudiantesAula(id);
    }

    @Override
    public List<Aula> findAll() {
        return aulaDao.findAll();
    }

    @Override
    public Aula save(Aula aula) {
        return aulaDao.save(aula);
    }

    @Override
    public Optional<Aula> findById(String id) {
        return aulaDao.findById(id);
    }

    @Override
    public List<Clase> findClasesAula(String id) {
        return aulaDao.findClasesAula(id);
    }

    @Override
    public void delete(String id) {
        aulaDao.deleteById(id);
    }
}

