package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.LoginDataDTO;
import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRespostaDTO;
import com.messias.finsyn.adapters.inbounds.exceptions.StandardError;
import com.messias.finsyn.adapters.inbounds.mappers.UsuarioDTOMapper;
import com.messias.finsyn.application.usecases.AuthUseCases;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.infrastructure.security.Token;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authenticação", description = "Controlador para registrar e fazer login.")
public class AuthController {
    private final AuthUseCases authUseCase;
    private final UsuarioDTOMapper usuarioDTOMapper;

    public AuthController(AuthUseCases authUseCase, UsuarioDTOMapper usuarioDTOMapper) {
        this.authUseCase = authUseCase;
        this.usuarioDTOMapper = usuarioDTOMapper;
    }

    @Operation(
            summary = "Efetuar login",
            description = "Permite fazer login, e gerar o token JWT",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso", content = @Content(schema = @Schema(implementation = Token.class))),
                    @ApiResponse(responseCode = "404", description = "Usuario não encontrado", content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody @Valid LoginDataDTO login) {
        Token token = authUseCase.login(login);
        return ResponseEntity.ok().body(token);
    }

    @Operation(
            summary = "Salva dasdos do usuário",
            description = "Registra um novo usuário no sistema a partir dos dados informados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioRespostaDTO.class))),
                    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado com e-mail informado", content = @Content(schema = @Schema(implementation = StandardError.class))),
                    @ApiResponse(responseCode = "400", description = "Campos obrigatórios nulos ou em inválidos", content = @Content(schema = @Schema(implementation = StandardError.class)))
            }
    )
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioRespostaDTO> register(@RequestBody @Valid UsuarioRegistrarDTO registrarDTO) {
        Usuario usuario = usuarioDTOMapper.usuarioRegistrarDtoToDomain(registrarDTO);
        return ResponseEntity.ok().body(usuarioDTOMapper.domainToUsuarioRespostaDTO(authUseCase.registrar(usuario)));
    }
}
