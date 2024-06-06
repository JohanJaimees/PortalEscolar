package com.school.service;


import com.school.dao.EmpleadoDao;
import com.school.model.Clase;
import com.school.model.Empleado;
import com.school.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoDao empleadoDao;

    @Override
    public Empleado save(Empleado empleado) {
        return empleadoDao.save(empleado);
    }

    @Override
    public Optional<Empleado> getEmpleadoById(String id) {
        return empleadoDao.findById(id);
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoDao.findAll();
    }

    @Override
    public Page<Empleado> findAll(Pageable pageable) {
        return empleadoDao.findAll(pageable);
    }

    @Override
    public boolean delete(String id) {
        if (empleadoDao.existsById(id)) {
            empleadoDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Clase> findClasesProfesor(String id) {
        return empleadoDao.findClasesProfesor(id);
    }

    @Override
    public Empleado findByDni(String dni) {
        return empleadoDao.findByDni(dni);
    }

    @Override
    public Empleado update(String id, Empleado empleado) {
        if (empleadoDao.existsById(id)) {
            empleado.setId(id);
            return empleadoDao.save(empleado);
        }
        return null;
    }
}
