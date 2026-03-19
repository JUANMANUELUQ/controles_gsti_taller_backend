package com.controles.taller.services;

import com.controles.taller.config.JWTUtils;
import com.controles.taller.dto.TokenDTO;
import com.controles.taller.dto.UsuarioDTO;
import com.controles.taller.model.Rol;
import com.controles.taller.model.Usuario;
import com.controles.taller.repos.UsuarioRepo;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepo usuarioRepo;
    private final JWTUtils jwtUtils;

    public UsuarioService(UsuarioRepo usuarioRepo,JWTUtils jwtUtils) {
        this.usuarioRepo = usuarioRepo;
        this.jwtUtils = jwtUtils;
    }

    public Usuario obtenerUsuario(String nombre) throws Exception {
        Optional<Usuario> accountOptional = usuarioRepo.encontrarPorNombre(nombre);
        if (accountOptional.isEmpty()) {
            throw new Exception("El usuario no esta registrado");
        }
        return accountOptional.get();
    }

    private Map<String, Object> buildClaims(Usuario usuario) {
        return Map.of(
                "rol", usuario.getRol(),
                "nombre", usuario.getNombre(),
                "id", usuario.getId()
        );

    }

    public TokenDTO login(@Valid UsuarioDTO usuarioDTO) throws Exception {
        Usuario usuario = obtenerUsuario(usuarioDTO.nombre());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(usuarioDTO.clave(), usuario.getClave())) {
            throw new Exception("Contrasena invalida");
        }
        Map<String, Object> map = buildClaims(usuario);
        return new TokenDTO(jwtUtils.generarToken(usuario.getNombre(), map));
    }

    public String guardarUsuario(@Valid UsuarioDTO usuarioDTO) throws Exception {
        Usuario usuario;
        try {
            obtenerUsuario(usuarioDTO.nombre());
        } catch (Exception e) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String claveEncriptada=new BCryptPasswordEncoder().encode(usuarioDTO.clave());
            usuario = Usuario.builder()
                    .nombre(usuarioDTO.nombre())
                    .rol(Rol.EMPLOYEE.getDescripcion())
                    .clave(usuarioDTO.clave())
                    .build();
            usuario.setClave(claveEncriptada);
            usuarioRepo.save(usuario);
            return usuario.getNombre();
        }
        return null;
    }
}
