package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.controller;

import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.dto.ArchivoDto;
import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/filesdoc")
@PreAuthorize("hasRole('Administrador')")
public class FileController {
    private FileService fileService;

    @PostMapping("")
    public ResponseEntity<ArchivoDto> subirArchivos(
            @RequestParam("files")List<MultipartFile> multipartFileList
    ) throws Exception{
        fileService.guardarArchivos(multipartFileList);
        return new ResponseEntity<>(ArchivoDto.builder()
                .mensaje("Archivos subidos correctamente").build(),
                HttpStatus.OK);
    }
}