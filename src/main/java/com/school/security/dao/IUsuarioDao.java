package com.school.security.dao;

import com.school.security.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUsuarioDao extends MongoRepository<Usuario, String> { // El ID es `String`

    Optional<Usuario> findByUsername(String username); // Devuelve un usuario por nombre de usuario

    Boolean existsByUsername(String username); // Devuelve verdadero si el usuario existe

    // El método `findById` está implícitamente disponible a través de `MongoRepository`
    // Puedes eliminarlo si el uso de `Optional<Usuario>` es el correcto en tu implementación
}
