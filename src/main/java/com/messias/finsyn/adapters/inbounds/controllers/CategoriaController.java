package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.CategoriaRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.CategoriaRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.CategoriaDTOMapper;
import com.messias.finsyn.application.usecases.CategoriaUseCases;
import com.messias.finsyn.domain.models.categoria.Categoria;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
    private final CategoriaUseCases categoriaUseCases;
    private final CategoriaDTOMapper mapper;

    public CategoriaController(CategoriaUseCases categoriaUseCases, CategoriaDTOMapper categoriaDTOMapper) {
        this.categoriaUseCases = categoriaUseCases;
        this.mapper = categoriaDTOMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaRespostaDTO>> listarTodas() {
        return ResponseEntity.ok().body(categoriaUseCases.buscarTodas().stream().map(mapper::domainToCategoriaRespostaDto).toList());
    }

    @PostMapping
    public ResponseEntity<CategoriaRespostaDTO> registrar(@RequestBody @Valid CategoriaRegistrarDTO registrarDTO) {
        Categoria novaCategoria = categoriaUseCases.criarNovaCategoria(mapper.categoriaRegistrarDtoToDomain(registrarDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.domainToCategoriaRespostaDto(novaCategoria));
    }


}
