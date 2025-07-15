package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.MetaFinanceiraRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.MetaFinanceiraRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.MetaFinanceiraDTOMapper;
import com.messias.finsyn.application.usecases.MetaFinanceiraUseCases;
import com.messias.finsyn.domain.models.meta_financeira.MetaFinanceira;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/metas-financeiras")
public class MetaFinanceiraController {
    private final MetaFinanceiraUseCases useCases;
    private final MetaFinanceiraDTOMapper mapper;

    public MetaFinanceiraController(MetaFinanceiraUseCases useCases, MetaFinanceiraDTOMapper mapper) {
        this.useCases = useCases;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<MetaFinanceiraRespostaDTO>> listarTodas() {
        return ResponseEntity.ok().body(useCases.buscarTodas().stream().map(mapper::domainToRespostaDTO).toList());
    }

    @PostMapping
    public ResponseEntity<MetaFinanceiraRespostaDTO> registrar(@Valid @RequestBody MetaFinanceiraRegistrarDTO registrarDTO) {
        MetaFinanceira novaMeta = mapper.metaRegistrarDtoToDomain(registrarDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.domainToRespostaDTO(useCases.criarNovaMeta(novaMeta)));
    }

    @DeleteMapping(value = "/{idMeta}")
    public ResponseEntity<Void> deletar(@PathVariable Long idMeta) {
        useCases.deletarMeta(idMeta);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "{idMeta}")
    public ResponseEntity<MetaFinanceiraRespostaDTO> buscarPorId(@PathVariable Long idMeta) {
        return ResponseEntity.ok().body(mapper.domainToRespostaDTO(useCases.buscarPorId(idMeta)));
    }

    @PutMapping(value = "/{idMetaExistente}")
    public ResponseEntity<MetaFinanceiraRespostaDTO> atualizarMeta(@RequestBody MetaFinanceiraRegistrarDTO metaAtualizada, @PathVariable Long idMetaExistente) {
        MetaFinanceira atualizada = mapper.metaRegistrarDtoToDomain(metaAtualizada);
        return ResponseEntity.ok().body(mapper.domainToRespostaDTO(useCases.atualizarMeta(atualizada, idMetaExistente)));
    }
}
