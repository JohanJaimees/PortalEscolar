package com.school.security;

import com.school.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class MainSecurity {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF si no es necesario
            .cors(cors -> cors.disable()) // Desactiva CORS si no es necesario
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/auth/login", "/auth/nuevo").permitAll() // Permite acceso a estas rutas sin autenticación
                .anyRequest().permitAll() // Requiere autenticación para todas las demás rutas
            )
            .formLogin(form -> form
                .loginPage("/auth/login") // Define la página de login
                .loginProcessingUrl("/auth/login") // URL al que se envía el formulario de login
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

                        for (GrantedAuthority authority : authorities) {
                            if (authority.getAuthority().equals("ROLE_ESTUDIANTE")) {
                                response.sendRedirect("/cursoestudiante");
                                return;
                            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                                response.sendRedirect("/cursos");
                                return;
                            }
                            // Agrega más roles y redirecciones si es necesario
                        }

                        response.sendRedirect("/cursos"); // Redirección predeterminada
                    }
                })
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/auth/login?logout")
                .permitAll()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Usa sesiones si es necesario
            );

        return http.build();
    }
}
