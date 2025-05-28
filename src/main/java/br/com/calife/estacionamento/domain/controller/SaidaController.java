package br.com.calife.estacionamento.domain.controller;

import br.com.calife.estacionamento.domain.documentation.SaidaDocumentation;
import br.com.calife.estacionamento.domain.entity.DTO.SaidaRequestDTO;
import br.com.calife.estacionamento.domain.entity.Veiculo;
import br.com.calife.estacionamento.domain.service.EntradaService;
import br.com.calife.estacionamento.config.Constants;
import br.com.calife.estacionamento.domain.service.SaidaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Constants.CONTROLLER.SAIDA.BASE_URL)
public class SaidaController implements SaidaDocumentation {
    @Autowired
    private SaidaService saidaService;

    @PostMapping
    public ResponseEntity<String> registrarSaida(@RequestBody SaidaRequestDTO saidaRequestDTO) {
        try {
            Double valorCobrado = saidaService.registrarSaida(saidaRequestDTO);
            return ResponseEntity.ok("Saída registrada com sucesso. Valor cobrado: R$ " + valorCobrado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar saída: " + e.getMessage());
        }
    }
}
