package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.DBRef;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "clases")
public class Clase implements Serializable {

    @Id
    private String id;

    private String nombre; // Campo para el nombre
    private String seccion; // Campo para la sección
    private String turno; // Campo para el turno

    @DBRef
    @NotNull(message = "El aula no puede estar vacía.")
    private Aula aula; // Referencia a otro documento

    @DBRef
    @NotNull(message = "El curso no puede estar vacío.")
    private Curso curso; // Referencia a otro documento

    @DBRef
    private Nivel nivel; // Campo para nivel

    @DBRef
    private Grado grado; // Campo para grado

    @DBRef
    private Empleado empleado; // Referencia a un documento Empleado (nullable)

    @DBRef
    private List<Material> materiales = new ArrayList<>();
   
    @DBRef
    private List<Frecuencia> frecuencias = new ArrayList<>(); // Corrección: usar "new"

    @DBRef
    private List<DiaSemana> diasSemana = new ArrayList<>(); // Lista de días de la semana
    

    // Constructor sin argumentos
    public Clase() {
    }

    // Getters y setters para los campos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() { // Método para obtener el nombre
        return nombre;
    }

    public void setNombre(String nombre) { // Método para establecer el nombre
        this.nombre = nombre;
    }

    public String getSeccion() { // Método para obtener la sección
        return seccion;
    }

    public void setSeccion(String seccion) { // Método para establecer la sección
        this.seccion = seccion;
    }

    public String getTurno() { // Método para obtener el turno
        return turno;
    }

    public void setTurno(String turno) { // Método para establecer el turno
        this.turno = turno;
    }

    public Nivel getNivel() { // Método para obtener el nivel
        return nivel;
    }

    public void setNivel(Nivel nivel) { // Método para establecer el nivel
        this.nivel = nivel;
    }

    public Grado getGrado() { // Método para obtener el grado
        return grado;
    }

    public void setGrado(Grado grado) { // Método para establecer el grado
        this.grado = grado;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<Material> materiales) {
        this.materiales = materiales;
    }

    public List<Frecuencia> getFrecuencias() {
        return frecuencias;
    }

    public void setFrecuencias(List<Frecuencia> frecuencias) {
        this.frecuencias = frecuencias;
    }

    public List<DiaSemana> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<DiaSemana> diasSemana) {
        this.diasSemana = diasSemana;
    }

    private static final long serialVersionUID = 1L; // Para compatibilidad con Serializable
}
