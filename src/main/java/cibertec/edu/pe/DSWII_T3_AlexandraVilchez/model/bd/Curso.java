package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.bd;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "curso")
public class Curso {
    @Id
    private  String idcurso;
    private String nomcurso;
    private Integer credito;

    @OneToMany(mappedBy = "curso",
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Notas> notas = new HashSet<>();
}