package com.school.config;

import com.school.dao.FrecuenciaDao;
import com.school.model.Frecuencia;
import com.school.model.DiaSemana; // Asegúrate de que esta clase esté correctamente definida
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializerfer implements CommandLineRunner {

    @Autowired
    private FrecuenciaDao frecuenciaDao;

    @Override
    public void run(String... args) throws Exception {
        // Limpiar datos anteriores si es necesario
        frecuenciaDao.deleteAll();

        // Crear e inicializar frecuencias
        List<Frecuencia> frecuencias = new ArrayList<>();
        
        // Horarios específicos
        frecuencias.add(crearFrecuencia("07:00", "08:00"));
        frecuencias.add(crearFrecuencia("08:00", "09:00"));
        frecuencias.add(crearFrecuencia("09:00", "10:00"));
        frecuencias.add(crearFrecuencia("10:00", "11:00"));
        frecuencias.add(crearFrecuencia("11:00", "12:00"));
        frecuencias.add(crearFrecuencia("12:00", "13:00"));
        frecuencias.add(crearFrecuencia("13:00", "14:00"));
        frecuencias.add(crearFrecuencia("14:00", "15:00"));
        frecuencias.add(crearFrecuencia("15:00", "16:00"));
        frecuencias.add(crearFrecuencia("16:00", "17:00"));
        frecuencias.add(crearFrecuencia("17:00", "18:00"));
        frecuencias.add(crearFrecuencia("18:00", "19:00"));

        // Guardar frecuencias en la base de datos
        frecuenciaDao.saveAll(frecuencias);
    }

    private Frecuencia crearFrecuencia(String inicio, String fin) {
        Frecuencia frecuencia = new Frecuencia();
        frecuencia.setHorario_inicio(inicio);
        frecuencia.setHorario_fin(fin);
        // Configura los demás campos según sea necesario, por ejemplo, el día de la semana
        // frecuencia.setDia(diaSemana); // Asigna el día de la semana adecuado si es necesario
        return frecuencia;
    }
}

