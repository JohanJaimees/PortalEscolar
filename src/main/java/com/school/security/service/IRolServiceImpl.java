package com.school.security.service;

import java.util.Optional;

import com.school.security.dao.IRolDao;
import com.school.security.enums.RolNombre;
import com.school.security.models.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Usa con precaución en MongoDB

@Service
@Transactional // Indica que los métodos pueden tener transacciones
public class IRolServiceImpl implements IRolService { // Clase que implementa la interfaz IRolService

    @Autowired
    private IRolDao rolDao; // Inyección del DAO para MongoDB

    @Override
    public Optional<Rol> findByRolNombre(RolNombre rolNombre) { // Encuentra un rol por nombre
        return rolDao.findByRolNombre(rolNombre);
    }

    @Override
    public void save(Rol rol) { // Guarda un rol en MongoDB
        rolDao.save(rol);
    }

}
