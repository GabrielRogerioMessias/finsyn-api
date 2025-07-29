package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.CategoriaRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.CategoriaRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.CategoriaDTOMapper;
import com.messias.finsyn.application.usecases.CategoriaUseCases;
import com.messias.finsyn.domain.models.categoria.Categoria;
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
@RequestMapping(value = "/categorias")
@Tag(name = "Categorias", description = "Operações relacionadas ás categorias de transações")
public class CategoriaController {
    private final CategoriaUseCases categoriaUseCases;
    private final CategoriaDTOMapper mapper;

    public CategoriaController(CategoriaUseCases categoriaUseCases, CategoriaDTOMapper categoriaDTOMapper) {
        this.categoriaUseCases = categoriaUseCases;
        this.mapper = categoriaDTOMapper;
    }

    @Operation(
            summary = "Listar todas as categorias",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoriaRespostaDTO.class))))
            }
    )
    @GetMapping
    public ResponseEntity<List<CategoriaRespostaDTO>> listarTodas() {
        return ResponseEntity.ok().body(categoriaUseCases.buscarTodas().stream().map(mapper::domainToCategoriaRespostaDto).toList());
    }

    @Operation(
            summary = "Registrar uma nova categoria",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso", content = @Content(schema = @Schema(implementation = CategoriaRespostaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<CategoriaRespostaDTO> registrar(@RequestBody @Valid CategoriaRegistrarDTO registrarDTO) {
        Categoria novaCategoria = categoriaUseCases.criarNovaCategoria(mapper.categoriaRegistrarDtoToDomain(registrarDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.domainToCategoriaRespostaDto(novaCategoria));
    }

    @Operation(
            summary = "Deletar uma categoria",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
            }
    )
    @DeleteMapping(value = "/{idCategoria}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da categoria a ser excluída", example = "2")
            @PathVariable Long idCategoria) {
        categoriaUseCases.deletarCategoria(idCategoria);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar uma categoria por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoria encontrada", content = @Content(schema = @Schema(implementation = CategoriaRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content)
            }
    )
    @GetMapping(value = "{idCategoria}")
    public ResponseEntity<CategoriaRespostaDTO> buscarPorId(
            @Parameter(description = "ID da categoria a ser encontrada", example = "1")
            @PathVariable Long idCategoria) {
        return ResponseEntity.ok().body(mapper.domainToCategoriaRespostaDto(categoriaUseCases.buscarCategoriaPorId(idCategoria)));
    }

    @Operation(
            summary = "Atualizar propriedades de uma categoria",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Propriedades atualizadas com sucesso", content = @Content(schema = @Schema(implementation = CategoriaRespostaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PutMapping(value = "/{idCategoriaExistente}")
    public ResponseEntity<CategoriaRespostaDTO> atualizarCategoria(@RequestBody CategoriaRegistrarDTO categoriaAtualizada,
                                                                   @Parameter(description = "ID da categoria a ser atualizada", example = "10")
                                                                   @PathVariable Long idCategoriaExistente) {
        Categoria categoria = mapper.categoriaRegistrarDtoToDomain(categoriaAtualizada);
        return ResponseEntity.ok().body(mapper.domainToCategoriaRespostaDto(categoriaUseCases.atualizarCategoria(categoria, idCategoriaExistente)));
    }


}
