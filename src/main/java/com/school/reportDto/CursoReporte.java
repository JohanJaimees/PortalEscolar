package com.school.reportDto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "curso_reporte") // Nombre de la colección en MongoDB
public class CursoReporte {

    @Id
    private String id; // Campo para ID en MongoDB
    
    private String nombreAula;
    private String nombreCurso;
    private Long aprobados;
    private Long desaprobados;

    public CursoReporte() {
        // Constructor por defecto
    }

    public CursoReporte(String nombreAula, String nombreCurso, Long aprobados, Long desaprobados) {
        this.nombreAula = nombreAula;
        this.nombreCurso = nombreCurso;
        this.aprobados = aprobados;
        this.desaprobados = desaprobados;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreAula() {
        return nombreAula; // Revisa estructura y asegura el cierre de las líneas
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Long getAprobados() {
        return aprobados; // Asegúrate de cerrar las líneas con punto y coma
    }

    public void setAprobados(Long aprobados) {
        this.aprobados = aprobados; // Verifica caracteres invisibles
    }

    public Long getDesaprobados() {
        return desaprobados;
    }

    public void setDesaprobados(Long desaprobados) {
        this.desaprobados = desaprobados; // Asegúrate de que el método termine correctamente
    }
}
