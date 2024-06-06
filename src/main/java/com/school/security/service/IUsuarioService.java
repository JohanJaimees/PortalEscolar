package com.school.security.service;

import java.util.Optional;

import com.school.security.models.Usuario;

public interface IUsuarioService {
	

    Optional<Usuario> findByUsername(String username); // Devuelve `Optional`
    Boolean existsByUsername(String username); // Devuelve `Boolean`
    Usuario save(Usuario usuario); // Devuelve `Usuario`
    Optional<Usuario> findById(String id); // Devuelve `Optional<Usuario>`
    void delete(String id); // Usa `String` para MongoDB
}
