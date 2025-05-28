package br.com.calife.estacionamento.domain.controller;

import br.com.calife.estacionamento.config.Constants;
import br.com.calife.estacionamento.domain.documentation.EntradaDocumentation;
import br.com.calife.estacionamento.domain.entity.DTO.EntradaRequestDTO;
import br.com.calife.estacionamento.domain.service.EntradaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(Constants.CONTROLLER.ENTRADA.BASE_URL)
public class EntradaController implements EntradaDocumentation {
    @Autowired
    private EntradaService entradaService;

    @PostMapping
    public ResponseEntity<String> registrarEntrada(@RequestBody EntradaRequestDTO entradaRequestDTO) {
        try {
            entradaService.registrarEntrada(entradaRequestDTO);
            return ResponseEntity.ok("Entrada registrada com sucesso.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao registrar entrada");
        }
    }
}