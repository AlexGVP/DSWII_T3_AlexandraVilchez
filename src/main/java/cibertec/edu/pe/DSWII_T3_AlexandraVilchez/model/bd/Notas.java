package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.bd;

import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.bd.pk.AlumnoCurso;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notas")
public class Notas {
    @EmbeddedId
    private AlumnoCurso alumnoCursoId;
    private Double exaparcial;
    private Double exafinal;
    @ManyToOne
    @MapsId("idalumno")
    @JoinColumn(name = "idalumno")
    @JsonBackReference
    private Alumno alumno;
    @ManyToOne
    @MapsId("idcurso")
    @JoinColumn(name = "idcurso")
    @JsonBackReference
    private Curso curso;

}