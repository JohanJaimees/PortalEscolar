package com.school.security.models;



import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails {
    
    private String username; // Nombre de usuario
    
    private String password; // Contraseña
    
    private Boolean enabled; // Indica si está habilitado
    
    private Collection<? extends GrantedAuthority> authorities; // Autoridades

    // Constructor completo para UsuarioPrincipal
    public UsuarioPrincipal(String username, String password,
                            Boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    // Método para construir UsuarioPrincipal desde Usuario
    public static UsuarioPrincipal build(Usuario usuario) {
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name()))
                .collect(Collectors.toList());
        
        return new UsuarioPrincipal(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), authorities);
    }

    // Métodos de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // Definición correcta de serialVersionUID
    private static final long serialVersionUID = 1L; // Asegúrate de que no haya caracteres no válidos
}

