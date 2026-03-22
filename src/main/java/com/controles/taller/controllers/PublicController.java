package com.controles.taller.controllers;

import com.controles.taller.dto.MessageDTO;
import com.controles.taller.dto.TokenDTO;
import com.controles.taller.dto.UsuarioDTO;
import com.controles.taller.services.UsuarioService;
import com.controles.taller.util.FileLogger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<MessageDTO<TokenDTO>> login(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception{
        FileLogger.log("Intento de incio de sesion de "+usuarioDTO.nombre());
        TokenDTO token = usuarioService.login(usuarioDTO);
        FileLogger.log(usuarioDTO.nombre()+" inicio sesion exitosamente");
        return ResponseEntity.ok(new MessageDTO<>(false, token));
    }

}
