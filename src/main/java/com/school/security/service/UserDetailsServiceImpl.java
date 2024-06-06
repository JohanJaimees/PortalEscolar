package com.school.security.service;

import java.util.Optional;

import com.school.security.dao.IUsuarioDao;
import com.school.security.models.Usuario;
import com.school.security.models.UsuarioPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Para registrar el servicio
public class UserDetailsServiceImpl implements UserDetailsService { // Implementa la interfaz correcta

    @Autowired
    private IUsuarioService usuarioService; // Uso del servicio para lógica de negocio
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // Asegúrate de implementar correctamente
        Optional<Usuario> usuarioOptional = usuarioService.findByUsername(username); // Usa Optional para manejo seguro
        if (usuarioOptional.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username); // Manejo de usuario no encontrado
        }

        Usuario usuario = usuarioOptional.get(); // Obtiene el usuario si está presente
        return UsuarioPrincipal.build(usuario); // Retorna UserDetails a partir del Usuario
    }
}


