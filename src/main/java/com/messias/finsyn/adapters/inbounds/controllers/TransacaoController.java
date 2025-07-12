package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.TransacaoRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.TransacaoRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.TransacaoDTOMapper;
import com.messias.finsyn.application.usecases.TransacaoUseCase;
import com.messias.finsyn.domain.models.transacao.Transacao;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoController {
    private final TransacaoUseCase transacaoUseCase;
    private final TransacaoDTOMapper mapper;

    public TransacaoController(TransacaoUseCase transacaoUseCase, TransacaoDTOMapper dtoMapper) {
        this.transacaoUseCase = transacaoUseCase;
        this.mapper = dtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<TransacaoRespostaDTO>> listarTodas() {
        return ResponseEntity.ok().body(transacaoUseCase.buscarTodas()
                .stream()
                .map(mapper::domainToTransacaoRespostaDTO)
                .toList());
    }

    @PostMapping
    public ResponseEntity<TransacaoRespostaDTO> registrar(@RequestBody @Valid TransacaoRegistrarDTO registrarDTO) {
        Transacao novaTransacao = transacaoUseCase.registrarTransacao(mapper.transacaoRegistrarDtoToDomain(registrarDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.domainToTransacaoRespostaDTO(novaTransacao));
    }

    @DeleteMapping(value = "/{idTransacao}")
    public ResponseEntity<Void> excluir(@PathVariable Long idTransacao) {
        transacaoUseCase.excluirTransacao(idTransacao);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{idTransacao}")
    public ResponseEntity<TransacaoRespostaDTO> buscarPorId(@PathVariable Long idTransacao) {
        return ResponseEntity.ok().body(mapper.domainToTransacaoRespostaDTO(transacaoUseCase.buscarPorId(idTransacao)));
    }

    @PutMapping(value = "/{idTransacao}")
    public ResponseEntity<TransacaoRespostaDTO> atualizarTransacao(@RequestBody TransacaoRegistrarDTO transacaoAtualizada, @PathVariable Long idTransacao) {
        Transacao transacao = mapper.transacaoRegistrarDtoToDomain(transacaoAtualizada);
        return ResponseEntity.ok().body(mapper.domainToTransacaoRespostaDTO(transacaoUseCase.atualizar(transacao, idTransacao)));
    }
}
