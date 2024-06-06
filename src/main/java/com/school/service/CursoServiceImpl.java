package com.school.service;

import com.school.dao.CursoDao;
import com.school.model.Curso;
import com.school.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoDao cursoDao;

    @Override
    public Curso save(Curso curso) {
        return cursoDao.save(curso);
    }

    @Override
    public Optional<Curso> findById(String id) {
        return cursoDao.findById(id);
    }

    @Override
    public List<Curso> findAll() {
        return cursoDao.findAll();
    }

    @Override
    public boolean delete(String id) {
        if (cursoDao.existsById(id)) {
            cursoDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Curso update(String id, Curso curso) {
        if (cursoDao.existsById(id)) {
            curso.setId(id);
            return cursoDao.save(curso);
        }
        return null;
    }
}
