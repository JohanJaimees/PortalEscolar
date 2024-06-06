package com.school.service;

import com.school.dao.EstudianteDao;
import com.school.model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteDao estudianteDao;

    @Override
    public Estudiante save(Estudiante estudiante) {
        return estudianteDao.save(estudiante);
    }

    @Override
    public List<Estudiante> saveAll(List<Estudiante> estudiantes) {
        return estudianteDao.saveAll(estudiantes);
    }

    @Override
    public Optional<Estudiante> getEstudianteById(String id) {
        return estudianteDao.findById(id);
    }

    @Override
    public List<Estudiante> findAll() {
        return estudianteDao.findAll();
    }

    @Override
    public Page<Estudiante> findAll(Pageable pageable) {
        return estudianteDao.findAll(pageable);
    }

    @Override
    public boolean delete(String id) {
        Optional<Estudiante> estudiante = estudianteDao.findById(id);
        if (estudiante.isPresent()) {
            estudianteDao.delete(estudiante.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Estudiante> findByUsernameAndPassword(String username, String password) {
        return estudianteDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public Optional<Estudiante> findByDni(String dni) {
        return estudianteDao.findByDni(dni);
    }

    @Override
    public Estudiante update(String id, Estudiante updatedEstudiante) {
        Optional<Estudiante> existingEstudiante = estudianteDao.findById(id);
        if (existingEstudiante.isPresent()) {
            Estudiante estudiante = existingEstudiante.get();
            // Actualizar campos
            estudiante.setNombres(updatedEstudiante.getNombres());
            estudiante.setApellidoPaterno(updatedEstudiante.getApellidoPaterno());
            estudiante.setApellidoMaterno(updatedEstudiante.getApellidoMaterno());
            estudiante.setFechaNacimiento(updatedEstudiante.getFechaNacimiento());
            estudiante.setDni(updatedEstudiante.getDni());
            estudiante.setDomicilio(updatedEstudiante.getDomicilio());
            estudiante.setCelular(updatedEstudiante.getCelular());
            estudiante.setSexo(updatedEstudiante.getSexo());
            estudiante.setCorreo(updatedEstudiante.getCorreo());
            estudiante.setAulaEstudiante(updatedEstudiante.getAulaEstudiante());
            estudiante.setApoderado(updatedEstudiante.getApoderado());
            estudiante.setGrado(updatedEstudiante.getGrado());
            estudiante.setUsuario(updatedEstudiante.getUsuario());
            estudiante.setTurno(updatedEstudiante.getTurno());
            estudiante.setNivel(updatedEstudiante.getNivel());
            estudiante.setAsistencias(updatedEstudiante.getAsistencias());
            estudiante.setNotas(updatedEstudiante.getNotas());
            
            return estudianteDao.save(estudiante); // Guardar estudiante actualizado
        }
        return null; // Devuelve `null` si el estudiante no existe
    }

    @Override
    public Estudiante loginUsuario(String username, String password) {
        Optional<Estudiante> optionalEstudiante = estudianteDao.findByUsernameAndPassword(username, password);
        return optionalEstudiante.orElse(null); // Devuelve `null` si no se encuentra el estudiante
    }
}


