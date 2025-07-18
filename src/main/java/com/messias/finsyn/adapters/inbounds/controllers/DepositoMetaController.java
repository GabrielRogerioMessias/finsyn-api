package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.DepositoMetaRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.DepositoMetaRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.DepositoMetaDTOMapper;
import com.messias.finsyn.application.usecases.DepositoMetaUseCases;
import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depositos-metas")
public class DepositoMetaController {
    private final DepositoMetaUseCases depositoMetaUseCases;
    private final DepositoMetaDTOMapper mapper;

    public DepositoMetaController(DepositoMetaUseCases depositoMetaUseCases, DepositoMetaDTOMapper mapper) {
        this.depositoMetaUseCases = depositoMetaUseCases;
        this.mapper = mapper;
    }

    @GetMapping(value = "/{idMetaFinanceira}")
    public ResponseEntity<List<DepositoMetaRespostaDTO>> listarTodos(@PathVariable Long idMetaFinanceira) {
        return ResponseEntity.ok().body(depositoMetaUseCases.buscarTodos(idMetaFinanceira).stream().map(mapper::domainToDtoResposta).toList());
    }

    @PostMapping
    public ResponseEntity<DepositoMetaRespostaDTO> registrar(@Valid @RequestBody DepositoMetaRegistrarDTO dto) {
        DepositoMeta depositoMeta = mapper.dtoRegistrarToDomain(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.domainToDtoResposta(depositoMetaUseCases.registrar(depositoMeta)));
    }

    @DeleteMapping(value = "/{idDepositoMeta}")
    public ResponseEntity<Void> deletar(@PathVariable Long idDepositoMeta) {
        depositoMetaUseCases.deletar(idDepositoMeta);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/by-id/{idDepositoMeta}")
    public ResponseEntity<DepositoMetaRespostaDTO> buscarPorId(@PathVariable Long idDepositoMeta) {
        return ResponseEntity.ok().body(mapper.domainToDtoResposta(depositoMetaUseCases.buscarPorId(idDepositoMeta)));
    }

    @PutMapping(value = "/{idDepositoMetaExistente}")
    public ResponseEntity<DepositoMetaRespostaDTO> atualizarCategoria(@RequestBody DepositoMetaRegistrarDTO depositoMetaAtualizado, @PathVariable Long idDepositoMetaExistente) {
        DepositoMeta deposito = mapper.dtoRegistrarToDomain(depositoMetaAtualizado);
        return ResponseEntity.ok().body(mapper.domainToDtoResposta(depositoMetaUseCases.atualizar(deposito, idDepositoMetaExistente)));
    }
}
