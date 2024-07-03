package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.service;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;

@Service
public class FileService implements IFileService {

    private final Path pathFolder = Paths.get("documentos");
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("doc", "docx");

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(pathFolder)) {
                Files.createDirectories(pathFolder);
                System.out.println("Directorio creado: " + pathFolder.toAbsolutePath().toString());
            } else {
                System.out.println("Directorio ya existe: " + pathFolder.toAbsolutePath().toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!", e);
        }
    }

    @Override
    public void guardarArchivo(MultipartFile archivo) throws Exception {
        if (archivo.getSize() > 2 * 1024 * 1024) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El archivo excede el tamaño máximo permitido de 2MB");
        }

        String extension = StringUtils.getFilenameExtension(archivo.getOriginalFilename()).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Archivo con extensión no permitida: " + extension);
        }

        Path destino = pathFolder.resolve(archivo.getOriginalFilename());
        System.out.println("Guardando archivo en: " + destino.toAbsolutePath().toString());

        try (InputStream inputStream = archivo.getInputStream()) {
            Files.copy(inputStream, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo guardado exitosamente: " + archivo.getOriginalFilename());
        } catch (FileAlreadyExistsException e) {
            System.err.println("El archivo ya existe: " + archivo.getOriginalFilename());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El archivo ya existe: " + archivo.getOriginalFilename());
        } catch (FileSystemException e) {
            System.err.println("Error del sistema de archivos al guardar el archivo: " + archivo.getOriginalFilename());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error del sistema de archivos al guardar el archivo");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + archivo.getOriginalFilename());
            throw new Exception("Error al guardar el archivo", e);
        }
    }

    @Override
    public void guardarArchivos(List<MultipartFile> archivosList) throws Exception {
        for (MultipartFile archivo : archivosList) {
            guardarArchivo(archivo);
        }
    }
}