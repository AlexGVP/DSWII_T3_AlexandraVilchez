package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.bd.Notas;
import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.repository.NotasRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class NotasService implements INotasService {
    NotasRepository notasRepository;

    @Override
    public List<Notas> listarNotas() {
        return notasRepository.findAll();
    }
}