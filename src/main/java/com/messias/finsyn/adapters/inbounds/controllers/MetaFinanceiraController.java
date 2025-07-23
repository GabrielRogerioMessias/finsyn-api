package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.MetaFinanceiraRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.MetaFinanceiraRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.MetaFinanceiraDTOMapper;
import com.messias.finsyn.application.usecases.MetaFinanceiraUseCases;
import com.messias.finsyn.domain.models.meta_financeira.MetaFinanceira;
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
@RequestMapping(value = "/metas-financeiras")
@Tag(name = "Metas Financeiras", description = "Operações relacionadas com as metas financeiras do usuário")
public class MetaFinanceiraController {
    private final MetaFinanceiraUseCases useCases;
    private final MetaFinanceiraDTOMapper mapper;

    public MetaFinanceiraController(MetaFinanceiraUseCases useCases, MetaFinanceiraDTOMapper mapper) {
        this.useCases = useCases;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Listar todas as metas financeiras",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de Metas Financeiras é retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MetaFinanceiraRespostaDTO.class))))
            }
    )
    @GetMapping
    public ResponseEntity<List<MetaFinanceiraRespostaDTO>> listarTodas() {
        return ResponseEntity.ok().body(useCases.buscarTodas().stream().map(mapper::domainToRespostaDTO).toList());
    }

    @Operation(
            summary = "Registrar uma nova meta financeira",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Meta financeira registrada com sucesso", content = @Content(schema = @Schema(implementation = MetaFinanceiraRespostaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<MetaFinanceiraRespostaDTO> registrar(@Valid @RequestBody MetaFinanceiraRegistrarDTO registrarDTO) {
        MetaFinanceira novaMeta = mapper.metaRegistrarDtoToDomain(registrarDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.domainToRespostaDTO(useCases.criarNovaMeta(novaMeta)));
    }

    @Operation(
            summary = "Deletar uma meta financeira",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Meta financeira excluída com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Meta financeira não encontrada", content = @Content)
            }
    )
    @DeleteMapping(value = "/{idMeta}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da meta financeira a ser excluída", example = "2")
            @PathVariable Long idMeta) {
        useCases.deletarMeta(idMeta);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar uma meta financeira pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Meta financeira encontrada", content = @Content(schema = @Schema(implementation = MetaFinanceiraRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Meta financeira não encontrada", content = @Content)
            }
    )
    @GetMapping(value = "{idMeta}")
    public ResponseEntity<MetaFinanceiraRespostaDTO> buscarPorId(
            @Parameter(description = "ID da meta financeira a ser encontrada", example = "1")
            @PathVariable Long idMeta) {
        return ResponseEntity.ok().body(mapper.domainToRespostaDTO(useCases.buscarPorId(idMeta)));
    }

    @Operation(
            summary = "Atualizar propriedades de uma meta financeira",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Meta financeira atualizada com sucesso", content = @Content(schema = @Schema(implementation = MetaFinanceiraRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Meta financeira não encontrada", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PutMapping(value = "/{idMetaExistente}")
    public ResponseEntity<MetaFinanceiraRespostaDTO> atualizarMeta(@RequestBody MetaFinanceiraRegistrarDTO metaAtualizada,
                                                                   @Parameter(description = "ID da meta financeira a ser atualizada", example = "3")
                                                                   @PathVariable Long idMetaExistente) {
        MetaFinanceira atualizada = mapper.metaRegistrarDtoToDomain(metaAtualizada);
        return ResponseEntity.ok().body(mapper.domainToRespostaDTO(useCases.atualizarMeta(atualizada, idMetaExistente)));
    }
}
