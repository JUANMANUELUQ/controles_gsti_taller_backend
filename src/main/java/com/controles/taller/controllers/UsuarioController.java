package com.controles.taller.controllers;

import com.controles.taller.dto.MessageDTO;
import com.controles.taller.dto.UsuarioDTO;
import com.controles.taller.services.UsuarioService;
import com.controles.taller.util.FileLogger;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<MessageDTO<String>> registrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception{
        String usuario=usuarioService.guardarUsuario(usuarioDTO);
        FileLogger.log("Nuevo usuario registrado: "+usuario);
        return ResponseEntity.ok(new MessageDTO<>(false, usuario));
    }

}
