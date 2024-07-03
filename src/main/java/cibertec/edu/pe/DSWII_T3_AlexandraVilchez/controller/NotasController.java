package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.controller;

import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.dto.DtoEntity;
import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.dto.NotasDto;
import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.service.INotasService;
import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.util.DtoUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/pubs-dto")
@PreAuthorize("hasRole('Supervisor')")
public class NotasController {

    private final INotasService iNotasService;

    @GetMapping("")
    public ResponseEntity<List<DtoEntity>> listarNotas() {
        List<DtoEntity> notasDtoList = iNotasService.listarNotas()
                .stream()
                .map(x -> new DtoUtil().convertirADto(x, new NotasDto()))
                .collect(Collectors.toList());

        if (notasDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notasDtoList, HttpStatus.OK);
    }
}