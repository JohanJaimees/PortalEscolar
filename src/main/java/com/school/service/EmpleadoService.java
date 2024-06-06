package com.school.service;

import com.school.model.Clase;
import com.school.model.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {

    Empleado save(Empleado empleado);

    Optional<Empleado> getEmpleadoById(String id);

    List<Empleado> findAll();

    Page<Empleado> findAll(Pageable pageable);

    boolean delete(String id);

    List<Clase> findClasesProfesor(String id);

    Empleado findByDni(String dni);

    Empleado update(String id, Empleado empleado);
}
