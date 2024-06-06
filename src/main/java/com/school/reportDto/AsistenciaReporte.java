package com.school.reportDto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "asistencia_reporte") // Indica la colección en MongoDB
public class AsistenciaReporte {

    @Id
    private String id; // Campo de ID para MongoDB
    
    private String fecha;
    private Long puntual;
    private Long tardanza;
    private Long inasistencia;

    public AsistenciaReporte(String fecha, Long puntual, Long tardanza, Long inasistencia) {
        this.fecha = fecha;
        this.puntual = puntual;
        this.tardanza = tardanza;
        this.inasistencia = inasistencia;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Long getPuntual() {
        return puntual;
    }

    public void setPuntual(Long puntual) {
        this.puntual = puntual;
    }

    public Long getTardanza() {
        return tardanza;
    }

    public void setTardanza(Long tardanza) {
        this.tardanza = tardanza;
    }

    public Long getInasistencia() {
        return inasistencia;
    }

    public void setInasistencia(Long inasistencia) {
        this.inasistencia = inasistencia; // Asegúrate de que el código esté correctamente cerrado
    }
}
