package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.model.bd.Usuario;
import cibertec.edu.pe.DSWII_T3_AlexandraVilchez.repository.UsuarioRepository;

@AllArgsConstructor
@Service
public class UsuarioService implements IUsuarioService {

    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario obtenerUsuarioPorNomUsuario(
            String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }
}
