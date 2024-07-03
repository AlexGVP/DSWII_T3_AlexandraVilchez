package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.bd.Rol;

@Repository
public interface RolRepository extends
        JpaRepository<Rol, Integer> {
    Rol findByNomrol(String nomrol);
}