package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.TransacaoRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.TransacaoRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.TransacaoDTOMapper;
import com.messias.finsyn.application.usecases.TransacaoUseCase;
import com.messias.finsyn.domain.models.transacao.Transacao;
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
@RequestMapping(value = "/transacoes")
@Tag(name = "Transações", description = "Operações relacionadas com as transações do usuário - Receita e Despesa")
public class TransacaoController {
    private final TransacaoUseCase transacaoUseCase;
    private final TransacaoDTOMapper mapper;

    public TransacaoController(TransacaoUseCase transacaoUseCase, TransacaoDTOMapper dtoMapper) {
        this.transacaoUseCase = transacaoUseCase;
        this.mapper = dtoMapper;
    }

    @Operation(
            summary = "Listar todas as transações",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de Transações retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransacaoRespostaDTO.class))))
            })
    @GetMapping
    public ResponseEntity<List<TransacaoRespostaDTO>> listarTodas() {
        return ResponseEntity.ok().body(transacaoUseCase.buscarTodas()
                .stream()
                .map(mapper::domainToTransacaoRespostaDTO)
                .toList());
    }

    @Operation(
            summary = "Registrar uma nova transação financeira",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Transação criada com sucesso", content = @Content(schema = @Schema(implementation = TransacaoRespostaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            })
    @PostMapping
    public ResponseEntity<TransacaoRespostaDTO> registrar(@RequestBody @Valid TransacaoRegistrarDTO registrarDTO) {
        Transacao novaTransacao = transacaoUseCase.registrarTransacao(mapper.transacaoRegistrarDtoToDomain(registrarDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.domainToTransacaoRespostaDTO(novaTransacao));
    }

    @Operation(
            summary = "Excluir transação financeira",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Transação excluída com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Transação não encontrada", content = @Content)
            })
    @DeleteMapping(value = "/{idTransacao}")
    public ResponseEntity<Void> excluir(
            @Parameter(description = "ID da transação a ser excluída", example = "1")
            @PathVariable Long idTransacao) {
        transacaoUseCase.excluirTransacao(idTransacao);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar uma transação por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transação encontrada", content = @Content(schema = @Schema(implementation = TransacaoRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Transação não encontrada", content = @Content)
            })
    @GetMapping(value = "/{idTransacao}")
    public ResponseEntity<TransacaoRespostaDTO> buscarPorId(
            @Parameter(description = "ID da transação a ser encontrada", example = "2")
            @PathVariable Long idTransacao) {
        return ResponseEntity.ok().body(mapper.domainToTransacaoRespostaDTO(transacaoUseCase.buscarPorId(idTransacao)));
    }

    @Operation(
            summary = "Atualizar propriedades de uma transação",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Propriedades da transação atualizadas com sucesso", content = @Content(schema = @Schema(implementation = TransacaoRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Transação não encontrada", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            })
    @PutMapping(value = "/{idTransacao}")
    public ResponseEntity<TransacaoRespostaDTO> atualizarTransacao(@RequestBody TransacaoRegistrarDTO transacaoAtualizada,
                                                                   @Parameter(description = "ID da transação a ser atualizada", example = "3")
                                                                   @PathVariable Long idTransacao) {
        Transacao transacao = mapper.transacaoRegistrarDtoToDomain(transacaoAtualizada);
        return ResponseEntity.ok().body(mapper.domainToTransacaoRespostaDTO(transacaoUseCase.atualizar(transacao, idTransacao)));
    }
}
