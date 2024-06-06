package com.school.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "notas")
public class Nota implements Serializable {

    @Id
    private String id;

    private Integer nota_bim1;
    private Integer nota_bim2;
    private Integer nota_bim3;
    private Integer nota_bim4;
    private Double promedio_final;

    @JsonIgnoreProperties({"notas"})
    private Curso curso;

    @JsonIgnoreProperties({"notas"})
    private Estudiante estudiante;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNota_bim1() {
        return nota_bim1;
    }

    public void setNota_bim1(Integer nota_bim1) {
        this.nota_bim1 = nota_bim1;
    }

    public Integer getNota_bim2() {
        return nota_bim2;
    }

    public void setNota_bim2(Integer nota_bim2) {
        this.nota_bim2 = nota_bim2;
    }

    public Integer getNota_bim3() {
        return nota_bim3;
    }

    public void setNota_bim3(Integer nota_bim3) {
        this.nota_bim3 = nota_bim3;
    }

    public Integer getNota_bim4() {
        return nota_bim4;
    }

    public void setNota_bim4(Integer nota_bim4) {
        this.nota_bim4 = nota_bim4;
    }

    public Double getPromedio_final() {
        promedio_final = (double) ((nota_bim1 + nota_bim2 + nota_bim3 + nota_bim4) / 4);
        return promedio_final;
    }

    public void setPromedio_final(Double promedio_final) {
        this.promedio_final = promedio_final;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    private static final long serialVersionUID=1L;
}