package cibertec.edu.pe.DSWII_T3_AlexandraVilchez.controller;

pimport io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertect.api_rest_ventas.model.bd.Usuario;
import pe.edu.cibertect.api_rest_ventas.model.dto.UsuarioSeguridadDto;
import pe.edu.cibertect.api_rest_ventas.service.DetalleUsuarioService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {

    private DetalleUsuarioService detalleUsuarioService;
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioSeguridadDto> autenticarUsuario(
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password
    ) throws  Exception{
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usuario, password
                    ));
            if(authentication.isAuthenticated()){
                Usuario objUsuario = detalleUsuarioService
                        .obtenerUsuarioXNomusuario(usuario);
                String token = generarToken(objUsuario);
                UsuarioSeguridadDto usuarioSeguridadDto =
                        new UsuarioSeguridadDto(objUsuario.getIdusuario(),
                                usuario, token);
                return new ResponseEntity<>(usuarioSeguridadDto, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            }
        }catch (Exception ex){
            throw  new Exception("Usuario y/u password incorrecto");
        }
    }

    private String generarToken(Usuario usuario){
        String clave = "@Cibertec2024";
        List<GrantedAuthority> grantedAuthorityList =
                detalleUsuarioService.obtenerListaRoles(usuario.getRoles());
        String token = Jwts.builder()
                .setId(usuario.getIdusuario().toString())
                .setSubject(usuario.getNomusuario())
                .claim("authorities",
                        grantedAuthorityList.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList())
                )
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 300000))
                .signWith(SignatureAlgorithm.HS512, clave.getBytes())
                .compact();
        return token;
    }

}