package com.school.service;

import com.school.dao.MatriculaDao;
import com.school.model.Matricula;
import com.school.model.Nivel;
import com.school.model.Turno;
import com.school.model.DiaSemana;
import com.school.model.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaDao matriculaDao;

    @Override
    public Matricula save(Matricula matricula) {
        return matriculaDao.save(matricula);
    }

    @Override
    public Optional<Matricula> getMatriculaById(String id) { // Conversión a String
        return matriculaDao.findById(id);
    }

    @Override
    public List<Matricula> findAll() {
        return matriculaDao.findAll();
    }

    @Override
    public boolean delete(String id) { // Elimina por ID usando String
        return matriculaDao.findById(id).map(matricula -> {
            matriculaDao.delete(matricula);
            return true; // Indica éxito
        }).orElse(false); // Indica fracaso si no se encuentra
    }

    @Override
    public List<Nivel> getNiveles() {
        return matriculaDao.getNiveles(); // Asegúrate de que el DAO tenga el método
    }

    @Override
    public List<Turno> getTurnos() {
        return matriculaDao.getTurnos(); // Uso correcto del DAO
    }

    @Override
    public List<DiaSemana> getDias() {
        return matriculaDao.getDias(); // Asegúrate de que el DAO tenga el método
    }

    @Override
    public List<Matricula> getMatriculasPorEstudiante(String idEstudiante) { // Usa String para MongoDB
        return matriculaDao.getMatriculasPorEstudiante(idEstudiante);
    }

    @Override
    public List<Nota> findNotasPorAulaYCurso(String idAula, String idCurso) {
        return matriculaDao.findNotasPorAulaYCurso(idAula, idCurso); // Asegúrate de que el DAO tenga este método
    }
}
