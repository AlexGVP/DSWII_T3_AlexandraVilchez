package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.dto;

import lombok.Data;

@Data
public class NotasDto extends DtoEntity {
    private String nomalumnoAlumno;
    private String apealumnoAlumno;
    private String nomcursoCurso;
    private Double exaParcial;
    private Double exaFinal;
}