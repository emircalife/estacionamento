package br.com.calife.estacionamento.domain.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaidaRequestDTO {
    private String placaVeiculo;

    public SaidaRequestDTO() {

    }
}
