package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRespostaDTO;
import com.messias.finsyn.adapters.inbounds.exceptions.StandardError;
import com.messias.finsyn.adapters.inbounds.mappers.UsuarioDTOMapper;
import com.messias.finsyn.application.usecases.UsuarioUseCases;
import com.messias.finsyn.domain.models.usuario.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Usuário", description = "Controlados para atualizar dados do usuário e buscar por ID")
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

    @Operation(
            summary = "Atualizar dados do usuário",
            description = "Atualiza os dados de NOME, SOBRENOME e SALDO do usuário logado no sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dados do usuário atualizados com sucesso", content = @Content(schema = @Schema(implementation = UsuarioRespostaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Campos obrigatórios nulos ou em inválidos", content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @PutMapping
    public ResponseEntity<UsuarioRespostaDTO> atualizarDados(@RequestBody UsuarioRegistrarDTO usuarioRegistrarDTO) {
        Usuario usuario = usuarioCadastroUseCase.atualizar(usuarioDTOMapper.usuarioRegistrarDtoToDomain(usuarioRegistrarDTO));
        return ResponseEntity.ok().body(usuarioDTOMapper.domainToUsuarioRespostaDTO(usuario));
    }

    @Operation(
            summary = "Buscar dados do usuário pelo ID",
            description = "Busca o usuário autenticado pelo ID - não traz de outros usuários",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Traz os dados do usuário autenticado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Caso o ID passado seje diferente do usuário autenticado", content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @GetMapping(value = "/{uuid}")
    public ResponseEntity<UsuarioRespostaDTO> buscarPorId(@PathVariable String uuid) {
        return ResponseEntity.ok().body(usuarioDTOMapper.domainToUsuarioRespostaDTO(usuarioCadastroUseCase.buscarPorId(UUID.fromString(uuid))));
    }
}
