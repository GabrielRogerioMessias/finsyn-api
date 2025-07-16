package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.DepositoMetaRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.DepositoMetaRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.DepositoMetaDTOMapper;
import com.messias.finsyn.application.usecases.DepositoMetaUseCases;
import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/depositos-metas")
public class DepositoMetaController {
    private final DepositoMetaUseCases depositoMetaUseCases;
    private final DepositoMetaDTOMapper mapper;

    public DepositoMetaController(DepositoMetaUseCases depositoMetaUseCases, DepositoMetaDTOMapper mapper) {
        this.depositoMetaUseCases = depositoMetaUseCases;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<DepositoMetaRespostaDTO> registrar(@Valid @RequestBody DepositoMetaRegistrarDTO dto) {
        DepositoMeta depositoMeta = mapper.dtoRegistrarToDomain(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.domainToDtoResposta(depositoMetaUseCases.registrar(depositoMeta)));
    }
}
