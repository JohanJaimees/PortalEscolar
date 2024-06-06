package com.school.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {

    /**
     * Sube un archivo a GridFS.
     * 
     * @param file El archivo para subir
     * @return El ID del archivo subido
     * @throws IOException Si hay un error al leer el archivo
     */
    String uploadFile(MultipartFile file) throws IOException;

    /**
     * Elimina un archivo por su ID en GridFS.
     * 
     * @param id El ID del archivo a eliminar
     * @return `true` si se elimina con éxito, `false` si falla
     */
    boolean deleteFile(String id);

    /**
     * Carga un archivo por su ID en GridFS.
     * 
     * @param id El ID del archivo a cargar
     * @return Un arreglo de bytes con el contenido del archivo
     */
    byte[] loadFileById(String id) throws IOException;
}
