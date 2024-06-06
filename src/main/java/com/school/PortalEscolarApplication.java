package com.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.school") // Ajusta para incluir todos los paquetes relevantes
public class PortalEscolarApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalEscolarApplication.class, args);
    }
}

