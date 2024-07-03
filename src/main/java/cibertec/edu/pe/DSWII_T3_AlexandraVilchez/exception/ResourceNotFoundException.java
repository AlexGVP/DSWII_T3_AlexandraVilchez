package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
