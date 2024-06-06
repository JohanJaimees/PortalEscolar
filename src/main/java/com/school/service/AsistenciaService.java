package com.school.service;

import com.school.model.Asistencia;
import com.school.reportDto.AsistenciaReporte;

import java.util.List;

public interface AsistenciaService {

    // Método para generar un reporte de asistencia en formato de bytes
    byte[] generarReporteAsistencia(String tipo, String fecha);

    // Encuentra asistencias por fecha
    List<Asistencia> findByFecha(String fecha);

    // Encuentra asistencias por fecha y aula
    List<Asistencia> findAsistenciaByFechaAula(String fecha, String idAula);

    // Obtiene datos de asistencia por día
    AsistenciaReporte obtenerDatosAsistenciaPorDia(String fecha);

    // Actualiza asistencias y devuelve la lista actualizada
    List<Asistencia> updateAsistencias(List<Asistencia> asistencias);
}
