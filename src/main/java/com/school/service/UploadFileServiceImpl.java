package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils; // Importación correcta

import java.io.IOException;
import java.io.InputStream;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private GridFsTemplate gridFsTemplate; // Para operaciones con GridFS

    @Autowired
    private GridFsOperations gridFsOperations; // Para obtener recursos de GridFS

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename().replace(" ", "");
        String fileId = gridFsTemplate.store(inputStream, filename).toString(); // Sube a GridFS y devuelve el ID
        return fileId;
    }

    @Override
    public boolean deleteFile(String id) {
        if (id != null && !id.isEmpty()) {
            gridFsTemplate.delete(new org.springframework.data.mongodb.core.query.Query(
                org.springframework.data.mongodb.core.query.Criteria.where("_id").is(id)
            )); // Elimina por ID
            return true; // Devuelve `true` si se elimina con éxito
        }
        return false; // Devuelve `false` si el ID es nulo o vacío
    }

    @Override
    public byte[] loadFileById(String id) throws IOException {
        if (id == null || id.isEmpty()) {
            return null; // Manejo de casos nulos o vacíos
        }
        GridFsResource resource = gridFsOperations.getResource(id); // Carga el archivo por ID
        try (InputStream inputStream = resource.getInputStream()) {
            return IOUtils.toByteArray(inputStream); // Devuelve el contenido como bytes
        }
    }
}
