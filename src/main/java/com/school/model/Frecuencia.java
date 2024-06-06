package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;

@Document(collection = "frecuencias")
public class Frecuencia implements Serializable {

    @Id
    private String id; // ID como cadena para MongoDB
    
    private String horario_inicio; // Campo simple

    private String horario_fin; // Campo simple

    @DBRef
    private Clase clase; // Referencia a otro documento

    @DBRef
    private DiaSemana dia; // Referencia a otro documento

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHorario_inicio() {
        return horario_inicio;
    }

    public void setHorario_inicio(String horario_inicio) {
        this.horario_inicio = horario_inicio;
    }

    public String getHorario_fin() {
        return horario_fin;
    }

    public void setHorario_fin(String horario_fin) {
        this.horario_fin = horario_fin;
    }

    public DiaSemana getDia() {
        return dia;
    }

    public void setDia(DiaSemana dia) {
        this.dia = dia;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    private static final long serialVersionUID = 1L; // Asegúrate de eliminar caracteres no válidos
}
