package com.school.security.dao;

import com.school.security.enums.RolNombre;
import com.school.security.models.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolDao extends MongoRepository<Rol, String> { // Usando MongoRepository

    Optional<Rol> findByRolNombre(RolNombre rolNombre); // Revisa caracteres extraños y asegúrate de que el código esté bien escrito

}
