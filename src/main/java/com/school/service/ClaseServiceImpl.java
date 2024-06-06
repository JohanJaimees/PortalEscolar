package com.school.service;

import com.school.dao.ClaseDao;
import com.school.dao.AulaDao;
import com.school.dao.NotaDao;
import com.school.model.Clase;
import com.school.model.Asignacion;
import com.school.model.Aula;
import com.school.model.Nota;
import com.school.reportDto.CursoReporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ClaseServiceImpl implements ClaseService {

    @Autowired
    private ClaseDao claseDao;

    @Autowired
    private AulaDao aulaDao;

    @Autowired
    private NotaDao notaDao;

    @Override
    public Clase save(Clase clase) {
        return claseDao.save(clase);
    }

    @Override
    public Clase update(String id, Clase clase) {
        return claseDao.findById(id).map(existingClase -> {
            existingClase.setNombre(clase.getNombre());
            return claseDao.save(existingClase);
        }).orElseThrow(() -> new IllegalArgumentException("Clase no encontrada con ID: " + id));
    }

    @Override
    public Optional<Clase> findById(String id) {
        return claseDao.findById(id);
    }

    @Override
    public List<Clase> findAll() {
        return claseDao.findAll();
    }

    @Override
    public boolean delete(String id) {
        return findById(id).map(clase -> {
            claseDao.delete(clase);
            return true;
        }).orElse(false);
    }

    @Override
    public byte[] generarReporteCurso(String formato, String idCurso, String idGrado) {
        byte[] data = new byte[0];

        try {
            JasperPrint rpt = JasperFillManager.fillReport(
                "ruta_a_tu_reporte.jasper",
                null,
                new JRBeanCollectionDataSource(getCursoReporte(idCurso, idGrado))
            );

            if ("pdf".equalsIgnoreCase(formato)) {
                data = JasperExportManager.exportReportToPdf(rpt);
            } else {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(rpt));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
                config.setOnePagePerSheet(true);
                config.setIgnoreGraphics(false);
                exporter.setConfiguration(config);
                exporter.exportReport();
                data = out.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    private List<CursoReporte> getCursoReporte(String idCurso, String idGrado) {
        List<CursoReporte> reporte = new ArrayList<>();
        // Añadir lógica para obtener datos para el reporte
        return reporte;
    }

    @Override
    public List<Asignacion> asignacionesPorClase(String idClase) {
        return claseDao.asignacionesPorClase(idClase);
    }

    @Override
    public List<Clase> findByAula(Aula aula) {
        return claseDao.findByAula(aula.getId());
    }
}


