package com.school.security.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.security.dto.LoginUsuario;
import com.school.security.dto.NuevoUsuario;
import com.school.security.enums.RolNombre;
import com.school.security.models.Rol;
import com.school.security.models.Usuario;
import com.school.security.service.IRolService;
import com.school.security.service.IUsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;
    
  
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> actualizarUsuario(
            @Valid @RequestBody Usuario usuario,
            BindingResult result,
            @PathVariable String id
    ) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Usuario usuarioActual = usuarioService.findById(id).orElse(null);

        if (usuarioActual == null) {
            response.put("mensaje", "Error: No se pudo editar el usuario con el ID: ".concat(id).concat(" no existe en la base de datos"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            usuarioActual.setEnabled(usuario.getEnabled());
            usuarioActual.setPassword(passwordEncoder.encode(usuario.getPassword())); // Asegurarse de codificar la contraseña
            Usuario usuarioActualizado = usuarioService.save(usuarioActual);
            response.put("mensaje", "Usuario actualizado con éxito!");
            response.put("usuario", usuarioActualizado);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar el usuario en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult result){
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(usuarioService.existsByUsername(nuevoUsuario.getUsername())) {
			response.put("mensaje", "El usuario ya existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		 Usuario usuario = new Usuario();
	        usuario.setUsername(nuevoUsuario.getUsername());
	        usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
	        usuario.setEnabled(nuevoUsuario.getEnabled());

		Set<Rol> roles = new HashSet<>();
		if(nuevoUsuario.getRoles().contains("ROLE_ESTUDIANTE")){
			roles.add(rolService.findByRolNombre(RolNombre.ROLE_ESTUDIANTE).get());
		}

		if(nuevoUsuario.getRoles().contains("ROLE_PROFESOR")){
			roles.add(rolService.findByRolNombre(RolNombre.ROLE_PROFESOR).get());
		}

		if(nuevoUsuario.getRoles().contains("ROLE_ADMIN")){
			roles.add(rolService.findByRolNombre(RolNombre.ROLE_ADMIN).get());
		}
		
		if(nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.findByRolNombre(RolNombre.ROLE_ADMIN).get());
		
		usuario.setRoles(roles);
		
		Usuario usuarioCreado = usuarioService.save(usuario);
		
		response.put("mensaje", "Usuario guardado");
		response.put("usuario", usuarioCreado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
   
    @GetMapping("/loginPage") // Cambia el mapeo a /loginPage o cualquier otra URL única
    public String loginPage() {
        return "auth/login";
    }
     
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> usuarioOptional = usuarioService.findByUsername(loginUsuario.getUsername());
        if (usuarioOptional.isEmpty()) {
            response.put("mensaje", "Credenciales inválidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        Usuario usuario = usuarioOptional.get();
        if (!passwordEncoder.matches(loginUsuario.getPassword(), usuario.getPassword())) {
            response.put("mensaje", "Credenciales inválidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            response.put("mensaje", "Login exitoso");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            response.put("mensaje", "Credenciales inválidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/usuarios/{username}")
    public ResponseEntity<?> findUsuario(@PathVariable String username) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = usuarioService.findByUsername(username).orElse(null);
            if (usuario == null) {
                response.put("mensaje", "El usuario: ".concat(username).concat(" no existe en la base de datos"));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
