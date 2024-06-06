package com.school.service;

import com.school.dao.AsistenciaDao;
import com.school.model.Asistencia;
import com.school.reportDto.AsistenciaReporte;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciaDao asistenciaDao;

    @Override
    public byte[] generarReporteAsistencia(String tipo, String fecha) {
        byte[] data = null;

        AsistenciaReporte asistenciaDTO = obtenerDatosAsistenciaPorDia(fecha);

        if (asistenciaDTO == null) {
            return data; // Asegúrate de tener punto y coma aquí
        }

        List<AsistenciaReporte> lista = new ArrayList<>();
        lista.add(asistenciaDTO);

        try {
            JasperPrint rpt = JasperFillManager.fillReport("ruta_a_tu_archivo_jasper", null, new JRBeanCollectionDataSource(lista));

            if (tipo.equals("pdf")) {
                data = JasperExportManager.exportReportToPdf(rpt);
            } else {
                SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
                config.setOnePagePerSheet(true);
                config.setIgnoreGraphics(false);

                ByteArrayOutputStream out = new ByteArrayOutputStream();

                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(rpt));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                exporter.exportReport();

                data = out.toByteArray();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data; // Asegúrate de tener punto y coma aquí
    }

    @Override
    public List<Asistencia> findByFecha(String fecha) {
        return asistenciaDao.findByFecha(fecha); // Asegúrate de tener punto y coma
    }

    @Override
    public List<Asistencia> findAsistenciaByFechaAula(String fecha, String idAula) {
        return asistenciaDao.findAsistenciaByFechaAula(fecha, idAula); // Asegúrate de tener punto y coma
    }

    @Override
    public AsistenciaReporte obtenerDatosAsistenciaPorDia(String fecha) {
        List<Asistencia> asistencias = findByFecha(fecha);

        if (asistencias.isEmpty()) { // Ajusta a isEmpty() para listas
            return null;
        }

        Long puntual = 0L;
        Long tardanza = 0L;
        Long inasistencia = 0L;

        for (Asistencia asistencia : asistencias) { // Asegúrate de tener punto y coma
            if (asistencia.getEstado().equals("PUNTUAL")) {
                puntual++;
            }
            if (asistencia.getEstado().equals("TARDANZA")) {
                tardanza++;
            }
            if (asistencia.getEstado().equals("FALTA")) {
                inasistencia++;
            }
        }

        AsistenciaReporte asistenciaDTO = new AsistenciaReporte(fecha, puntual, tardanza, inasistencia);

        return asistenciaDTO; // Asegúrate de tener punto y coma
    }

    @Override
    public List<Asistencia> updateAsistencias(List<Asistencia> asistencias) {
        return asistenciaDao.saveAll(asistencias); // Asegúrate de tener punto y coma
    }
}
