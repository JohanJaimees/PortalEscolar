package com.school.service;

import com.school.model.Apoderado;

import java.util.List;
import java.util.Optional;

public interface ApoderadoService {

    Apoderado save(Apoderado apoderado);

    Optional<Apoderado> getApoderadoById(String id);

    List<Apoderado> findAll();

    boolean delete(String id);

    Apoderado findByDni(String dni);
}