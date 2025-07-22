package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.UsuarioDTOMapper;
import com.messias.finsyn.application.usecases.UsuarioUseCases;
import com.messias.finsyn.domain.models.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioUseCases usuarioCadastroUseCase;
    private final UsuarioDTOMapper usuarioDTOMapper;

    public UsuarioController(UsuarioUseCases usuarioCadastroUseCase,
                             UsuarioDTOMapper usuarioDTOMapper) {
        this.usuarioCadastroUseCase = usuarioCadastroUseCase;
        this.usuarioDTOMapper = usuarioDTOMapper;
    }

    @PutMapping
    public ResponseEntity<UsuarioRespostaDTO> atualizarDados(@RequestBody UsuarioRegistrarDTO usuarioRegistrarDTO) {
        Usuario usuario = usuarioCadastroUseCase.atualizar(usuarioDTOMapper.usuarioRegistrarDtoToDomain(usuarioRegistrarDTO));
        return ResponseEntity.ok().body(usuarioDTOMapper.domainToUsuarioRespostaDTO(usuario));
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<UsuarioRespostaDTO> buscarPorId(@PathVariable String uuid) {
        return ResponseEntity.ok().body(usuarioDTOMapper.domainToUsuarioRespostaDTO(usuarioCadastroUseCase.buscarPorId(UUID.fromString(uuid))));
    }
}
