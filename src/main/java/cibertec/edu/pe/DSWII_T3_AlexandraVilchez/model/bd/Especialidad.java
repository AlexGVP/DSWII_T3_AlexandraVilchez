package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.bd;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "especialidad")
public class Especialidad {
    @Id
    private String idesp;
    private String nomesp;
    private Double costo;
}