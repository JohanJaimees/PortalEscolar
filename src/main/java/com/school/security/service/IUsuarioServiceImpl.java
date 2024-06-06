package com.school.security.service;

import java.util.Optional;

import com.school.security.dao.IUsuarioDao;
import com.school.security.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Asegúrate de tener esta anotación para registrar el servicio
@Transactional // Permite transacciones, si es necesario
public class IUsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao; // Inyección del DAO para acceder a la base de datos

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioDao.findByUsername(username); // Devuelve un Optional
    }

    @Override
    public Boolean existsByUsername(String username) {
        return usuarioDao.existsByUsername(username); // Devuelve un Boolean
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario); // Guarda un usuario
    }

    @Override
    public Optional<Usuario> findById(String id) {
        return usuarioDao.findById(id); // Busca por ID
    }

    @Override
    public void delete(String id) {
        usuarioDao.deleteById(id); // Elimina por ID
    }
}
