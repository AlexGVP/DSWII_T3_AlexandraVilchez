package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.repository;

import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.bd.Notas;
import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.bd.pk.AlumnoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotasRepository extends JpaRepository<Notas, AlumnoCurso> {
}