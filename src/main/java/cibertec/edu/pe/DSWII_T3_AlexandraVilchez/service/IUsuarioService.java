package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.service;

import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.bd.Usuario;

public interface IUsuarioService {

    Usuario obtenerUsuarioPorNomUsuario(
            String nomusuario);
}
