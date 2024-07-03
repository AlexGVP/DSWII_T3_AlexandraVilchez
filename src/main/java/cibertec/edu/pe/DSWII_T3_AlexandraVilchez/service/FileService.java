package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class FileService implements IFileService {

    private final Path rootFolder = Paths.get("archivos");
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("doc", "docx");

    @Override
    public void guardarArchivo(MultipartFile archivo) throws Exception {
        if (archivo.getSize() > 2 * 1024 * 1024) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El archivo excede el tamaño máximo permitido de 2MB");
        }
    }

    @Override
    public void guardarArchivos(List<MultipartFile> archivosList) throws Exception {
        for (MultipartFile archivo : archivosList) {
            String extension = StringUtils.getFilenameExtension(archivo.getOriginalFilename()).toLowerCase();
            if (!ALLOWED_EXTENSIONS.contains(extension)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Archivo con extensión no permitida: " + extension);
            }
        }
    }
}