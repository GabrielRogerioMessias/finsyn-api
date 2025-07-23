package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.DepositoMetaRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.DepositoMetaRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.DepositoMetaDTOMapper;
import com.messias.finsyn.application.usecases.DepositoMetaUseCases;
import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depositos-metas")
@Tag(name = "Depósitos Metas Financeiras", description = "Operações relacionadas com depósitos em metas financeiras do usuário")
public class DepositoMetaController {
    private final DepositoMetaUseCases depositoMetaUseCases;
    private final DepositoMetaDTOMapper mapper;

    public DepositoMetaController(DepositoMetaUseCases depositoMetaUseCases, DepositoMetaDTOMapper mapper) {
        this.depositoMetaUseCases = depositoMetaUseCases;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Listar todos os depósitos de uma meta financeira",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retornar uma lista com todos os depósitos de uma meta financeira", content = @Content(array = @ArraySchema(schema = @Schema(implementation = DepositoMetaRespostaDTO.class)))),
                    @ApiResponse(responseCode = "404", description = "Meta financeira não encontrada", content = @Content)
            }
    )
    @GetMapping(value = "/{idMetaFinanceira}")
    public ResponseEntity<List<DepositoMetaRespostaDTO>> listarTodos(
            @Parameter(description = "ID da meta financeira desejada", example = "10")
            @PathVariable Long idMetaFinanceira) {
        return ResponseEntity.ok().body(depositoMetaUseCases.buscarTodos(idMetaFinanceira).stream().map(mapper::domainToDtoResposta).toList());
    }

    @Operation(
            summary = "Registra um novo depósito para uma meta financeira",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Depósito registrado com sucesso", content = @Content(schema = @Schema(implementation = DepositoMetaRespostaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Meta financeira não encontrada", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<DepositoMetaRespostaDTO> registrar(@Valid @RequestBody DepositoMetaRegistrarDTO dto) {
        DepositoMeta depositoMeta = mapper.dtoRegistrarToDomain(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.domainToDtoResposta(depositoMetaUseCases.registrar(depositoMeta)));
    }

    @Operation(
            summary = "Deletar um depósito realizado em uma meta financeira",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Depósito deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Depósito não encotrado", content = @Content)
            }
    )
    @DeleteMapping(value = "/{idDepositoMeta}")
    public ResponseEntity<Void> deletar(@PathVariable Long idDepositoMeta) {
        depositoMetaUseCases.deletar(idDepositoMeta);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar um depósito realizado em uma meta financeira",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Depósito encontrado com sucesso", content = @Content(schema = @Schema(implementation = DepositoMetaRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Depósito não encotrado", content = @Content)
            }
    )
    @GetMapping(value = "/by-id/{idDepositoMeta}")
    public ResponseEntity<DepositoMetaRespostaDTO> buscarPorId(
            @Parameter(description = "ID do depósito a ser encontrado", example = "5")
            @PathVariable Long idDepositoMeta) {
        return ResponseEntity.ok().body(mapper.domainToDtoResposta(depositoMetaUseCases.buscarPorId(idDepositoMeta)));
    }

    @Operation(
            summary = "Atualizar um depósito realizado em uma meta financeira",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Depósito atualizado com sucesso", content = @Content(schema = @Schema(implementation = DepositoMetaRespostaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Depósito não encotrado", content = @Content)

            }
    )
    @PutMapping(value = "/{idDepositoMetaExistente}")
    public ResponseEntity<DepositoMetaRespostaDTO> atualizarCategoria(@RequestBody DepositoMetaRegistrarDTO depositoMetaAtualizado,
                                                                      @Parameter(description = "ID do depósito a ser atualizado", example = "6")
                                                                      @PathVariable Long idDepositoMetaExistente) {
        DepositoMeta deposito = mapper.dtoRegistrarToDomain(depositoMetaAtualizado);
        return ResponseEntity.ok().body(mapper.domainToDtoResposta(depositoMetaUseCases.atualizar(deposito, idDepositoMetaExistente)));
    }
}
